package com.mydoctor.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.BloodOxygen;
import com.mydoctor.model.HeartRate;
import com.mydoctor.module.DataPK;

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

	
	public List<BloodOxygen> getBloodOxygenByDate(String username, String fromDate, String toDate) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from BloodOxygen as bo where bo.username=:username and bo.measurement_time between :fromDate and :toDate";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);

		@SuppressWarnings("unchecked")
		List<BloodOxygen> bloodOxygenList = query.list();
		session.clear();
		
		return bloodOxygenList;
	}
	
	public void deleteBloodOxygen(String username, String measurement_time) {
		// TODO Auto-generated method stub
		DataPK pk = new DataPK(username,measurement_time);
		Session session = sessionFactory.getCurrentSession();
		BloodOxygen bloodOxygen = session.get(BloodOxygen.class,pk);
		
		System.out.println(bloodOxygen.toString());
		
		session.delete(bloodOxygen);
		session.flush();
		session.clear();
		
		
	}

	
}
