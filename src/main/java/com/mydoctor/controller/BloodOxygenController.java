package com.mydoctor.controller;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mydoctor.model.BloodOxygen;
import com.mydoctor.model.HeartRate;
import com.mydoctor.service.BloodOxygenService;

@Controller
public class BloodOxygenController {
	@Autowired
	private BloodOxygenService bloodOxygenService;

	@RequestMapping("/bloodOxygen")
	public String graph(Model model, HttpServletRequest request,
			@RequestParam(value = "username", required = false) String username) {

		Device device = DeviceUtils.getCurrentDevice(request);

		String userId = username;

		if (username == null) {
			userId = SecurityContextHolder.getContext().getAuthentication().getName();
		}
		
		List<BloodOxygen> bloodOxygen = this.bloodOxygenService.getAllBloodOxygen(userId);
		model.addAttribute("bloodOxygens", bloodOxygen);
		
		
		
		if (device.isMobile()) {
			return "webview_bloodOxygen";
		}

		return "bloodOxygen";
		
//		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
//		List<BloodOxygen> bloodOxygen = this.bloodOxygenService.getAllBloodOxygen(userId);
//		model.addAttribute("bloodOxygens", bloodOxygen);
//
//		if (request.getQueryString() != null && request.getQueryString().equals("webview")) {
//			return "webview_bloodOxygen";
//		} else {
//			return "bloodOxygen";
//		}

	}
	
	@RequestMapping("/bloodOxygen/search")
	public String search(HttpServletRequest request, Model model) {
		Device device = DeviceUtils.getCurrentDevice(request);

		StringTokenizer st = new StringTokenizer(request.getQueryString(), "/");

		String username = st.nextToken();
		String fromDate = st.nextToken();
		String toDate = st.nextToken();
		System.out.println(username + fromDate + toDate);

		List<BloodOxygen> searchData = this.bloodOxygenService.getBloodOxygenByDate(username, fromDate, toDate);

		model.addAttribute("bloodOxygens", searchData);

		if (device.isMobile()) {
			return "webview_bloodOxygen";
		}

		return "bloodOxygen";

	}
	
	
	@RequestMapping("/bloodOxygen/delete/{username}/{measurement_time}")
	public String deleteProduct(@PathVariable String username,@PathVariable String measurement_time,HttpServletRequest request,RedirectAttributes redirectAttributes){
		System.out.println(measurement_time);
		this.bloodOxygenService.deleteBloodOxygen(username,measurement_time);
		
		redirectAttributes.addAttribute("username",username);
		
		return "redirect:/bloodOxygen";
	}
	
	
	
	
	
	
	
	
	
	
	
}
