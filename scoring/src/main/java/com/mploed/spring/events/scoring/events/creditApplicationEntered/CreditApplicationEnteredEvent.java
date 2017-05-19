package com.mploed.spring.events.scoring.events.creditApplicationEntered;


public class CreditApplicationEnteredEvent {
	private String applicationNumber;

	private CreditDetails creditDetails;

	private FinancialSituation financialSituation;


	public boolean isBalancePositive() {
		long monthlyPayment = getCreditDetails().monthlyPayment();
		long currentMonthlyBalance = getFinancialSituation().balance();

		long overallBalance = currentMonthlyBalance - monthlyPayment;
		if (overallBalance > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isCauseSupportWorthy() {
		String purpose = getCreditDetails().getPurpose().toLowerCase();
		if (purpose != null) {
			if (purpose.contains("pug")
					|| purpose.contains("dog")
					|| purpose.contains("food")
					|| purpose.contains("animal shelter")
					|| purpose.contains("puppy")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String getApplicationNumber() {
		return applicationNumber;
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

	@Override
	public String toString() {
		return "CreditApplicationEnteredEvent{" +
				"applicationNumber='" + applicationNumber + '\'' +
				", creditDetails=" + creditDetails +
				", financialSituation=" + financialSituation +
				'}';
	}
}
