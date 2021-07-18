package com.yapikredi.annualleave.repositories;

import com.yapikredi.annualleave.entities.ApprovalTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalTableRepository extends JpaRepository<ApprovalTable, Long> {
}
