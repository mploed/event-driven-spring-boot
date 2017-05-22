package com.mploed.spring.events.creditapplication.domain;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class CreditDetails {
	private int term;

	private BigDecimal amount;

	private String purpose;

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
