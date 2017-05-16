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

import com.mydoctor.model.HeartRate;
import com.mydoctor.service.HeartRateService;

@Controller
public class HeartRateController {

	@Autowired
	private HeartRateService heartRateService;

	@RequestMapping("/heartrate")
	public String graph(Model model, HttpServletRequest request,
			@RequestParam(value = "username", required = false) String username) {

		Device device = DeviceUtils.getCurrentDevice(request);

		String userId = username;

		if (username == null) {
			userId = SecurityContextHolder.getContext().getAuthentication().getName();
		}

		
		List<HeartRate> heartRates = this.heartRateService.getHeartRate(userId);
		model.addAttribute("heartRates", heartRates);

		if (device.isMobile()) {
			return "webview_heartrate";
		}

		return "heartrate";

	}

	@RequestMapping("/heartrate/search")
	public String search(HttpServletRequest request, Model model) {
		Device device = DeviceUtils.getCurrentDevice(request);

		StringTokenizer st = new StringTokenizer(request.getQueryString(), "/");

		String username = st.nextToken();
		String fromDate = st.nextToken();
		String toDate = st.nextToken();
		System.out.println(username + fromDate + toDate);

		List<HeartRate> searchData = this.heartRateService.getHeartRateByDate(username, fromDate, toDate);

		model.addAttribute("heartRates", searchData);

		if (device.isMobile()) {
			return "webview_heartrate";
		}

		return "heartrate";

	}

	@ResponseBody
	@RequestMapping(value = "/heartrate/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<HeartRate> addHeartRate(@RequestBody HeartRate heartRate) {

		this.heartRateService.addHeartRate(heartRate);

		return new ResponseEntity<HeartRate>(heartRate, HttpStatus.OK);
	}
	
	@RequestMapping("/heartrate/delete/{username}/{measurement_time}")
	public String deleteProduct(@PathVariable String username,@PathVariable String measurement_time,HttpServletRequest request){
		System.out.println(measurement_time);
		this.heartRateService.deleteHeartRate(username,measurement_time);
		
		return "redirect:/heartrate";
	}
	
	
	
}