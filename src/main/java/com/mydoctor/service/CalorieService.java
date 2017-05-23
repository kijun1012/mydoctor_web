package com.mydoctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.CalorieDao;
import com.mydoctor.model.Calorie;

@Service
public class CalorieService {

	@Autowired
	private CalorieDao calorieDao;
	
	public List<Calorie> getAllCalorie(String userId) {
		
		return this.calorieDao.getAllCalorie(userId);
	}

	public Calorie getRecentCalorie(String userId){
		List<Calorie> calorieList = this.calorieDao.getAllCalorie(userId);
		if(calorieList.isEmpty()){
			return null;
		}
		else
			return calorieList.get(calorieList.size()-1);
	}
	
	public List<Calorie> getCalorieByDate(String username, String fromDate, String toDate) {

		List<Calorie> data = this.calorieDao.getCalorieByDate(username,fromDate,toDate);
	
		return data;
	}

	public void addCalorie(Calorie calorie) {
		this.calorieDao.addCalorie(calorie);
		
	}

	public void deleteCalorie(String username, String measurement_time) {
		this.calorieDao.deleteCalorie(username,measurement_time);
	}

}
