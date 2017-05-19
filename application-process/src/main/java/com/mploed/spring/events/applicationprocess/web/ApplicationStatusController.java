package com.mploed.spring.events.applicationprocess.web;

import com.mploed.spring.events.applicationprocess.domain.CreditApplicationStatus;
import com.mploed.spring.events.applicationprocess.repository.CreditApplicationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/status")
public class ApplicationStatusController {
	private CreditApplicationStatusRepository repository;

	@Autowired
	public ApplicationStatusController(CreditApplicationStatusRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public String index(Model model) {
		List<CreditApplicationStatus> statusList = repository.findAll();

		model.addAttribute("statusList", statusList);
		return "status";
	}

	@GetMapping("/{applicationNumber}")
	public String statusForApplicationNummber(@PathVariable String applicationNumber, Model model) {
		CreditApplicationStatus creditApplicationStatus = repository.findByApplicationNumber(applicationNumber);
		model.addAttribute("status", creditApplicationStatus);
		return "confirm";

	}
}
