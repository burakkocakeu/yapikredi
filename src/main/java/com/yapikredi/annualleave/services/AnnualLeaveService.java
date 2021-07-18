package com.yapikredi.annualleave.services;

import com.yapikredi.annualleave.entities.AnnualLeave;
import com.yapikredi.annualleave.repositories.AnnualLeaveRepository;
import com.yapikredi.annualleave.resources.AnnualLeaveDto;
import com.yapikredi.annualleave.util.Utils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * a. ConfigurableBeanFactory.SCOPE_PROTOTYPE = A bean with prototype scope will return a different instance every time it is requested from the container.
 * b. ScopedProxyMode.TARGET_CLASS = If there is no active request. Spring will create a proxy to be injected as a dependency,
 *    and instantiate the target bean when it is needed in a request.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AnnualLeaveService {

    private final AnnualLeaveRepository repository;

    public AnnualLeaveService(AnnualLeaveRepository repository) {
        this.repository = repository;
    }

    public List<AnnualLeaveDto> toDtoList(List<AnnualLeave> annualLeaveList) {
        return annualLeaveList.stream().map(e -> Utils.MODEL_MAPPER.map(e, AnnualLeaveDto.class)).collect(Collectors.toList());
    }

    public List<AnnualLeave> getAnnualLeaveList() {
        return repository.findAll();
    }

}
