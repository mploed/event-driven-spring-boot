package com.mploed.spring.events.creditapplication.events.internal;

import com.mploed.spring.events.creditapplication.domain.CreditDetails;
import com.mploed.spring.events.creditapplication.domain.FinancialSituation;
import com.mploed.spring.events.creditapplication.events.PersistentEvent;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Entity
public class FinancialSituationEnteredEvent extends PersistentEvent {
	@Embedded
	private FinancialSituation financialSituation;

	private FinancialSituationEnteredEvent() {
	}

	public FinancialSituationEnteredEvent(String applicationNumber,
	                                      FinancialSituation financialSituation) {
		super(applicationNumber);
		this.financialSituation = financialSituation;
	}

	public FinancialSituation getFinancialSituation() {
		return financialSituation;
	}

	@Override
	public String toString() {
		return "FinancialSituationEnteredEvent{" +
				super.toString() +
				"financialSituation=" + financialSituation +
				'}';
	}
}
