package com.mydoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mydoctor.model.BloodPressure;
import com.mydoctor.model.HeartRate;
import com.mydoctor.service.BloodPressureService;
import com.mydoctor.service.HeartRateService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DashboardController {

	@Autowired
	BloodPressureService bloodPressureService;
	@Autowired
	HeartRateService heartRateService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String dashboard(Model model) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		BloodPressure bloodPressure = this.bloodPressureService.getRecentBloodPressure(userId);
		HeartRate heartRate = this.heartRateService.getRecentHeartRate(userId);

		model.addAttribute("heartRate", heartRate);
		model.addAttribute("bloodPressure",bloodPressure);

		return "dashboard";
	}

}
