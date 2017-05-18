package com.mydoctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.StepCountDao;
import com.mydoctor.model.HeartRate;
import com.mydoctor.model.StepCount;

@Service
public class StepCountService {

	@Autowired
	private StepCountDao stepCountDao;

	public List<StepCount> getStepCount(String userId) {
		return this.stepCountDao.getStepCounts(userId);
	}

	public StepCount getRecentStepCount(String userId) {
		List<StepCount> stepCountList = this.stepCountDao.getStepCounts(userId);
		if (stepCountList.isEmpty())
			return null;
		else
			return stepCountList.get(stepCountList.size() - 1);

	}

	public void addStepCount(StepCount stepCount) {
		this.stepCountDao.addStepCount(stepCount);
	}

	public List<StepCount> getStepCountByDate(String username, String fromDate, String toDate) {
//		fromDate = fromDate + " 00:00:00";
//		toDate = toDate + " 23:59:59";
		List<StepCount> data = this.stepCountDao.getStepCountByDate(username,fromDate,toDate);
		
		return data;
	}

	public void deleteStepCount(String username, String measurement_time) {
		this.stepCountDao.deleteStepCount(username,measurement_time);
		
	}

}
