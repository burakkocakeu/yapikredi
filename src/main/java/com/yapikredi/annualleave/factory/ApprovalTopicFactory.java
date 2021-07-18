package com.yapikredi.annualleave.factory;

import com.yapikredi.annualleave.approval.ApprovalAnnualLeave;
import com.yapikredi.annualleave.enums.ApprovalEnum;
import com.yapikredi.annualleave.interfaces.IApprovalTopicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApprovalTopicFactory {

    private ApprovalAnnualLeave approvalAnnualLeave;

    @Autowired
    public ApprovalTopicFactory(ApprovalAnnualLeave approvalAnnualLeave) {
        this.approvalAnnualLeave = approvalAnnualLeave;
    }

    public IApprovalTopicType getApprovalTopicInterface(ApprovalEnum topic) {
        switch (topic) {
            case ANNUAL_LEAVE:
                return approvalAnnualLeave;
            default:
                return null;
        }
    }

}
