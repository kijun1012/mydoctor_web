package com.mydoctor.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.StepCount;

@Repository
@Transactional
public class StepCountDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<StepCount> getStepCounts(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from StepCount as sc where sc.username=:username");
		query.setParameter("username", userId);
		List<StepCount> stepCountList = query.list();

		return stepCountList;

	}

	public void addStepCount(StepCount stepCount) {
		Session session = sessionFactory.getCurrentSession();
		session.save(stepCount);
		session.flush();
	}
}
