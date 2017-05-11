package com.mydoctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.HeartRateDao;
import com.mydoctor.model.HeartRate;

@Service
public class HeartRateService {
	@Autowired
	private HeartRateDao heartRateDao;

	public List<HeartRate> getHeartRate(String userId) {
		return this.heartRateDao.getHeartRate(userId);

	}

	public HeartRate getRecentHeartRate(String userId) {
		List<HeartRate> heartRateList = this.heartRateDao.getHeartRate(userId);
		if (heartRateList.isEmpty())
			return null;
		else
			return heartRateList.get(heartRateList.size() - 1);

	}

	public void addHeartRate(HeartRate heartRate) {
		this.heartRateDao.addHeartRate(heartRate);
	}
	
public List<HeartRate> getHeartRateByDate(String username, String fromDate, String toDate) {
		
		fromDate = fromDate + " 00:00:00";
		toDate = toDate + " 23:59:59";
		List<HeartRate> data = this.heartRateDao.getHeartRateByDate(username,fromDate,toDate);
		
		return data;
		
	}
}
