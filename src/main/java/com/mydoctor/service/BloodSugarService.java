package com.mydoctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.BloodSugarDao;
import com.mydoctor.model.BloodSugar;

@Service
public class BloodSugarService {
	@Autowired
	private BloodSugarDao bloodSugarDao;

	public List<BloodSugar> getBloodSugar() {
		return this.bloodSugarDao.getBloodSugar();
	}
}
