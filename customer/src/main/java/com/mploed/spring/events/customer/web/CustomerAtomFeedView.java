package com.mploed.spring.events.customer.web;

import com.mploed.spring.events.customer.domain.Customer;
import com.mploed.spring.events.customer.repository.CustomerRepository;

import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.atom.Person;
import com.rometools.rome.feed.synd.SyndPerson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerAtomFeedView extends AbstractAtomFeedView {
	private CustomerRepository customerRepository;

	@Autowired
	public CustomerAtomFeedView(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Feed feed, HttpServletRequest request) {
		feed.setId("https://github.com/mploed/event-driven-spring-boot/customer");
		feed.setTitle("Customer");
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
		feed.setUpdated(customerRepository.lastUpdate());
		Content subtitle = new Content();
		subtitle.setValue("List of all customers");
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
		List<Customer> customerlist = (List<Customer>) model.get("customers");

		for (Customer o : customerlist) {
			Entry entry = new Entry();
			entry.setId("https://github.com/mploed/event-driven-spring-boot/customer/" + Long.toString(o.getId()));
			entry.setUpdated(o.getUpdated());
			entry.setTitle("Customer " + o.getId());
			List<Content> contents = new ArrayList<Content>();
			Content content = new Content();
			content.setSrc(baseUrl(request) + "customer/rest/" + Long.toString(o.getId()));
			content.setType("application/json");
			contents.add(content);
			entry.setContents(contents);
			Content summary = new Content();
			summary.setValue("This is the customer " + o.getId());
			entry.setSummary(summary);
			entries.add(entry);
		}

		return entries;
	}
}
