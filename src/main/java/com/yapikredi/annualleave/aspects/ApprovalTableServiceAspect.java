package com.yapikredi.annualleave.aspects;

import com.yapikredi.annualleave.enums.ApprovalStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect @Slf4j
public class ApprovalTableServiceAspect {

    // private static Logger log = LoggerFactory.getLogger(ApprovalTableServiceAspect.class);

    /**
     * Long id, ApprovalStatusEnum status
     * @param joinPoint
     */
    @After("execution(* com.yapikredi.annualleave.services.ApprovalTableService.updateApprovalStatus(..))")
    private void afterUpdateApprovalStatus(JoinPoint joinPoint) {
        Long id = (Long) joinPoint.getArgs()[0];
        ApprovalStatusEnum status = (ApprovalStatusEnum) joinPoint.getArgs()[1];

        log.debug("Approval has been updated successfully! - id: " + id + " status: " + status);
    }

}
