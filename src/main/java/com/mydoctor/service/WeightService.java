package com.mydoctor.service;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.UserCheckListDao;
import com.mydoctor.dao.WeightDao;
import com.mydoctor.model.UserCheckList;
import com.mydoctor.model.Weight;

@Service
public class WeightService {

	@Autowired
	private WeightDao weightDao;
	@Autowired
	private UserCheckListDao userCheckListDao;

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

	public List<String> addWeightByList(List<Weight> weightList, String userId) {
		

		for (int i = 0; i < weightList.size(); i++) {
			weightList.get(i).setUsername(userId);
			double weightValue = weightList.get(i).getWeightValue();

			String pattern = "##.##";
			DecimalFormat dformat = new DecimalFormat(pattern);

			weightValue = Double.parseDouble(dformat.format(weightValue));
			weightList.get(i).setWeightValue(weightValue);

		}
		
		

		return this.weightDao.addWeightByList(weightList, userId);
	}

	public void addWeight(Weight weight) {

		// 추가 할 때 유저 체크리스트 불러서 몸무게 업데이트
		UserCheckList userCheckList = this.userCheckListDao.findById(weight.getUsername());
		userCheckList.setWeight(weight.getWeightValue());
		this.userCheckListDao.updateCheckList(userCheckList);

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
		this.weightDao.deleteWeight(username, measurement_time);

		// userCheckList에 최근 몸무게 다시 적용
		Weight weight = this.getRecentWeight(username);
		UserCheckList userCheckList = this.userCheckListDao.findById(username);
		userCheckList.setWeight(weight.getWeightValue());
		this.userCheckListDao.updateCheckList(userCheckList);
		
	}

}
