package com.mydoctor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.UserCheckListDao;
import com.mydoctor.model.UserCheckList;

@Service
public class UserCheckListService {

	@Autowired
	UserCheckListDao userCheckListDao;
	
	
	public UserCheckList findById(String id) {
		return userCheckListDao.findById(id);
	}


	public void updateCheckList(UserCheckList currentUserCheckList) {
		userCheckListDao.updateCheckList(currentUserCheckList);
	}

}
