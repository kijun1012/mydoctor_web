package com.mydoctor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mydoctor.model.Advice;
import com.mydoctor.model.AssignedUser;
import com.mydoctor.model.BloodOxygen;
import com.mydoctor.model.BloodPressure;
import com.mydoctor.model.BloodSugar;
import com.mydoctor.model.Calorie;
import com.mydoctor.model.HeartRate;
import com.mydoctor.model.SleepingTime;
import com.mydoctor.model.StepCount;
import com.mydoctor.model.UserCheckList;
import com.mydoctor.model.Weight;
import com.mydoctor.service.BloodOxygenService;
import com.mydoctor.service.BloodPressureService;
import com.mydoctor.service.BloodSugarService;
import com.mydoctor.service.CalorieService;
import com.mydoctor.service.DoctorService;
import com.mydoctor.service.HeartRateService;
import com.mydoctor.service.SleepingTimeService;
import com.mydoctor.service.StepCountService;
import com.mydoctor.service.UserCheckListService;
import com.mydoctor.service.WeightService;

@Controller
@RequestMapping(value = "/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private BloodPressureService bloodPressureService;
	@Autowired
	private HeartRateService heartRateService;
	@Autowired
	private BloodSugarService bloodSugarService;
	@Autowired
	private StepCountService stepCountService;
	@Autowired
	private WeightService weightService;
	@Autowired
	private UserCheckListService userCheckListService;

	@Autowired
	private BloodOxygenService bloodOxygenService;

	@Autowired
	private SleepingTimeService sleepingTimeService;

	@Autowired
	private CalorieService calorieService;

	public static String selectUsername;

	@RequestMapping(value = "")
	public String DoctorDashboard(Model model, @RequestParam(value = "username", required = false) String username) {

		String doctorId = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(doctorId);

		List<AssignedUser> userList = doctorService.getAssignedUser(doctorId);

		model.addAttribute("userList", userList);

		if (username != null) {
			selectUsername = username;
		}

		if (selectUsername != null) {
			System.out.println(selectUsername);
			model.addAttribute("selectUsername", selectUsername + "님이 선택되었습니다.");

			BloodPressure bloodPressure = this.bloodPressureService.getRecentBloodPressure(selectUsername);
			HeartRate heartRate = this.heartRateService.getRecentHeartRate(selectUsername);
			StepCount stepCount = this.stepCountService.getRecentStepCount(selectUsername);
			BloodSugar bloodSugar = this.bloodSugarService.getRecentBloodSugar(selectUsername);
			Weight weight = this.weightService.getRecentWeight(selectUsername);
			SleepingTime sleepingTime = this.sleepingTimeService.getRecentSleepingTime(selectUsername);

			model.addAttribute("heartRate", heartRate);
			model.addAttribute("bloodPressure", bloodPressure);
			model.addAttribute("bloodSugar", bloodSugar);
			model.addAttribute("stepCount", stepCount);
			model.addAttribute("weight", weight);
			model.addAttribute("sleepingTime", sleepingTime);

			Advice advice = new Advice();
			String userId = SecurityContextHolder.getContext().getAuthentication().getName();
			advice.setDoctorname(userId);
			advice.setUsername(selectUsername);

			model.addAttribute("advice", advice);
			
		}

		return "doctor_dashboard";
	}

	@RequestMapping(value = "/advice", method = RequestMethod.GET)
	public String advice(Model model) {
		Advice advice = new Advice();
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		advice.setDoctorname(userId);
		advice.setUsername(selectUsername);

		model.addAttribute("advice", advice);

		return "advice";
	}

	@RequestMapping(value = "/advice", method = RequestMethod.POST)
	public String advicePost(@Valid Advice advice, BindingResult result, HttpServletRequest request) {

		System.out.println(advice.toString());
		this.doctorService.addAdvice(advice);

		return "redirect:/doctor";
	}

	@RequestMapping("/heartrate")
	public String heartrateByUser(Model model) {

		List<HeartRate> heartRates = this.heartRateService.getHeartRate(selectUsername);
		model.addAttribute("heartRates", heartRates);

		return "heartrate";
	}

	@RequestMapping("/bloodPressure")
	public String bloodPressureByUser(Model model) {
		List<BloodPressure> bloodPressure = this.bloodPressureService.getAllBloodPressure(selectUsername);
		model.addAttribute("bloodPressures", bloodPressure);
		return "bloodPressure";

	}

	@RequestMapping("/stepcount")
	public String stepCountByUser(Model model) {
		List<StepCount> stepCounts = this.stepCountService.getStepCount(selectUsername);
		model.addAttribute("stepCounts", stepCounts);
		return "stepcount";
	}

	@RequestMapping("/bloodSugar")
	public String bloodSugarByUser(Model model) {
		List<BloodSugar> bloodSugar = this.bloodSugarService.getBloodSugar(selectUsername);
		model.addAttribute("bloodSugars", bloodSugar);

		return "bloodSugar";
	}

	@RequestMapping("/bloodOxygen")
	public String bloodOxygenByUser(Model model) {
		List<BloodOxygen> bloodOxygen = this.bloodOxygenService.getAllBloodOxygen(selectUsername);
		model.addAttribute("bloodOxygens", bloodOxygen);

		return "bloodOxygen";
	}

	@RequestMapping("/weight")
	public String weightByUser(Model model) {

		List<Weight> weights = this.weightService.getAllWeight(selectUsername);
		model.addAttribute("weights", weights);

		return "weight";
	}

	@RequestMapping("/sleepingtime")
	public String sleepingTimeByUser(Model model) {

		List<SleepingTime> sleepingTimes = this.sleepingTimeService.getSleepingTime(selectUsername);
		model.addAttribute("sleepingTimes", sleepingTimes);

		return "sleepingtime";
	}

	@RequestMapping("/calorie")
	public String calorieByUser(Model model) {

		List<Calorie> calories = this.calorieService.getAllCalorie(selectUsername);
		model.addAttribute("calories", calories);

		return "calorie";
	}

}