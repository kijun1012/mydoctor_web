package com.mydoctor.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.HeartRate;

@Repository
@Transactional
public class HeartRateDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<HeartRate> getHeartRate(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from HeartRate as hr where hr.username=:username");
		query.setParameter("username", userId);
		@SuppressWarnings("unchecked")
		List<HeartRate> heartRateList = query.list();

		return heartRateList;

	}

	public void addHeartRate(HeartRate heartRate) {
		Session session = sessionFactory.getCurrentSession();
		session.save(heartRate);
		session.flush();
	}

}
