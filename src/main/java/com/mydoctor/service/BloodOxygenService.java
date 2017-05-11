package com.mydoctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.BloodOxygenDao;
import com.mydoctor.model.BloodOxygen;

@Service
public class BloodOxygenService {
	@Autowired
	private BloodOxygenDao bloodOxygenDao;

	public List<BloodOxygen> getAllBloodOxygen(String userId) {
		return this.bloodOxygenDao.getBloodOxygen(userId);
	}

	public BloodOxygen getRecentBloodPressure(String userId) {
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
}
