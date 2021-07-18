package com.yapikredi.annualleave.approval;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yapikredi.annualleave.entities.AnnualLeave;
import com.yapikredi.annualleave.entities.ApprovalTable;
import com.yapikredi.annualleave.entities.Employee;
import com.yapikredi.annualleave.enums.AnnualLeaveInDaysEnum;
import com.yapikredi.annualleave.enums.ApprovalStatusEnum;
import com.yapikredi.annualleave.enums.WeekendEnum;
import com.yapikredi.annualleave.exceptions.AnnualLeaveAlreadyApprovedException;
import com.yapikredi.annualleave.exceptions.ExceedingAnnualLeaveThresholdException;
import com.yapikredi.annualleave.exceptions.StartDateIsGreaterThanEndDateException;
import com.yapikredi.annualleave.interfaces.IApprovalTopicType;
import com.yapikredi.annualleave.resources.approvals.DateRangeDto;
import com.yapikredi.annualleave.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@Component @Slf4j
public class ApprovalAnnualLeave implements IApprovalTopicType {

    private EntityManager entityManager;

    @Autowired
    public ApprovalAnnualLeave(EntityManager entityManager) {

        this.entityManager = entityManager;

    }

    @Override
    @Transactional
    public void updateStatus(ApprovalTable entity, ApprovalStatusEnum status)
            throws StartDateIsGreaterThanEndDateException, ExceedingAnnualLeaveThresholdException, AnnualLeaveAlreadyApprovedException {

        if (ApprovalStatusEnum.APPROVED == ApprovalStatusEnum.valueOf(entity.getStatus())) {
            throw new AnnualLeaveAlreadyApprovedException(entity.getId());
        }

        if (ApprovalStatusEnum.APPROVED != status) {
            entity.setStatus(status.name());
            entityManager.merge(entity);
            return;
        }

        DateRangeDto dto = null;
        try {
            dto = Utils.OBJECT_MAPPER.readValue(entity.getDtoBinder(), DateRangeDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        LocalDateTime fromDate = dto.getFromDate();
        LocalDateTime toDate = dto.getToDate();

        if (fromDate.isAfter(toDate)) {
            throw new StartDateIsGreaterThanEndDateException(fromDate.toString(), toDate.toString());
        }

        Long days = ChronoUnit.DAYS.between(fromDate.toLocalDate(), toDate.toLocalDate());

        log.debug("Days in range: " + days);

        for (LocalDate date = fromDate.toLocalDate(); date.isBefore(toDate.toLocalDate()); date = date.plusDays(1)) {
            if (EnumUtils.isValidEnum(WeekendEnum.class, date.getDayOfWeek().name())) {
                days--;
                log.debug("Removing weekend day at: " + date + " is " + date.getDayOfWeek().name());
            }
        }

        log.debug("Days in workdays: " + days);

        AnnualLeave leave = new AnnualLeave();
        leave.setIdentity(dto.getIdentity());

        AnnualLeave annualLeave = entityManager.createNamedQuery(AnnualLeave.FIND_BY_IDENTITY, AnnualLeave.class)
                .setParameter("identity", leave.getIdentity())
                .getSingleResult();

        Employee employee = entityManager.createNamedQuery(Employee.FIND_BY_IDENTITY, Employee.class)
                .setParameter("identity", leave)
                .getSingleResult();

        Long years =
                ChronoUnit.YEARS.between(employee.getStarted().toLocalDate(), LocalDate.now());

        AtomicLong threshold = new AtomicLong();
        Arrays.stream(AnnualLeaveInDaysEnum.values()).forEach(e -> {
            if (e.getMinYears() <= years && (e.getMaxYears() == null || years <= e.getMaxYears())) {
                threshold.set(e.getThreshold());
            }
        });

        Long leaveUsed = annualLeave.getAnnualLeaveUsed() == null ? 0 : annualLeave.getAnnualLeaveUsed();
        Long leaveUsedTotal = annualLeave.getTotalLeaveUsed() == null ? 0 : annualLeave.getTotalLeaveUsed();

        if (leaveUsed >= threshold.get()) {
            throw new ExceedingAnnualLeaveThresholdException(threshold.get(), days);
        }

        annualLeave.setAnnualLeaveUsed(leaveUsed + days);
        annualLeave.setTotalLeaveUsed(leaveUsedTotal + days);
        entityManager.merge(annualLeave);

        entity.setStatus(status.name());
        entityManager.merge(entity);

    }
}
