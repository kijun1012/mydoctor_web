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

import com.mydoctor.model.HeartRate;
import com.mydoctor.model.UserCheckList;
import com.mydoctor.model.Weight;
import com.mydoctor.service.UserCheckListService;
import com.mydoctor.service.WeightService;

@Controller
public class WeightController {

	@Autowired
	private WeightService weightService;
	
	@Autowired
	private UserCheckListService userCheckListService;

	@RequestMapping("/weight")
	public String weight(Model model, HttpServletRequest request,
			@RequestParam(value = "username", required = false) String username) {

		Device device = DeviceUtils.getCurrentDevice(request);

		String userId = username;

		if (username == null) {
			userId = SecurityContextHolder.getContext().getAuthentication().getName();
		}

		List<Weight> weights = this.weightService.getAllWeight(userId);
		model.addAttribute("weights", weights);

		if (device.isMobile()) {
			return "webview_weight";
		}

		return "weight";

		// String userId =
		// SecurityContextHolder.getContext().getAuthentication().getName();
		// List<Weight> weights = this.weightService.getAllWeight(userId);
		// model.addAttribute("weights", weights);
		//
		// //System.out.println(weights.get(0).toString());
		//
		// if (request.getQueryString() != null) {
		// StringTokenizer st = new StringTokenizer(request.getQueryString(),
		// "/");
		// if (st.nextToken().equals("webview")) {
		// String id = st.nextToken();
		// List<Weight> weightsWeb = this.weightService.getAllWeight(id);
		// model.addAttribute("weights", weightsWeb);
		// }
		// return "webview_weight";
		// } else {
		// return "weight";
		// }

	}
	
	@RequestMapping("/weight/search")
	public String search(HttpServletRequest request, Model model) {
		Device device = DeviceUtils.getCurrentDevice(request);

		StringTokenizer st = new StringTokenizer(request.getQueryString(), "/");

		String username = st.nextToken();
		String fromDate = st.nextToken();
		String toDate = st.nextToken();
		System.out.println(username + fromDate + toDate);

		List<Weight> searchData = this.weightService.getWeightByDate(username, fromDate, toDate);

		model.addAttribute("weights", searchData);

		if (device.isMobile()) {
			return "webview_weight";
		}

		return "weight";

	}
	
	@ResponseBody
	@RequestMapping(value = "/weight/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Weight> addWeight(@RequestBody Weight weight) {

		this.weightService.addWeight(weight);

		return new ResponseEntity<Weight>(weight, HttpStatus.OK);
	}
	
	@RequestMapping("/weight/delete/{username}/{measurement_time}")
	public String deleteProduct(@PathVariable String username,@PathVariable String measurement_time,HttpServletRequest request,RedirectAttributes redirectAttributes){
		System.out.println(measurement_time);
		
		this.weightService.deleteWeight(username,measurement_time);
	
		
		redirectAttributes.addAttribute("username",username);
		
		return "redirect:/weight";
	}
	
	
	

}