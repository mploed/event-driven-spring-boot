package com.mploed.spring.events.applicationprocess.feed;

import com.mploed.spring.events.applicationprocess.domain.CreditApplicationStatus;
import com.mploed.spring.events.applicationprocess.repository.CreditApplicationStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;

@Component
public class CreditDecisionPoller {
	private final Logger log = LoggerFactory.getLogger(CreditDecisionPoller.class);

	@Value("${creditDecisionFeed}")
	private String creditDecisionFeed;

	private Date lastModified = null;

	CreditApplicationStatusRepository repository;

	// Sprint Rest Template
	RestTemplate restTemplate;

	@Autowired
	public CreditDecisionPoller(CreditApplicationStatusRepository repository, RestTemplate restTemplate) {
		this.repository = repository;
		this.restTemplate = restTemplate;
	}

	@Scheduled(fixedDelay = 15000)
	public void poll() {

		HttpHeaders requestHeaders = new HttpHeaders();
		if (lastModified != null) {
			requestHeaders.set("If-Modified-Since", DateUtils.formatDate(lastModified));
		}
		HttpEntity<?> requestEntity = new HttpEntity(requestHeaders);
		ResponseEntity<Feed> response = restTemplate.exchange(creditDecisionFeed, HttpMethod.GET, requestEntity, Feed.class);

		if (response.getStatusCode() != HttpStatus.NOT_MODIFIED) {
			Feed feed = response.getBody();
			Date lastUpdateInFeed = null;
			for (Entry entry : feed.getEntries()) {
				String applicationNumber = entry.getSummary().getValue();
				if ((lastModified == null) || (entry.getUpdated().after(lastModified))) {
					log.info(applicationNumber + " is new, updating the status");


					CreditApplicationStatus applicationStatus = repository.findByApplicationNumber(applicationNumber);
					if (applicationStatus != null) {
						applicationStatus.setApproved(true);
						repository.save(applicationStatus);
					}
					if ((lastUpdateInFeed == null) || (entry.getUpdated().after(lastUpdateInFeed))) {
						lastUpdateInFeed = entry.getUpdated();
					}
				}
			}
			if (response.getHeaders().getFirst("Last-Modified") != null) {
				lastModified = DateUtils.parseDate(response.getHeaders().getFirst("Last-Modified"));
				log.info("LastModified header {}", lastModified);
			} else {
				if (lastUpdateInFeed != null) {
					lastModified = lastUpdateInFeed;
					log.info("Last in feed {}", lastModified);
				}

			}
		}
	}
}
