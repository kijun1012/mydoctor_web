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
	
	public List<StepCount> getStepCount() {
		// TODO Auto-generated method stub
		return this.stepCountDao.getStepCounts();
	}

}
