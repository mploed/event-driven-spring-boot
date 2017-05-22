package com.mploed.spring.events.creditapplication.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class FinancialSituation {
	@Embedded
	private Outgoings outgoings;

	@Embedded
	private Earnings earnings;

	public Outgoings getOutgoings() {
		return outgoings;
	}

	public void setOutgoings(Outgoings outgoings) {
		this.outgoings = outgoings;
	}

	public Earnings getEarnings() {
		return earnings;
	}

	public void setEarnings(Earnings earnings) {
		this.earnings = earnings;
	}

	@Override
	public String toString() {
		return "FinancialSituation{" +
				"outgoings=" + outgoings +
				", earnings=" + earnings +
				'}';
	}
}
