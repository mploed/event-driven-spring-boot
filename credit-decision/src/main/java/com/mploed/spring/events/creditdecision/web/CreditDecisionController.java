package com.mploed.spring.events.creditdecision.web;

import com.mploed.spring.events.creditdecision.domain.DecisionMemo;
import com.mploed.spring.events.creditdecision.repository.DecisionMemoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("credit-decision")
public class CreditDecisionController {
	private DecisionMemoRepository decisionMemoRepository;

	public CreditDecisionController(DecisionMemoRepository decisionMemoRepository) {
		this.decisionMemoRepository = decisionMemoRepository;
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("memoList", decisionMemoRepository.findAll());
		return "index";
	}

	@RequestMapping(value = "/feed", produces = "application/atom+xml")
	public ModelAndView orderFeed(WebRequest webRequest) {
		return new ModelAndView(new CreditDecisionAtomFeedView(decisionMemoRepository), "approvedDecisions", decisionMemoRepository.findByApproved(true));
	}
}
