package com.mydoctor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mydoctor.service.DoctorService;

@Controller
@RequestMapping(value="/doctor")
public class DoctorController {
	
	private DoctorService doctorService;
	
	@RequestMapping(value="")
	public String DoctorDashboard(){
		return "doctor_dashboard";
	}
}
