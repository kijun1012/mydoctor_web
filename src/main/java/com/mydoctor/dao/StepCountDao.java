package com.mydoctor.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.HeartRate;
import com.mydoctor.model.StepCount;
import com.mydoctor.module.DataPK;

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
		session.saveOrUpdate("StepCount",stepCount);
		session.flush();
		session.clear();
	}

	public List<StepCount> getStepCountByDate(String username, String fromDate, String toDate) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from StepCount as sc where sc.username=:username and sc.measurement_time between :fromDate and :toDate";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);

		@SuppressWarnings("unchecked")
		List<StepCount> stepCountList = query.list();
		session.clear();
		
		return stepCountList;
	}
	
	public void deleteStepCount(String username, String measurement_time) {
		DataPK pk = new DataPK(username,measurement_time);
		Session session = sessionFactory.getCurrentSession();
		StepCount stepCount = session.get(StepCount.class,pk);
		
		System.out.println(stepCount.toString());
		
		session.delete(stepCount);
		session.flush();
		session.clear();
		
	}

	
}
