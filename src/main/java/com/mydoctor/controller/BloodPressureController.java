package com.mydoctor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mydoctor.model.BloodPressure;
import com.mydoctor.service.BloodPressureService;

@Controller
public class BloodPressureController {

	@Autowired
	private BloodPressureService bloodPressureService;

	@RequestMapping("/bloodPressure")
	public String graph(Model model, HttpServletRequest request) {
		List<BloodPressure> bloodPressure = this.bloodPressureService.getAllBloodPressure();
		model.addAttribute("bloodPressures", bloodPressure);

		if (request.getQueryString() == null) {
			return "bloodPressure";
		} else {
			return "webview_bloodPressure";
		}
	}

}
