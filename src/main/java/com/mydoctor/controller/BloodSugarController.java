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

import com.mydoctor.model.BloodSugar;
import com.mydoctor.model.BloodSugar;
import com.mydoctor.service.BloodSugarService;

@Controller
public class BloodSugarController {

	@Autowired
	private BloodSugarService bloodSugarService;

	@RequestMapping("/bloodSugar")
	public String graph(Model model, HttpServletRequest request,
			@RequestParam(value = "username", required = false) String username) {
		//String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Device device = DeviceUtils.getCurrentDevice(request);

		String userId = username;

		if (username == null) {
			userId = SecurityContextHolder.getContext().getAuthentication().getName();
		}
		
		List<BloodSugar> bloodSugar = this.bloodSugarService.getBloodSugar(userId);
		model.addAttribute("bloodSugars", bloodSugar);
		
		if (device.isMobile()) {
			return "webview_bloodSugar";
		}

		return "bloodSugar";

//		if (request.getQueryString() != null) {
//			StringTokenizer st = new StringTokenizer(request.getQueryString(), "/");
//			if (st.nextToken().equals("webview")) {
//				String id = st.nextToken();
//				List<BloodSugar> bloodSugarWeb = this.bloodSugarService.getBloodSugar(id);
//				model.addAttribute("bloodSugars", bloodSugarWeb);
//			}
//			return "webview_bloodSugar";
//		} else {
//			return "bloodSugar";
//		}

	}
	
	@RequestMapping("/bloodSugar/search")
	public String search(HttpServletRequest request, Model model) {
		Device device = DeviceUtils.getCurrentDevice(request);

		StringTokenizer st = new StringTokenizer(request.getQueryString(), "/");

		String username = st.nextToken();
		String fromDate = st.nextToken();
		String toDate = st.nextToken();
		System.out.println(username + fromDate + toDate);

		List<BloodSugar> searchData = this.bloodSugarService.getBloodSugarByDate(username, fromDate, toDate);

		model.addAttribute("bloodSugars", searchData);

		if (device.isMobile()) {
			return "webview_bloodSugar";
		}

		return "bloodSugar";

	}

	@ResponseBody
	@RequestMapping(value = "/bloodSugar/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BloodSugar> addBloodSugar(@RequestBody BloodSugar bloodSugar) {

		this.bloodSugarService.addBloodSugar(bloodSugar);

		return new ResponseEntity<BloodSugar>(bloodSugar, HttpStatus.OK);
	}
	
	
	
	@RequestMapping("/bloodSugar/delete/{username}/{measurement_time}")
	public String deleteProduct(@PathVariable String username,@PathVariable String measurement_time,HttpServletRequest request,RedirectAttributes redirectAttributes){
		
		System.out.println(measurement_time);
		this.bloodSugarService.deleteBloodSugar(username,measurement_time);
		
		redirectAttributes.addAttribute("username",username);
		
		return "redirect:/bloodSugar";
	}
	

}