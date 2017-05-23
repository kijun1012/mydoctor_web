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

import com.mydoctor.model.Calorie;
import com.mydoctor.model.StepCount;
import com.mydoctor.service.CalorieService;

@Controller
@RequestMapping("/calorie")
public class CalorieController {

	@Autowired
	private CalorieService calorieService;

	@RequestMapping("")
	public String calorie(Model model, HttpServletRequest request,
			@RequestParam(value = "username", required = false) String username) {

		Device device = DeviceUtils.getCurrentDevice(request);

		String userId = username;

		if (username == null) {
			userId = SecurityContextHolder.getContext().getAuthentication().getName();
		}

		List<Calorie> calories = this.calorieService.getAllCalorie(userId);
		model.addAttribute("calories", calories);

		if (device.isMobile()) {
			return "webview_calorie";
		}

		return "calorie";
	}

	@RequestMapping("/search")
	public String search(HttpServletRequest request, Model model) {
		Device device = DeviceUtils.getCurrentDevice(request);

		StringTokenizer st = new StringTokenizer(request.getQueryString(), "/");

		String username = st.nextToken();
		String fromDate = st.nextToken();
		String toDate = st.nextToken();
		System.out.println(username + fromDate + toDate);

		List<Calorie> searchData = this.calorieService.getCalorieByDate(username, fromDate, toDate);

		model.addAttribute("calories", searchData);

		if (device.isMobile()) {
			return "webview_calorie";
		}

		return "calorie";

	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Calorie> addCalorie(@RequestBody Calorie calorie) {

		this.calorieService.addCalorie(calorie);

		return new ResponseEntity<Calorie>(calorie, HttpStatus.OK);
	}
	
	@RequestMapping("/delete/{username}/{measurement_time}")
	public String deleteCalorie(@PathVariable String username,@PathVariable String measurement_time,HttpServletRequest request,RedirectAttributes redirectAttributes){
		System.out.println(measurement_time);
		
		this.calorieService.deleteCalorie(username,measurement_time);
		
		redirectAttributes.addAttribute("username",username);
		
		return "redirect:/calorie";
	}

}
