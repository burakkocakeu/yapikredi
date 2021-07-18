package com.yapikredi.annualleave.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter @Getter @Slf4j
public class ApprovalTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;

    @Column(columnDefinition = "TEXT")
    private String dtoBinder;

    private String status;

    private String note;

    @PreRemove
    @PreUpdate
    @PrePersist
    private void beforeAnyUpdate() {
        if (id == null) {
            log.debug("[APPROVAL TABLE] About to add a topic");
        } else {
            log.debug("[APPROVAL TABLE] About to update/delete topic: " + id + ". " + topic);
        }
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate() {
        log.debug("[APPROVAL TABLE] add/update/delete complete for topic: " + id + ". " + topic);
    }

    @PostLoad
    private void afterLoad() {
        log.debug("[APPROVAL TABLE] topic loaded from database: " + id + ". " + topic);
    }

}
