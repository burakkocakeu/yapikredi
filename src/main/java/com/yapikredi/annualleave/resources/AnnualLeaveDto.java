package com.yapikredi.annualleave.resources;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter @Setter
public class AnnualLeaveDto implements Serializable {

    private Long identity;
    private Short annualLeaveUsed;
    private Short totalLeaveUsed;

}
