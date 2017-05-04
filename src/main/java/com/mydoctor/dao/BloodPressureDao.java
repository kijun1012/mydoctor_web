package com.mydoctor.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.BloodPressure;
import com.mydoctor.model.BloodSugar;

@Repository
@Transactional
public class BloodPressureDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addBloodPressure(List<BloodPressure> bpList) {
		Session session = sessionFactory.getCurrentSession();
		for (int i = 0; i < bpList.size(); i++) {
			session.save(bpList.get(i));
		}

		session.flush();
	}

	public List<BloodPressure> getBloodPressure() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from BloodPressure");
		List<BloodPressure> bloodPressureList = query.list();

		return bloodPressureList;

	}
}
