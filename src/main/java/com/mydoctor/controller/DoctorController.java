package com.mydoctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mydoctor.model.AssignedUser;
import com.mydoctor.service.DoctorService;

@Controller
@RequestMapping(value="/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@RequestMapping(value="")
	public String DoctorDashboard(Model model){
		
		String doctorId = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(doctorId);
		
		List<AssignedUser> userList = doctorService.getAssignedUser(doctorId);
		
		model.addAttribute("userList",userList);
		
		return "doctor_dashboard";
	}
}
