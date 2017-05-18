package com.mploed.spring.events.creditapplication.repository;

import com.mploed.spring.events.creditapplication.events.external.CreditApplicationEnteredEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditApplicationEnteredEventRepository extends JpaRepository<CreditApplicationEnteredEvent, Long> {
}
