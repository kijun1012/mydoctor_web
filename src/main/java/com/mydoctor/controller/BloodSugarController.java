package com.mydoctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String graph(Model model) {
		List<BloodSugar> bloodSugar = this.bloodSugarService.getBloodSugar();
		model.addAttribute("bloodSugars", bloodSugar);

		return "bloodSugar";
	}

}