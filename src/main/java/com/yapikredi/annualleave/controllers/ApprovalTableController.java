package com.yapikredi.annualleave.controllers;

import com.yapikredi.annualleave.enums.ApprovalStatusEnum;
import com.yapikredi.annualleave.resources.ApprovalTableDto;
import com.yapikredi.annualleave.services.ApprovalTableService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/approval")
@Tag(name = "Approval Table", description = "the Approval Table API used for any actions requires approval")
public class ApprovalTableController {

    private ApprovalTableService approvalService;

    @Autowired
    public ApprovalTableController(ApprovalTableService approvalService) {
        this.approvalService = approvalService;
    }

    @GetMapping
    private ResponseEntity<List<ApprovalTableDto>> getList() {
        return new ResponseEntity<>(
                approvalService.toDtoList(approvalService.getList()),
                HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Object> create(@RequestBody ApprovalTableDto approvalRequested) {
        try {
            approvalService.create(approvalRequested);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(approvalRequested, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}/{status}")
    private ResponseEntity<Object> updateApprovalStatus(@PathVariable Long id, @Validated @PathVariable ApprovalStatusEnum status) {
        try {
            approvalService.updateApprovalStatus(id, status);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
