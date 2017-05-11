package com.mydoctor.controller;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		List<BloodPressure> bloodPressure = this.bloodPressureService.getAllBloodPressure(userId);
		model.addAttribute("bloodPressures", bloodPressure);

		if (request.getQueryString() != null) {
			StringTokenizer st = new StringTokenizer(request.getQueryString(), "/");
			if (st.nextToken().equals("webview")) {
				String id = st.nextToken();
				List<BloodPressure> bloodPressureWeb =this.bloodPressureService.getAllBloodPressure(id);
				model.addAttribute("bloodPressures", bloodPressureWeb);
			}return "webview_bloodPressure";
		} else {
			return "bloodPressure";
		}

	}

}