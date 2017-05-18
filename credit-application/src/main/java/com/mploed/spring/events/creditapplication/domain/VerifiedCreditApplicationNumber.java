package com.mploed.spring.events.creditapplication.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class VerifiedCreditApplicationNumber {
	@Id
	@GeneratedValue
	private Long id;

	private String applicationNumber;

	public VerifiedCreditApplicationNumber() {
	}

	public VerifiedCreditApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
}
