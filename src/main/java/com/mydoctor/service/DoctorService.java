package com.mydoctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.DoctorDao;
import com.mydoctor.model.AssignedUser;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorDao doctorDao;

	public List<AssignedUser> getAssignedUser(String doctorId) {
		// TODO Auto-generated method stub
		return this.doctorDao.getAssignedUser(doctorId);
	}
}
