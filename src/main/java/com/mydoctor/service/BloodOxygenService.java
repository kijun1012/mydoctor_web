package com.mydoctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.BloodOxygenDao;
import com.mydoctor.model.BloodOxygen;
import com.mydoctor.model.HeartRate;

@Service
public class BloodOxygenService {
	@Autowired
	private BloodOxygenDao bloodOxygenDao;

	public List<BloodOxygen> getAllBloodOxygen(String userId) {
		return this.bloodOxygenDao.getBloodOxygen(userId);
	}

	public BloodOxygen getRecentBloodOxygen(String userId) {
		List<BloodOxygen> bloodOxygenList = this.bloodOxygenDao.getBloodOxygen(userId);
		if (bloodOxygenList.isEmpty())
			return null;
		else
			return bloodOxygenList.get(bloodOxygenList.size() - 1);
	}

	public void addBloodOxygen(List<BloodOxygen> boList, String userId) {
		for (int i = 0; i < boList.size(); i++) {
			boList.get(i).setUsername(userId);
		}
		this.bloodOxygenDao.addBloodOxygen(boList);

	}

	public List<BloodOxygen> getBloodOxygenByDate(String username, String fromDate, String toDate) {
		fromDate = fromDate + " 00:00:00";
		toDate = toDate + " 23:59:59";
		List<BloodOxygen> data = this.bloodOxygenDao.getBloodOxygenByDate(username, fromDate, toDate);

		return data;
	}

	public void deleteBloodOxygen(String username, String measurement_time) {
		this.bloodOxygenDao.deleteBloodOxygen(username,measurement_time);
		
		
	}

	public void addBloodOxygen(BloodOxygen bloodOxygen) {
		this.bloodOxygenDao.addBloodOxygen(bloodOxygen);
		
	}
}
