package com.mydoctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.BloodPressureDao;
import com.mydoctor.model.BloodPressure;

@Service
public class BloodPressureService {
	@Autowired
	private BloodPressureDao bloodPressureDao;

	public List<BloodPressure> getAllBloodPressure() {
		return this.bloodPressureDao.getBloodPressure();
	}

	public BloodPressure getRecentBloodPressure(String userId) {
		// TODO Auto-generated method stub
		return this.bloodPressureDao.getRecentBloodPressure(userId);
	}
}
