package com.mploed.spring.events.applicationprocess.repository;

import com.mploed.spring.events.applicationprocess.domain.CreditApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditApplicationStatusRepository extends JpaRepository<CreditApplicationStatus, Long> {
	CreditApplicationStatus findByApplicationNumber(String applicationNumber);

}
