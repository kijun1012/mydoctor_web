package com.mydoctor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mydoctor.model.HeartRate;
import com.mydoctor.service.HeartRateService;

@Controller
public class HeartRateController {

	@Autowired
	private HeartRateService heartRateService;

	@RequestMapping("/heartrate")
	public String graph(Model model, HttpServletRequest request) {
		List<HeartRate> heartRates = this.heartRateService.getHeartRate();
		model.addAttribute("heartRates", heartRates);

		if (request.getQueryString() == null) {
			return "heartrate";
		} else {
			return "webview_heartrate";
		}

	}

}