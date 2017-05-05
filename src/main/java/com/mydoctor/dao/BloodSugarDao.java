package com.mydoctor.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.BloodSugar;

@Repository
@Transactional
public class BloodSugarDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<BloodSugar> getBloodSugar(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from BloodSugar as bs where bs.username=:username");
		query.setParameter("username", userId);
		List<BloodSugar> bloodSugarList = query.list();

		return bloodSugarList;

	}

	public void addBloodSugar(BloodSugar bloodSugar) {
		Session session = sessionFactory.getCurrentSession();
		session.save(bloodSugar);
		session.flush();
	}
}
