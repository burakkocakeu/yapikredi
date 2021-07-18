package com.yapikredi.annualleave.repositories;

import com.yapikredi.annualleave.entities.AnnualLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnualLeaveRepository extends JpaRepository<AnnualLeave, Long> {
}
