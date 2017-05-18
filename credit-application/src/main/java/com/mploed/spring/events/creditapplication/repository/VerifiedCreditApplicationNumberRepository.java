package com.mploed.spring.events.creditapplication.repository;

import com.mploed.spring.events.creditapplication.domain.VerifiedCreditApplicationNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifiedCreditApplicationNumberRepository extends JpaRepository<VerifiedCreditApplicationNumber, Long> {
	VerifiedCreditApplicationNumber findByApplicationNumber(String applicationNumber);
}
