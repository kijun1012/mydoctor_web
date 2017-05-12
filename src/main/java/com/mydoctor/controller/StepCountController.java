package com.mydoctor.controller;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mydoctor.model.HeartRate;
import com.mydoctor.model.StepCount;
import com.mydoctor.service.StepCountService;

@Controller
public class StepCountController {

	@Autowired
	private StepCountService stepCountService;

	@RequestMapping("/stepCount")
	public String step(Model model, HttpServletRequest request) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		List<StepCount> stepCounts = this.stepCountService.getStepCount(userId);
		model.addAttribute("stepCounts", stepCounts);

		if (request.getQueryString() != null) {
			StringTokenizer st = new StringTokenizer(request.getQueryString(), "/");
			if (st.nextToken().equals("webview")) {
				String id = st.nextToken();
				List<StepCount> stepCountWeb = this.stepCountService.getStepCount(id);
				model.addAttribute("stepCounts", stepCountWeb);
			}
			return "webview_stepCount";
		} else {
			return "stepCount";
		}

	}

}