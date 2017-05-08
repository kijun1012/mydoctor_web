package com.mydoctor.controller;

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
import com.mydoctor.service.UserCheckListService;

@Controller
@RequestMapping("/checklist")
public class UserCheckListController {
	
	@Autowired
	private UserCheckListService userCheckListService;
	
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
		
		userCheckListService.updateCheckList(currentUserCheckList);
		
		return new ResponseEntity<UserCheckList>(currentUserCheckList, HttpStatus.OK);
		
	}
	

}
