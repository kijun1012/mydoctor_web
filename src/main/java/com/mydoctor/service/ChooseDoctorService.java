package com.mydoctor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.ChooseDoctorDao;
import com.mydoctor.model.AssignedUser;

@Service
public class ChooseDoctorService {

	@Autowired
	ChooseDoctorDao chooseDoctorDao;
	
	public void addDoctor(AssignedUser assignedUser) {
		this.chooseDoctorDao.addDoctor(assignedUser);
		
	}

	public AssignedUser getAssignedUserById(String userId) {
		return this.chooseDoctorDao.getAssignedUserById(userId);
		
	}

	
}
