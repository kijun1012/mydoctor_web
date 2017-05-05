package com.mydoctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.BloodSugarDao;
import com.mydoctor.model.BloodSugar;
import com.mydoctor.model.HeartRate;

@Service
public class BloodSugarService {
	@Autowired
	private BloodSugarDao bloodSugarDao;

	public List<BloodSugar> getBloodSugar(String userId) {
		return this.bloodSugarDao.getBloodSugar(userId);
	}

	public BloodSugar getRecentBloodSugar(String userId) {
		List<BloodSugar> bloodSugarList = this.bloodSugarDao.getBloodSugar(userId);
		if (bloodSugarList.isEmpty())
			return null;
		else
			return bloodSugarList.get(bloodSugarList.size() - 1);
	}

	public void addBloodSugar(BloodSugar bloodSugar) {
		this.bloodSugarDao.addBloodSugar(bloodSugar);
	}
}
