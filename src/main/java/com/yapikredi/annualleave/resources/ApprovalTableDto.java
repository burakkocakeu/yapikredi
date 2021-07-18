package com.yapikredi.annualleave.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.yapikredi.annualleave.enums.ApprovalEnum;
import com.yapikredi.annualleave.enums.ApprovalStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApprovalTableDto implements Serializable {

    private ApprovalEnum topic;
    private JsonNode dtoBinder;
    private ApprovalStatusEnum status;
    private String note;

}
