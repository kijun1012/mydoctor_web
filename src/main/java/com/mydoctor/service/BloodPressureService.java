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

	public List<BloodPressure> getAllBloodPressure(String userId) {
		return this.bloodPressureDao.getBloodPressure(userId);
	}

	public BloodPressure getRecentBloodPressure(String userId) {
		List<BloodPressure> bloodPressureList = this.bloodPressureDao.getBloodPressure(userId);
		
		if (bloodPressureList.isEmpty())
			return null;
		else
			return bloodPressureList.get(bloodPressureList.size() - 1);
	}

	public List<String> addBloodPressure(List<BloodPressure> bpList, String userId) {
		for (int i = 0; i < bpList.size(); i++) {
			bpList.get(i).setUsername(userId);
		}
		return this.bloodPressureDao.addBloodPressure(bpList);

	}
}
