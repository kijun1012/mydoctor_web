package com.mydoctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.AdviceDao;
import com.mydoctor.model.Advice;

@Service
public class AdviceService {
	
	@Autowired
	AdviceDao adviceDao;

	public List<Advice> getAdvice(String userId) {
		return this.adviceDao.getAdvice(userId);
	}

	public void deleteAdvice(int id) {
		this.adviceDao.deleteAdvice(id);
		
	}

}
