package com.mploed.spring.events.creditdecision.events.applicationentered;

import java.io.Serializable;
import java.math.MathContext;
import java.math.RoundingMode;

public class ApplicationEnteredEvent implements Serializable {
	private String applicationNumber;
	private CreditDetails creditDetails;
	private FinancialSituation financialSituation;

	public long monthlyIncome() {
		return financialSituation.getEarnings().sum();
	}

	public long monthlyOutgoings() {
		return financialSituation.getOutgoings().sum();
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public long creditAmount() {
		return creditDetails.getAmount().round(new MathContext(0, RoundingMode.HALF_UP)).longValue();
	}

	public long creditTerm() {
		return creditDetails.getTerm();
	}

	public String creditReason() {
		return creditDetails.getPurpose();
	}

	private void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	private CreditDetails getCreditDetails() {
		return creditDetails;
	}

	private void setCreditDetails(CreditDetails creditDetails) {
		this.creditDetails = creditDetails;
	}

	private FinancialSituation getFinancialSituation() {
		return financialSituation;
	}

	private void setFinancialSituation(FinancialSituation financialSituation) {
		this.financialSituation = financialSituation;
	}
}
