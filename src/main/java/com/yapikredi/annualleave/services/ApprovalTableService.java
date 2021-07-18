package com.yapikredi.annualleave.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yapikredi.annualleave.entities.ApprovalTable;
import com.yapikredi.annualleave.enums.ApprovalEnum;
import com.yapikredi.annualleave.enums.ApprovalStatusEnum;
import com.yapikredi.annualleave.exceptions.AnnualLeaveAlreadyApprovedException;
import com.yapikredi.annualleave.exceptions.ExceedingAnnualLeaveThresholdException;
import com.yapikredi.annualleave.exceptions.StartDateIsGreaterThanEndDateException;
import com.yapikredi.annualleave.factory.ApprovalTopicFactory;
import com.yapikredi.annualleave.interfaces.IApprovalTopicType;
import com.yapikredi.annualleave.repositories.ApprovalTableRepository;
import com.yapikredi.annualleave.resources.ApprovalTableDto;
import com.yapikredi.annualleave.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * a. ConfigurableBeanFactory.SCOPE_PROTOTYPE = A bean with prototype scope will return a different instance every time it is requested from the container.
 * b. ScopedProxyMode.TARGET_CLASS = If there is no active request. Spring will create a proxy to be injected as a dependency,
 *    and instantiate the target bean when it is needed in a request.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApprovalTableService {

    private final ApprovalTableRepository repository;

    private ApprovalTopicFactory topicFactory;

    @Autowired
    public ApprovalTableService(ApprovalTableRepository repository, ApprovalTopicFactory topicFactory) {
        this.repository = repository;
        this.topicFactory = topicFactory;
    }

    public ApprovalTable toEntity(ApprovalTableDto dto) {
        return Utils.MODEL_MAPPER.map(dto, ApprovalTable.class);
    }

    public ApprovalTableDto toDto(ApprovalTable entity) {
        try {
            ApprovalTableDto dto = new ApprovalTableDto(
                    ApprovalEnum.valueOf(entity.getTopic()),
                    Utils.OBJECT_MAPPER.readTree(entity.getDtoBinder()),
                    ApprovalStatusEnum.valueOf(entity.getStatus()),
                    entity.getNote());
            return dto;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ApprovalTableDto> toDtoList(List<ApprovalTable> approvalList) {
        return approvalList.stream().map(e ->toDto(e)).collect(Collectors.toList());
    }

    public List<ApprovalTable> getList() {
        return repository.findAll();
    }

    public void create(ApprovalTableDto dto) {
        repository.save(toEntity(dto));
    }

    public void updateApprovalStatus(Long id, ApprovalStatusEnum status)
            throws StartDateIsGreaterThanEndDateException, ExceedingAnnualLeaveThresholdException, AnnualLeaveAlreadyApprovedException {

        Optional<ApprovalTable> entityOpt = repository.findById(id);

        if (!entityOpt.isPresent()) {
            return;
        }

        ApprovalTable entity = entityOpt.get();

        IApprovalTopicType topicType = topicFactory
                .getApprovalTopicInterface(
                        ApprovalEnum.valueOf(entity.getTopic()));

        topicType.updateStatus(entity, status);

    }

}
