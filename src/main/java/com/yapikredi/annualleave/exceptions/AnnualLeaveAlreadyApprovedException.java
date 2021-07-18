package com.yapikredi.annualleave.exceptions;

public class AnnualLeaveAlreadyApprovedException extends Exception {
    private static final String message = "annual_leave_already_approved_before";
    public AnnualLeaveAlreadyApprovedException(Long id) {
        super(message + ": id '" + id + "'");
    }
    public AnnualLeaveAlreadyApprovedException() {
        super(message);
    }
}
