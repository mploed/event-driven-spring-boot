package com.mploed.spring.events.creditdecision.repository;

import com.mploed.spring.events.creditdecision.domain.DecisionMemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface DecisionMemoRepository extends JpaRepository<DecisionMemo, Long> {
	DecisionMemo findByApplicationNumber(String applicationNumber);

	List<DecisionMemo> findByApproved(boolean approved);

	@Query("SELECT max(dm.lastUpdate) FROM DecisionMemo dm")
	Date lastUpdate();
}
