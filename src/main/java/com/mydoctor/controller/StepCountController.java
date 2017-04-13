package com.mydoctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mydoctor.model.StepCount;
import com.mydoctor.service.StepCountService;

@Controller
public class StepCountController {
	
	@Autowired
	private StepCountService stepCountService;
	
	@RequestMapping("/stepCount")
	public String step(Model model) {
		List<StepCount> stepCounts = this.stepCountService.getStepCount();
		model.addAttribute("stepCounts", stepCounts);

		return "stepCount";
	}
}
