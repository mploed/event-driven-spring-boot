package com.mploed.spring.events.creditdecision.events.applicationentered;

import java.math.BigDecimal;


public class CreditDetails {

	private int term;

	private BigDecimal amount;

	private String purpose;

	private CreditDetails() {
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
				"term=" + term +
				", amount=" + amount +
				", purpose='" + purpose + '\'' +
				'}';
	}
}
