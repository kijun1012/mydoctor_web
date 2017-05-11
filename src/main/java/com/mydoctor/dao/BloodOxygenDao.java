package com.mydoctor.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.BloodOxygen;

@Repository
@Transactional
public class BloodOxygenDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addBloodOxygen(List<BloodOxygen> boList) {
		Session session = sessionFactory.getCurrentSession();
		for (int i = 0; i < boList.size(); i++) {
			session.save(boList.get(i));
		}

		session.flush();
	}

	@SuppressWarnings("unchecked")
	public List<BloodOxygen> getBloodOxygen(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from BloodOxygen as bo where bo.username=:username");
		query.setParameter("username", userId);
		List<BloodOxygen> bloodOxygenList = query.list();

		return bloodOxygenList;

	}
}
