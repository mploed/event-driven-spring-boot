package com.mploed.spring.events.creditapplication.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class FinancialSituation {
	private String applicationNumber;

	@Embedded
	private Outgoings outgoings;

	@Embedded
	private Earnings earnings;

	public FinancialSituation() {
	}

	public FinancialSituation(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

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
				"applicationNumber='" + applicationNumber + '\'' +
				", outgoings=" + outgoings +
				", earnings=" + earnings +
				'}';
	}
}
