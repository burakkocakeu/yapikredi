package com.yapikredi.annualleave.exceptions;

public class StartDateIsGreaterThanEndDateException extends Exception {
    private static final String message = "start_date_is_greater_than_end_date";
    public StartDateIsGreaterThanEndDateException(String startDate, String endDate) {
        super(message + ": '" + startDate + " is greater than " + endDate + "'");
    }
    public StartDateIsGreaterThanEndDateException() {
        super(message);
    }
}
