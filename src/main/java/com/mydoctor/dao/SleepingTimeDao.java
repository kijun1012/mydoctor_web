package com.mydoctor.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.HeartRate;
import com.mydoctor.model.SleepingTime;
import com.mydoctor.module.DataPK;

@Repository
@Transactional
public class SleepingTimeDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<SleepingTime> getSleepingTime(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from SleepingTime as st where st.username=:username");
		query.setParameter("username", userId);
		
		@SuppressWarnings("unchecked")
		List<SleepingTime> sleepingTimeList = query.list();
		System.out.println(userId);
		
		return sleepingTimeList;
	}

	public List<SleepingTime> getSleepingTimeByDate(String username, String fromDate, String toDate) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from SleepingTime as st where st.username=:username and st.measurement_time between :fromDate and :toDate";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);

		@SuppressWarnings("unchecked")
		List<SleepingTime> sleepingTimeList = query.list();
		session.clear();
		
		return sleepingTimeList;
	}

	public void addSleepingTime(SleepingTime sleepingTime) {
		Session session = sessionFactory.getCurrentSession();
		session.save("SleepingTime",sleepingTime);
		session.flush();
		session.clear();

	}

	public void deleteSleepingTime(String username, String measurement_time) {
		DataPK pk = new DataPK(username,measurement_time);
		Session session = sessionFactory.getCurrentSession();
		SleepingTime sleepingTime = session.get(SleepingTime.class,pk);
		
		System.out.println(sleepingTime.toString());
		
		session.delete(sleepingTime);
		session.flush();
		session.clear();

	}

}
