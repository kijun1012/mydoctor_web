package com.mydoctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mydoctor.model.HeartRate;
import com.mydoctor.service.HeartRateService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private HeartRateService heartRateService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {

		return "home";
	}

	@RequestMapping("/graph")
	public String graph(Model model) {
		List<HeartRate> heartRates = this.heartRateService.getHeartRate();
		model.addAttribute("heartRates", heartRates);
		
		
		return "graph";
	}

}
