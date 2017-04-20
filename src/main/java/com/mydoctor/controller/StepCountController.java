package com.mydoctor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	public String step(Model model, HttpServletRequest request) {
		List<StepCount> stepCounts = this.stepCountService.getStepCount();
		model.addAttribute("stepCounts", stepCounts);

		if (request.getQueryString() != null && request.getQueryString().equals("webview")) {
			return "webview_stepCount";
		} else {
			return "stepCount";
		}

	}

}