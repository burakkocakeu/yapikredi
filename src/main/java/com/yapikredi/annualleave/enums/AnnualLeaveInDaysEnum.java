package com.yapikredi.annualleave.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AnnualLeaveInDaysEnum {
    NONE(0, 1, 5),
    FIRST_LEVEL(1, 5, 15),
    SECOND_LEVEL(5, 10, 18),
    LAST_LEVEL(10, null, 24);

    AnnualLeaveInDaysEnum(int minYears, Integer maxYears, int threshold) {
        this.minYears = minYears;
        this.maxYears = maxYears;
        this.threshold = threshold;
    }

    private int minYears;
    private Integer maxYears;
    private int threshold;

    public int getMinYears() {
        return minYears;
    }

    public Integer getMaxYears() {
        return maxYears;
    }

    public int getThreshold() {
        return threshold;
    }
}
