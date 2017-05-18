package com.mploed.spring.events.customer.repository;

import com.mploed.spring.events.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByApplicationNumber(String applicationNumber);

	@Query("SELECT max(c.updated) FROM Customer c")
	Date lastUpdate();
}
