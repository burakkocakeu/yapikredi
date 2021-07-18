package com.yapikredi.annualleave.interfaces;

import com.yapikredi.annualleave.entities.ApprovalTable;
import com.yapikredi.annualleave.enums.ApprovalStatusEnum;
import com.yapikredi.annualleave.exceptions.AnnualLeaveAlreadyApprovedException;
import com.yapikredi.annualleave.exceptions.ExceedingAnnualLeaveThresholdException;
import com.yapikredi.annualleave.exceptions.StartDateIsGreaterThanEndDateException;

public interface IApprovalTopicType {

    void updateStatus(ApprovalTable entity, ApprovalStatusEnum status) throws StartDateIsGreaterThanEndDateException, ExceedingAnnualLeaveThresholdException, AnnualLeaveAlreadyApprovedException;

}
