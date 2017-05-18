package com.mydoctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.SleepingTimeDao;
import com.mydoctor.model.SleepingTime;

@Service
public class SleepingTimeService {

	@Autowired
	SleepingTimeDao sleepingTimeDao;
	
	public List<SleepingTime> getSleepingTime(String userId) {
		// TODO Auto-generated method stub
		return this.sleepingTimeDao.getSleepingTime(userId);
	}
	
	public SleepingTime getRecentSleepingTime(String userId) {
		List<SleepingTime> sleepingTimeList = this.sleepingTimeDao.getSleepingTime(userId);
		if (sleepingTimeList.isEmpty())
			return null;
		else
			return sleepingTimeList.get(sleepingTimeList.size() - 1);

	}

	public List<SleepingTime> getSleepingTimeByDate(String username, String fromDate, String toDate) {
		List<SleepingTime> data = this.sleepingTimeDao.getSleepingTimeByDate(username, fromDate, toDate);
		return data;
	}

	public void addSleepingTime(SleepingTime sleepingTime) {
		// TODO Auto-generated method stub
		this.sleepingTimeDao.addSleepingTime(sleepingTime);
		
	}

	public void deleteSleepingTime(String username, String measurement_time) {
		// TODO Auto-generated method stub
		this.sleepingTimeDao.deleteSleepingTime(username,measurement_time);
	}

}
