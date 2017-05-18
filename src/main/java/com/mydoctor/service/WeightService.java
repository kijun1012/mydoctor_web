package com.mydoctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.WeightDao;
import com.mydoctor.model.HeartRate;
import com.mydoctor.model.Weight;

@Service
public class WeightService {

	@Autowired
	private WeightDao weightDao;

	public List<Weight> getAllWeight(String userId) {
		return this.weightDao.getAllWeight(userId);
	}

	public Weight getRecentWeight(String userId) {
		List<Weight> weightList = this.weightDao.getAllWeight(userId);

		if (weightList.isEmpty())
			return null;
		else
			return weightList.get(weightList.size() - 1);
	}
	
	public List<String> addWeightByList(List<Weight> weightList, String userId){
		for (int i = 0; i < weightList.size(); i++) {
			weightList.get(i).setUsername(userId);
		}
		return this.weightDao.addWeightByList(weightList,userId);
	}
	
	public void addWeight(Weight weight) {
		this.weightDao.addWeight(weight);
	}

	public List<Weight> getWeightByDate(String username, String fromDate, String toDate) {
		fromDate = fromDate + " 00:00:00";
		toDate = toDate + " 23:59:59";
		List<Weight> data = this.weightDao.getWeightByDate(username, fromDate, toDate);

		return data;
	}

	public void deleteWeight(String username, String measurement_time) {
		// TODO Auto-generated method stub
		this.weightDao.deleteWeight(username,measurement_time);
	}

}
