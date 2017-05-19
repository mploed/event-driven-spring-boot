package com.mploed.spring.events.creditdecision.events.applicationentered;


public class FinancialSituation {

	private Outgoings outgoings;

	private Earnings earnings;

	public FinancialSituation() {
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
				"outgoings=" + outgoings +
				", earnings=" + earnings +
				'}';
	}
}
