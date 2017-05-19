package com.mydoctor.controller;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mydoctor.model.BloodPressure;
import com.mydoctor.model.HeartRate;
import com.mydoctor.service.BloodPressureService;

@Controller
public class BloodPressureController {

	@Autowired
	private BloodPressureService bloodPressureService;

	@RequestMapping("/bloodPressure")
	public String graph(Model model, HttpServletRequest request,
			@RequestParam(value = "username", required = false) String username) {

		Device device = DeviceUtils.getCurrentDevice(request);

		String userId = username;

		if (username == null) {
			userId = SecurityContextHolder.getContext().getAuthentication().getName();
		}

		List<BloodPressure> bloodPressure = this.bloodPressureService.getAllBloodPressure(userId);
		model.addAttribute("bloodPressures", bloodPressure);

		if (device.isMobile()) {
			return "webview_bloodPressure";
		}

		return "bloodPressure";

		// String userId =
		// SecurityContextHolder.getContext().getAuthentication().getName();
		// List<BloodPressure> bloodPressure =
		// this.bloodPressureService.getAllBloodPressure(userId);
		// model.addAttribute("bloodPressures", bloodPressure);
		//
		// if (request.getQueryString() != null) {
		// StringTokenizer st = new StringTokenizer(request.getQueryString(),
		// "/");
		// if (st.nextToken().equals("webview")) {
		// String id = st.nextToken();
		// List<BloodPressure> bloodPressureWeb
		// =this.bloodPressureService.getAllBloodPressure(id);
		// model.addAttribute("bloodPressures", bloodPressureWeb);
		// }return "webview_bloodPressure";
		// } else {
		// return "bloodPressure";
		// }

	}

	@RequestMapping("/bloodPressure/search")
	public String search(HttpServletRequest request, Model model) {
		Device device = DeviceUtils.getCurrentDevice(request);

		StringTokenizer st = new StringTokenizer(request.getQueryString(), "/");

		String username = st.nextToken();
		String fromDate = st.nextToken();
		String toDate = st.nextToken();
		System.out.println(username + fromDate + toDate);

		List<BloodPressure> searchData = this.bloodPressureService.getBloodPressureByDate(username, fromDate, toDate);

		model.addAttribute("bloodPressures", searchData);

		if (device.isMobile()) {
			return "webview_bloodPressure";
		}

		return "bloodPressure";

	}
	
	
	@ResponseBody
	@RequestMapping(value = "/bloodPressure/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BloodPressure> addBloodPressure(@RequestBody BloodPressure bloodPressure) {

		this.bloodPressureService.addBloodPressure(bloodPressure);

		return new ResponseEntity<BloodPressure>(bloodPressure, HttpStatus.OK);
	}
	
	@RequestMapping("/bloodPressure/delete/{username}/{measurement_time}")
	public String deleteProduct(@PathVariable String username,@PathVariable String measurement_time,HttpServletRequest request,RedirectAttributes redirectAttributes){
		System.out.println(measurement_time);
		this.bloodPressureService.deletebloodPressure(username,measurement_time);
		
		redirectAttributes.addAttribute("username",username);
		
		return "redirect:/bloodPressure";
	}
	

}