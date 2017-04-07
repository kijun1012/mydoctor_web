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

	public List<HeartRate> getHeartRate() {
		return this.heartRateDao.getHeartRates();

	}
}
