package com.yapikredi.annualleave.exceptions;

public class ExceedingAnnualLeaveThresholdException extends Exception {
    private static final String message = "exceeding_annual_leave_exception";
    public ExceedingAnnualLeaveThresholdException(long remainingDays, long requestedDays) {
        super(message + ": This employee can use '" + remainingDays + " days, but requested " + requestedDays + "'");
    }
    public ExceedingAnnualLeaveThresholdException() {
        super(message);
    }
}
