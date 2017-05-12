package com.mydoctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.WeightDao;
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
	
	public List<String> addWeight(List<Weight> weightList, String userId){
		for (int i = 0; i < weightList.size(); i++) {
			weightList.get(i).setUsername(userId);
		}
		return this.weightDao.addWeight(weightList,userId);
	}

}
