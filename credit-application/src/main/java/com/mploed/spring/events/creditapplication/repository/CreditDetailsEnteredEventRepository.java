package com.mploed.spring.events.creditapplication.repository;

import com.mploed.spring.events.creditapplication.events.internal.CreditDetailsEnteredEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditDetailsEnteredEventRepository extends JpaRepository<CreditDetailsEnteredEvent, Long> {
	CreditDetailsEnteredEvent findByApplicationNumber(String applicationNumber);
}
