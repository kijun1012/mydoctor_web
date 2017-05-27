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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mydoctor.model.Advice;
import com.mydoctor.model.AnalysisData;
import com.mydoctor.model.AssignedUser;
import com.mydoctor.model.BloodPressure;
import com.mydoctor.model.BloodSugar;
import com.mydoctor.model.HeartRate;
import com.mydoctor.model.SleepingTime;
import com.mydoctor.model.StepCount;
import com.mydoctor.model.UserCheckList;
import com.mydoctor.model.Weight;
import com.mydoctor.service.AdviceService;
import com.mydoctor.service.AnalysisDataService;
import com.mydoctor.service.BloodPressureService;
import com.mydoctor.service.BloodSugarService;
import com.mydoctor.service.ChooseDoctorService;
import com.mydoctor.service.HeartRateService;
import com.mydoctor.service.SleepingTimeService;
import com.mydoctor.service.StepCountService;
import com.mydoctor.service.UserCheckListService;
import com.mydoctor.service.WeightService;

import antlr.ParserSharedInputState;

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
	@Autowired
	AdviceService adviceService;
	@Autowired
	SleepingTimeService sleepingTimeService;
	@Autowired
	AnalysisDataService analysisDataService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String dashboard(Model model) {

		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		BloodPressure bloodPressure = this.bloodPressureService.getRecentBloodPressure(userId);
		HeartRate heartRate = this.heartRateService.getRecentHeartRate(userId);
		StepCount stepCount = this.stepCountService.getRecentStepCount(userId);
		BloodSugar bloodSugar = this.bloodSugarService.getRecentBloodSugar(userId);
		Weight weight = this.weightService.getRecentWeight(userId);
		SleepingTime sleepingTime = this.sleepingTimeService.getRecentSleepingTime(userId);
		UserCheckList curCheckList = userCheckListService.findById(userId);
		List<Advice> adviceList = this.adviceService.getAdvice(userId);

		model.addAttribute("heartRate", heartRate);
		model.addAttribute("bloodPressure", bloodPressure);
		model.addAttribute("bloodSugar", bloodSugar);
		model.addAttribute("stepCount", stepCount);
		model.addAttribute("height", curCheckList.getHeight());
		model.addAttribute("weight", curCheckList.getWeight());
		model.addAttribute("sleepingTime", sleepingTime);
		model.addAttribute("advices", adviceList);

		AnalysisData analysisData = this.analysisDataService.getAnalysisDataByUsername(userId);
		model.addAttribute("analysisData", analysisData);
		System.out.println(analysisData);

		String BPpoint = this.getDangerPointByBP(bloodPressure);
		String BSpoint = this.getDangerPointByBS(bloodSugar);
		String SCpoint = this.getAdviceByStepCount(stepCount);
		System.out.println(BSpoint + "위험지수는~~~~~~" + BPpoint);
		model.addAttribute("BPpoint", BPpoint);
		model.addAttribute("BSpoint", BSpoint);
		model.addAttribute("SCpoint", SCpoint);

		return "dashboard";
	}

	public String getDangerPointByBP(BloodPressure bloodPressure) {
		if (bloodPressure == null) {
			return "0";
		} else {
			int curHP = Integer.parseInt(bloodPressure.getHP());
			int curHR = Integer.parseInt(bloodPressure.getHR());

			// int curSugar = Integer.parseInt(bloodSugar.getBG());

			if (curHP < 120 && curHR < 80) {

				return "4";
			} else if ((curHP >= 120 && curHP <= 139) || (curHR >= 80 && curHR <= 89)) {
				if ((curHP >= 120 && curHP <= 129) || (curHR >= 80 && curHR <= 84))
					return "3-1";
				else
					return "3-2";
			} else if ((curHP >= 140 && curHP <= 160) || (curHR >= 90 && curHR <= 100)) {
				if ((curHP >= 140 && curHP <= 159) || (curHR >= 90 && curHR <= 99))
					return "2-1";
				else
					return "2-2";
			} else
				return "1";
		}
	}

	public String getDangerPointByBS(BloodSugar bloodSugar) {
		if (bloodSugar == null) {
			return "0";
		} else {
			int curBS = Integer.parseInt(bloodSugar.getBG());

			if (curBS >= 125) {
				return "3";

			}

			else if (curBS >= 100 && curBS <= 124) {
				return "2";
			}

			else
				return "1";
		}
	}

	public String getAdviceByStepCount(StepCount stepCount) {
		if (stepCount == null) {
			return "0";
		} else {
			int curSC = stepCount.getStepCount();
			
				if (curSC <= 5000) {
					return "1";
				}
				else if(curSC <= 7500) {
					return "2";
				}
				else if(curSC <= 10000) {
					return "3";
				}
				else 
					return "4";
			}
	}

	@RequestMapping(value = "/chooseDoctor", method = RequestMethod.GET)
	public String chooseDoctor(@RequestParam(value = "error", required = false) String error, Model model) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();

		AssignedUser assignedUser = this.chooseDoctorService.getAssignedUserById(userId);

		if (assignedUser == null) {
			assignedUser = new AssignedUser();
			assignedUser.setUsername(userId);
		}

		model.addAttribute("assignedUser", assignedUser);
		if (error != null) {
			if (error.equals("1"))
				model.addAttribute("error", "의사를 입력해주세요.");
			if (error.equals("2"))
				model.addAttribute("error", "등록되지 않은 의사입니다.");
		}
		return "chooseDoctor";
	}

	@RequestMapping(value = "/chooseDoctor", method = RequestMethod.POST)
	public String chooseDoctorPost(@Valid AssignedUser assignedUser, BindingResult result, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		System.out.println(assignedUser.toString());

		if (assignedUser.getDoctorname().equals("")) {

			if (!(this.chooseDoctorService.deleteDoctor(assignedUser.getUsername()))) {
				redirectAttributes.addAttribute("error", "1");
				// assignedUser.setDoctorname(null);

			}
		} else {
			if (!(this.chooseDoctorService.checkDoctor(assignedUser.getDoctorname()))) {
				redirectAttributes.addAttribute("error", "2");
				// assignedUser.setDoctorname(null);
			} else
				this.chooseDoctorService.addDoctor(assignedUser);
		}

		return "redirect:/chooseDoctor";
	}

	@RequestMapping(value = "/advice")
	public String advice(Model model, HttpServletRequest request,
			@RequestParam(value = "username", required = false) String username) {

		String userId = username;
		System.out.println(userId);

		if (username == null) {
			userId = SecurityContextHolder.getContext().getAuthentication().getName();
		}

		List<Advice> adviceList = this.adviceService.getAdvice(userId);
		AnalysisData analysisData = this.analysisDataService.getAnalysisDataByUsername(userId);

		model.addAttribute("analysisData", analysisData);
		model.addAttribute("advices", adviceList);

		return "webview_advice";
	}

}
