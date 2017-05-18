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

import com.mydoctor.model.SleepingTime;
import com.mydoctor.service.SleepingTimeService;

@Controller
public class SleepingTimeController {

	@Autowired
	SleepingTimeService sleepingTimeService;
	
	@RequestMapping("/sleepingtime")
	public String graph(Model model, HttpServletRequest request,
			@RequestParam(value = "username", required = false) String username) {

		Device device = DeviceUtils.getCurrentDevice(request);

		String userId = username;

		if (username == null) {
			userId = SecurityContextHolder.getContext().getAuthentication().getName();
		}

		
		List<SleepingTime> sleepingTimes = this.sleepingTimeService.getSleepingTime(userId);
		model.addAttribute("sleepingTimes", sleepingTimes);

		if (device.isMobile()) {
			return "webview_sleepingtime";
		}

		return "sleepingtime";

	}

	@RequestMapping("/sleepingtime/search")
	public String search(HttpServletRequest request, Model model) {
		Device device = DeviceUtils.getCurrentDevice(request);

		StringTokenizer st = new StringTokenizer(request.getQueryString(), "/");

		String username = st.nextToken();
		String fromDate = st.nextToken();
		String toDate = st.nextToken();
		System.out.println(username + fromDate + toDate);

		List<SleepingTime> searchData = this.sleepingTimeService.getSleepingTimeByDate(username, fromDate, toDate);

		model.addAttribute("sleepingTimes", searchData);

		if (device.isMobile()) {
			return "webview_sleepingtime";
		}

		return "sleepingtime";

	}

	@ResponseBody
	@RequestMapping(value = "/sleepingtime/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<SleepingTime> addSleepingTime(@RequestBody SleepingTime sleepingTime) {

		this.sleepingTimeService.addSleepingTime(sleepingTime);

		return new ResponseEntity<SleepingTime>(sleepingTime, HttpStatus.OK);
	}
	
	
	
	@RequestMapping("/sleepingtime/delete/{username}/{measurement_time}")
	public String deleteSleepingTime(@PathVariable String username,@PathVariable String measurement_time,HttpServletRequest request,RedirectAttributes redirectAttributes){
		System.out.println(measurement_time);
		this.sleepingTimeService.deleteSleepingTime(username,measurement_time);
		
		redirectAttributes.addAttribute("username",username);
		
		return "redirect:/sleepingtime";
	}
}
