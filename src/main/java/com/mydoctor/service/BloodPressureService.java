package com.mydoctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.BloodPressureDao;
import com.mydoctor.model.BloodPressure;
import com.mydoctor.model.HeartRate;

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

	public List<BloodPressure> getBloodPressureByDate(String username, String fromDate, String toDate) {
		
		
		fromDate = fromDate + " 00:00:00";
		toDate = toDate + " 23:59:59";
		List<BloodPressure> data = this.bloodPressureDao.getBloodPressureByDate(username, fromDate, toDate);

		return data;
	}

	public void deletebloodPressure(String username, String measurement_time) {
		this.bloodPressureDao.deletebloodPressure(username,measurement_time);
		
	}

	public void addBloodPressure(BloodPressure bloodPressure) {
		this.bloodPressureDao.addBloodPressure(bloodPressure);
		
	}
}
