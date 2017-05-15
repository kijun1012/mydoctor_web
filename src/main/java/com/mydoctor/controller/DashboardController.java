package com.mydoctor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mydoctor.model.AssignedUser;
import com.mydoctor.model.BloodPressure;
import com.mydoctor.model.BloodSugar;
import com.mydoctor.model.HeartRate;
import com.mydoctor.model.StepCount;
import com.mydoctor.model.UserCheckList;
import com.mydoctor.model.Weight;
import com.mydoctor.service.BloodPressureService;
import com.mydoctor.service.BloodSugarService;
import com.mydoctor.service.ChooseDoctorService;
import com.mydoctor.service.HeartRateService;
import com.mydoctor.service.StepCountService;
import com.mydoctor.service.UserCheckListService;
import com.mydoctor.service.WeightService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DashboardController {

	@Autowired
	BloodPressureService bloodPressureService;
	@Autowired
	HeartRateService heartRateService;
	@Autowired
	BloodSugarService bloodSugarService;
	@Autowired
	StepCountService stepCountService;
	@Autowired
	WeightService weightService;
	@Autowired
	UserCheckListService userCheckListService;
	@Autowired
	ChooseDoctorService chooseDoctorService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String dashboard(Model model) {

		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		BloodPressure bloodPressure = this.bloodPressureService.getRecentBloodPressure(userId);
		HeartRate heartRate = this.heartRateService.getRecentHeartRate(userId);
		StepCount stepCount = this.stepCountService.getRecentStepCount(userId);
		BloodSugar bloodSugar = this.bloodSugarService.getRecentBloodSugar(userId);
		Weight weight = this.weightService.getRecentWeight(userId);
		UserCheckList curCheckList = userCheckListService.findById(userId);

		model.addAttribute("heartRate", heartRate);
		model.addAttribute("bloodPressure", bloodPressure);
		model.addAttribute("bloodSugar", bloodSugar);
		model.addAttribute("stepCount", stepCount);
		model.addAttribute("height", curCheckList.getHeight());
		model.addAttribute("weight", curCheckList.getWeight());

		return "dashboard";
	}

	@RequestMapping(value = "/advice")
	public String advice() {
		return "webview_advice";
	}

	@RequestMapping(value = "/chooseDoctor", method = RequestMethod.GET)
	public String chooseDoctor(Model model) {
		AssignedUser assignedUser = new AssignedUser();
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();

		assignedUser.setUsername(userId);
		model.addAttribute("assignedUser", assignedUser);

		return "chooseDoctor";
	}

	@RequestMapping(value = "/chooseDoctor", method = RequestMethod.POST)
	public String chooseDoctorPost(@Valid AssignedUser assignedUser, BindingResult result, HttpServletRequest request) {
		System.out.println(assignedUser.toString());
		
		this.chooseDoctorService.addDoctor(assignedUser);
		
		return "chooseDoctor";
	}

}
