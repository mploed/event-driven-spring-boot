package com.mploed.spring.events.creditdecision.web;

import com.mploed.spring.events.creditdecision.domain.DecisionMemo;
import com.mploed.spring.events.creditdecision.repository.DecisionMemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("credit-decision/rest")
public class CreditDecisionRestController {
	private DecisionMemoRepository decisionMemoRepository;

	@Autowired
	public CreditDecisionRestController(DecisionMemoRepository decisionMemoRepository) {
		this.decisionMemoRepository = decisionMemoRepository;
	}

	@GetMapping("/{memoId}")
	public DecisionMemo index(Model model, @PathVariable Long memoId) {
		return decisionMemoRepository.findOne(memoId);
	}
}
