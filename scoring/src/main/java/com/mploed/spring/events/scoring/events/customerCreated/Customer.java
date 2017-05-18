package com.mploed.spring.events.scoring.events.customerCreated;

import java.util.Date;

public class Customer {
	private String applicationNumber;

	private Date updated;

	private String city;

	/**
	 * The pug is an avid Bayern München supporter and rates cities with soccer teams he does not like really bad
	 *
	 * @return true / false depending on the city
	 */
	public boolean isCityLegit() {
		if (city.equalsIgnoreCase("Madrid")
				|| city.equalsIgnoreCase("Gelsenkirchen")
				|| city.equalsIgnoreCase("Dortmund")
				|| city.equalsIgnoreCase("Hoffenheim")) {
			return false;
		} else {
			return true;
		}
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"applicationNumber='" + applicationNumber + '\'' +
				", updated=" + updated +
				", city='" + city + '\'' +
				'}';
	}
}
