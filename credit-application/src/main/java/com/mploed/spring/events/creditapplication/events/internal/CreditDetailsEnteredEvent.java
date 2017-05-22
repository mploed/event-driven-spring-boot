package com.mploed.spring.events.creditapplication.events.internal;

import com.mploed.spring.events.creditapplication.domain.CreditDetails;
import com.mploed.spring.events.creditapplication.events.PersistentEvent;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Entity
public class CreditDetailsEnteredEvent extends PersistentEvent implements InternalEvent {
	@Embedded
	private CreditDetails creditDetails;

	private CreditDetailsEnteredEvent() {
		super();
	}

	public CreditDetailsEnteredEvent(String applicationNumber, CreditDetails creditDetails) {
		super(applicationNumber);
		this.creditDetails = creditDetails;
	}

	public CreditDetails getCreditDetails() {
		return creditDetails;
	}

	@Override
	public String toString() {
		return "CreditDetailsEnteredEvent{" +
				super.toString() +
				"creditDetails=" + creditDetails +
				'}';
	}
}
