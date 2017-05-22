package com.mploed.spring.events.creditapplication.repository;

import com.mploed.spring.events.creditapplication.events.internal.FinancialSituationEnteredEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialSituationEnteredEventRepository extends JpaRepository<FinancialSituationEnteredEvent, Long> {
	FinancialSituationEnteredEvent findByApplicationNumber(String applicationNumber);
}
