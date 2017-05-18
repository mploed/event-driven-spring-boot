package com.mploed.spring.events.scoring.repository;

import com.mploed.spring.events.scoring.domain.ScoringResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoringResultRepository extends JpaRepository<ScoringResult, Long> {
	ScoringResult findByApplicationNumber(String applicationNumber);
}
