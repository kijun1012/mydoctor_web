package com.mydoctor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mydoctor.model.BloodOxygen;
import com.mydoctor.service.BloodOxygenService;

@Controller
public class BloodOxygenController {
	@Autowired
	private BloodOxygenService bloodOxygenService;

	@RequestMapping("/bloodOxygen")
	public String graph(Model model, HttpServletRequest request) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		List<BloodOxygen> bloodOxygen = this.bloodOxygenService.getAllBloodOxygen(userId);
		model.addAttribute("bloodOxygens", bloodOxygen);

		if (request.getQueryString() != null && request.getQueryString().equals("webview")) {
			return "webview_bloodOxygen";
		} else {
			return "bloodOxygen";
		}

	}
}
