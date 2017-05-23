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

import com.mydoctor.model.StepCount;
import com.mydoctor.service.StepCountService;

@Controller
public class StepCountController {

	@Autowired
	private StepCountService stepCountService;

	@RequestMapping("/stepcount")
	public String step(Model model, HttpServletRequest request,
			@RequestParam(value = "username", required = false) String username) {
		
		Device device = DeviceUtils.getCurrentDevice(request);

		String userId = username;
		
		if (username == null) {
			userId = SecurityContextHolder.getContext().getAuthentication().getName();
		}
		
		List<StepCount> stepCounts = this.stepCountService.getStepCount(userId);
		model.addAttribute("stepCounts", stepCounts);
		
		if (device.isMobile()) {
			return "webview_stepcount";
		}

		return "stepcount";
		
//		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
//		List<StepCount> stepCounts = this.stepCountService.getStepCount(userId);
//		model.addAttribute("stepCounts", stepCounts);
//
//		if (request.getQueryString() != null) {
//			StringTokenizer st = new StringTokenizer(request.getQueryString(), "/");
//			if (st.nextToken().equals("webview")) {
//				String id = st.nextToken();
//				List<StepCount> stepCountWeb = this.stepCountService.getStepCount(id);
//				model.addAttribute("stepCounts", stepCountWeb);
//			}
//			return "webview_stepCount";
//		} else {
//			return "stepCount";
//		}

	}
	
	@RequestMapping("/stepcount/search")
	public String search(HttpServletRequest request, Model model) {
		Device device = DeviceUtils.getCurrentDevice(request);

		StringTokenizer st = new StringTokenizer(request.getQueryString(), "/");

		String username = st.nextToken();
		String fromDate = st.nextToken();
		String toDate = st.nextToken();
		System.out.println(username + fromDate + toDate);

		List<StepCount> searchData = this.stepCountService.getStepCountByDate(username, fromDate, toDate);

		model.addAttribute("stepCounts", searchData);

		if (device.isMobile()) {
			return "webview_stepcount";
		}

		return "stepcount";

	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/stepcount/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<StepCount> addStepCount(@RequestBody StepCount stepCount){
		
		this.stepCountService.addStepCount(stepCount);
		
		
		return new ResponseEntity<StepCount>(stepCount,HttpStatus.OK);
	}
	
	
	@RequestMapping("/stepcount/delete/{username}/{measurement_time}")
	public String deleteStepCount(@PathVariable String username,@PathVariable String measurement_time,HttpServletRequest request,RedirectAttributes redirectAttributes){
		System.out.println(measurement_time);
		this.stepCountService.deleteStepCount(username,measurement_time);
		
		redirectAttributes.addAttribute("username",username);
		
		return "redirect:/stepcount";
	}
	
	
	

}