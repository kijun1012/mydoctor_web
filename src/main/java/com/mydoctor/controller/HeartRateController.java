package com.mydoctor.controller;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mydoctor.model.HeartRate;
import com.mydoctor.service.HeartRateService;

@Controller
public class HeartRateController {

	@Autowired
	private HeartRateService heartRateService;

	@RequestMapping("/heartrate")
	public String graph(Model model, HttpServletRequest request) {

		if (request.getQueryString() != null) {
			StringTokenizer st = new StringTokenizer(request.getQueryString(), "/");
			if (st.nextToken().equals("webview")) {
				String id = st.nextToken();
				List<HeartRate> heartRatesWeb = this.heartRateService.getHeartRate(id);
				model.addAttribute("heartRates", heartRatesWeb);
			}
			return "webview_heartrate";

		} 
		else {
			String userId = SecurityContextHolder.getContext().getAuthentication().getName();

			List<HeartRate> heartRates = this.heartRateService.getHeartRate(userId);
			model.addAttribute("heartRates", heartRates);
			System.out.println(request.getQueryString());
			return "heartrate";
		}
	}

	@RequestMapping("/heartrate/search")
	public String search(HttpServletRequest request, Model model) {
		StringTokenizer st = new StringTokenizer(request.getQueryString(), "/");

		String username = st.nextToken();
		String fromDate = st.nextToken();
		String toDate = st.nextToken();
		System.out.println(username + fromDate + toDate);

		List<HeartRate> searchData = this.heartRateService.getHeartRateByDate(username, fromDate, toDate);

		model.addAttribute("heartRates", searchData);

		return "heartrate";

	}
	
	@ResponseBody
	@RequestMapping(value = "/heartrate/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<HeartRate> addHeartRate(@RequestBody HeartRate heartRate){
		
		this.heartRateService.addHeartRate(heartRate);
		
		
		return new ResponseEntity<HeartRate>(heartRate,HttpStatus.OK);
	}
}