package com.mploed.spring.events.creditapplication.events.external;

import com.mploed.spring.events.creditapplication.domain.CreditDetails;
import com.mploed.spring.events.creditapplication.domain.FinancialSituation;
import com.mploed.spring.events.creditapplication.events.PersistentEvent;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class CreditApplicationEnteredEvent extends PersistentEvent implements ExternalEvent {
	@Embedded
	private CreditDetails creditDetails;

	@Embedded
	private FinancialSituation financialSituation;

	private CreditApplicationEnteredEvent() {
	}

	public CreditApplicationEnteredEvent(String applicationNumber, CreditDetails creditDetails, FinancialSituation financialSituation) {
		super(applicationNumber);
		this.creditDetails = creditDetails;
		this.financialSituation = financialSituation;
	}

	public CreditDetails getCreditDetails() {
		return creditDetails;
	}

	public FinancialSituation getFinancialSituation() {
		return financialSituation;
	}

	@Override
	public String toString() {
		return "CreditApplicationEnteredEvent{" +
				super.toString() +
				"creditDetails=" + creditDetails +
				", financialSituation=" + financialSituation +
				'}';
	}
}
