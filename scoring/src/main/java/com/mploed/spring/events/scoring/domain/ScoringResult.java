package com.mploed.spring.events.scoring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ScoringResult {
	@Id
	@GeneratedValue
	private long id;

	private String applicationNumber;

	private Boolean positiveBalance;

	private Boolean supportworthyCause;

	private Boolean legitCity;

	private Date lastUpdate;


	public boolean isComplete() {
		return positiveBalance != null
				&& supportworthyCause != null
				&& legitCity != null;
	}

	public boolean isScoringPositive() {
		return legitCity && positiveBalance && supportworthyCause;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public Boolean getPositiveBalance() {
		return positiveBalance;
	}

	public void setPositiveBalance(Boolean positiveBalance) {
		this.positiveBalance = positiveBalance;
	}

	public Boolean getSupportworthyCause() {
		return supportworthyCause;
	}

	public void setSupportworthyCause(Boolean supportworthyCause) {
		this.supportworthyCause = supportworthyCause;
	}

	public Boolean getLegitCity() {
		return legitCity;
	}

	public void setLegitCity(Boolean legitCity) {
		this.legitCity = legitCity;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
