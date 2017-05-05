package com.mydoctor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mydoctor.model.HeartRate;
import com.mydoctor.service.HeartRateService;

@Controller
public class HeartRateController {

	@Autowired
	private HeartRateService heartRateService;

	@RequestMapping("/heartrate")
	public String graph(Model model, HttpServletRequest request) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		List<HeartRate> heartRates = this.heartRateService.getHeartRate(userId);
		model.addAttribute("heartRates", heartRates);

		if (request.getQueryString() != null && request.getQueryString().equals("webview")) {
			return "webview_heartrate";
		} else {
			return "heartrate";
		}
	}
}