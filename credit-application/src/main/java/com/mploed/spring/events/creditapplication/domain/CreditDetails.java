package com.mploed.spring.events.creditapplication.domain;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class CreditDetails {
	private String applicationNumber;

	private int term;

	private BigDecimal amount;

	private String purpose;

	private CreditDetails() {
	}

	public CreditDetails(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@Override
	public String toString() {
		return "CreditDetails{" +
				"applicationNumber='" + applicationNumber + '\'' +
				", term=" + term +
				", amount=" + amount +
				", purpose='" + purpose + '\'' +
				'}';
	}
}
