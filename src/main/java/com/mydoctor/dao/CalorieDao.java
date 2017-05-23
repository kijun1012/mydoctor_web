package com.mydoctor.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.Calorie;
import com.mydoctor.module.DataPK;

@Repository
@Transactional
public class CalorieDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<Calorie> getAllCalorie(String userId) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Calorie as c where c.username=:username");
		query.setParameter("username", userId);
		@SuppressWarnings("unchecked")
		List<Calorie> calorieList = query.list();

		return calorieList;
	}

	public List<Calorie> getCalorieByDate(String username, String fromDate, String toDate) {
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "from Calorie as c where c.username=:username and c.measurement_time between :fromDate and :toDate";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);

		@SuppressWarnings("unchecked")
		List<Calorie> calorieList = query.list();
		session.clear();
		
		return calorieList;
	}

	public void addCalorie(Calorie calorie) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate("Calorie",calorie);
		session.flush();
		session.clear();
		
	}

	public void deleteCalorie(String username, String measurement_time) {
		DataPK pk = new DataPK(username,measurement_time);
		Session session = sessionFactory.getCurrentSession();
		Calorie calorie = session.get(Calorie.class,pk);
		
		System.out.println(calorie.toString());
		
		session.delete(calorie);
		session.flush();
		session.clear();
		
	}

}
