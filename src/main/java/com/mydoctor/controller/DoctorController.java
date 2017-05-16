package com.mydoctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mydoctor.model.AssignedUser;
import com.mydoctor.model.BloodOxygen;
import com.mydoctor.model.BloodPressure;
import com.mydoctor.model.BloodSugar;
import com.mydoctor.model.HeartRate;
import com.mydoctor.model.StepCount;
import com.mydoctor.model.UserCheckList;
import com.mydoctor.model.Weight;
import com.mydoctor.service.BloodOxygenService;
import com.mydoctor.service.BloodPressureService;
import com.mydoctor.service.BloodSugarService;
import com.mydoctor.service.DoctorService;
import com.mydoctor.service.HeartRateService;
import com.mydoctor.service.StepCountService;
import com.mydoctor.service.UserCheckListService;
import com.mydoctor.service.WeightService;

@Controller
@RequestMapping(value = "/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

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
	BloodOxygenService bloodOxygenService;

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
			model.addAttribute("selectUsername", selectUsername + "님을 선택하였습니다.");

			BloodPressure bloodPressure = this.bloodPressureService.getRecentBloodPressure(selectUsername);
			HeartRate heartRate = this.heartRateService.getRecentHeartRate(selectUsername);
			StepCount stepCount = this.stepCountService.getRecentStepCount(selectUsername);
			BloodSugar bloodSugar = this.bloodSugarService.getRecentBloodSugar(selectUsername);
			Weight weight = this.weightService.getRecentWeight(selectUsername);
			UserCheckList curCheckList = userCheckListService.findById(selectUsername);

			model.addAttribute("heartRate", heartRate);
			model.addAttribute("bloodPressure", bloodPressure);
			model.addAttribute("bloodSugar", bloodSugar);
			model.addAttribute("stepCount", stepCount);
			model.addAttribute("height", curCheckList.getHeight());
			model.addAttribute("weight", curCheckList.getWeight());

		}

		return "doctor_dashboard";
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

	@RequestMapping("/stepCount")
	public String stepCountByUser(Model model) {
		List<StepCount> stepCounts = this.stepCountService.getStepCount(selectUsername);
		model.addAttribute("stepCounts", stepCounts);
		return "stepCount";
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

}
