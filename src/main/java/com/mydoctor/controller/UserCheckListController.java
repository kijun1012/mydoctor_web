package com.mydoctor.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mydoctor.exception.UserNotFoundException;
import com.mydoctor.model.UserCheckList;
import com.mydoctor.model.Weight;
import com.mydoctor.service.UserCheckListService;
import com.mydoctor.service.WeightService;

@Controller
@RequestMapping("/checklist")
public class UserCheckListController {

	@Autowired
	private UserCheckListService userCheckListService;

	@Autowired
	private WeightService weightService;

	@ResponseBody
	@RequestMapping(value = "/set", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<UserCheckList> udpateCheckList(@RequestBody UserCheckList checkList) {

		System.out.println(checkList);

		UserCheckList currentUserCheckList = userCheckListService.findById(checkList.getUsername());

		if (currentUserCheckList == null) {
			throw new UserNotFoundException(checkList.getUsername());
		}

		else {
			// currentUserCheckList.setAge(checkList.getAge());
			// currentUserCheckList.setHeight(checkList.getHeight());
			// currentUserCheckList.setIsalcohol(checkList.getIsalcohol());
			// currentUserCheckList.setIssmoke(checkList.getIssmoke());
			// currentUserCheckList.setWeight(checkList.getWeight());
			// currentUserCheckList.setSex(checkList.getSex());

			currentUserCheckList = checkList;
			try {
				currentUserCheckList.setAge(checkAge(checkList.getBirth()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			userCheckListService.updateCheckList(currentUserCheckList);

			addWeight(checkList);
			// // checklist/set 요청 올때 weight을 테이블에 추가
			// Weight weight = new Weight();
			// weight.setUsername(checkList.getUsername());
			// Date date = new Date();
			// SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd
			// HH:mm:ss");
			// String newdate = format1.format(date);
			// weight.setMeasurement_time(newdate);
			// weight.setWeightValue(checkList.getWeight());
			//
			// weightService.addWeight(weight);

		}
		return new ResponseEntity<UserCheckList>(currentUserCheckList, HttpStatus.OK);

	}

	private void addWeight(UserCheckList checkList) {
		// checklist/set 요청 올때 weight을 테이블에 추가
		Weight weight = new Weight();
		weight.setUsername(checkList.getUsername());
		Date date = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String newdate = format1.format(date);
		weight.setMeasurement_time(newdate);
		weight.setWeightValue(checkList.getWeight());

		weightService.addWeight(weight);
	}

	private int checkAge(String birth) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = format.parse(birth);

		GregorianCalendar today = new GregorianCalendar();
		GregorianCalendar birthDay = new GregorianCalendar();
		birthDay.setTime(birthDate);

		int age = 0;

		if (today.get(Calendar.DAY_OF_YEAR) < birthDay.get(Calendar.DAY_OF_YEAR)) {
			age = -1;
		}
		return today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR) + age;

	}

}
