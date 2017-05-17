package com.mydoctor.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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

		currentUserCheckList.setAge(checkList.getAge());
		currentUserCheckList.setHeight(checkList.getHeight());
		currentUserCheckList.setIsalcohol(checkList.getIsalcohol());
		currentUserCheckList.setIssmoke(checkList.getIssmoke());
		currentUserCheckList.setWeight(checkList.getWeight());
		currentUserCheckList.setSex(checkList.getSex());

		// checklist/set 요청 올때 weight을 테이블에 추가
		Weight weight = new Weight();
		weight.setUsername(checkList.getUsername());
		Date date = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String newdate = format1.format(date);
		weight.setMeasurement_time(newdate);
		weight.setWeightValue(checkList.getWeight());

		weightService.addWeight(weight);

		userCheckListService.updateCheckList(currentUserCheckList);

		return new ResponseEntity<UserCheckList>(currentUserCheckList, HttpStatus.OK);

	}

}
