package com.mydoctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.StepCountDao;
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

}
