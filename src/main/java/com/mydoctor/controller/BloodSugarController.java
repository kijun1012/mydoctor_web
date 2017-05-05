package com.mydoctor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mydoctor.model.BloodSugar;
import com.mydoctor.service.BloodSugarService;

@Controller
public class BloodSugarController {

	@Autowired
	private BloodSugarService bloodSugarService;

	@RequestMapping("/bloodSugar")
	public String graph(Model model, HttpServletRequest request) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		List<BloodSugar> bloodSugar = this.bloodSugarService.getBloodSugar(userId);
		model.addAttribute("bloodSugars", bloodSugar);

		if (request.getQueryString() != null && request.getQueryString().equals("webview")) {
			return "webview_bloodSugar";
		} else {
			return "bloodSugar";
		}

	}

}