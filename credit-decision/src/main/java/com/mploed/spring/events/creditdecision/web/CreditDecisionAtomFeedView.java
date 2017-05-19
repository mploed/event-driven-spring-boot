package com.mploed.spring.events.creditdecision.web;

import com.mploed.spring.events.creditdecision.domain.DecisionMemo;
import com.mploed.spring.events.creditdecision.repository.DecisionMemoRepository;
import com.rometools.rome.feed.atom.*;
import com.rometools.rome.feed.synd.SyndPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreditDecisionAtomFeedView extends AbstractAtomFeedView {
	private DecisionMemoRepository decisionMemoRepository;

	@Autowired
	public CreditDecisionAtomFeedView(DecisionMemoRepository decisionMemoRepository) {
		this.decisionMemoRepository = decisionMemoRepository;
	}

	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Feed feed, HttpServletRequest request) {
		feed.setId("https://github.com/mploed/event-driven-spring-boot/credit-decision");
		feed.setTitle("Approved Credit Applications");
		List<Link> alternateLinks = new ArrayList<>();
		Link link = new Link();
		link.setRel("self");
		link.setHref(baseUrl(request) + "feed");
		alternateLinks.add(link);
		List<SyndPerson> authors = new ArrayList<SyndPerson>();
		Person person = new Person();
		person.setName("Big Pug Bank");
		authors.add(person);
		feed.setAuthors(authors);

		feed.setAlternateLinks(alternateLinks);
		feed.setUpdated(decisionMemoRepository.lastUpdate());
		Content subtitle = new Content();
		subtitle.setValue("List of all APPROVED credit applications");
		feed.setSubtitle(subtitle);
	}

	private String baseUrl(HttpServletRequest request) {
		return String.format("%s://%s:%d%s/", request.getScheme(), request.getServerName(), request.getServerPort(),
				request.getContextPath());
	}

	@Override
	protected List<Entry> buildFeedEntries(Map<String, Object> model, HttpServletRequest request,
	                                       HttpServletResponse response) throws Exception {

		List<Entry> entries = new ArrayList<Entry>();
		List<DecisionMemo> decisionMemos = (List<DecisionMemo>) model.get("approvedDecisions");

		for (DecisionMemo o : decisionMemos) {
			Entry entry = new Entry();
			entry.setId("https://github.com/mploed/event-driven-spring-boot/decision-memo/" + o.getId());
			entry.setUpdated(o.getLastUpdate());
			entry.setTitle("Approved Decision " + o.getId());
			List<Content> contents = new ArrayList<Content>();
			Content content = new Content();
			content.setSrc(baseUrl(request) + "credit-decision/rest/" + o.getId());
			content.setType("application/json");
			contents.add(content);
			entry.setContents(contents);
			Content summary = new Content();
			summary.setValue(o.getApplicationNumber());
			entry.setSummary(summary);
			entries.add(entry);
		}

		return entries;
	}
}
