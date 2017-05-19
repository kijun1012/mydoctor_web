package com.mydoctor.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.BloodPressure;
import com.mydoctor.model.HeartRate;
import com.mydoctor.module.DataPK;

@Repository
@Transactional
public class BloodPressureDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<String> addBloodPressure(List<BloodPressure> bpList) {
		
		Session session = sessionFactory.getCurrentSession();
		List<String> status = new ArrayList<String>();
		int lastCount = this.getBloodPressure(bpList.get(0).getUsername()).size();
		
		
		for (int i = 0; i < bpList.size(); i++) {
			
			session.saveOrUpdate("BloodPressure",bpList.get(i));
		}
		
		session.flush();
		session.clear();
		
		int allDataCount = this.getBloodPressure(bpList.get(0).getUsername()).size();
		
		status.add(Integer.toString(allDataCount));
		status.add(Integer.toString(allDataCount-lastCount));
		status.add(Integer.toString(lastCount));
		return status;
	}

	@SuppressWarnings("unchecked")
	public List<BloodPressure> getBloodPressure(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from BloodPressure as bp where bp.username=:username");
		query.setParameter("username", userId);
		List<BloodPressure> bloodPressureList = query.list();

		
		session.clear();
		System.out.println(userId);
		return bloodPressureList;

	}

	public List<BloodPressure> getBloodPressureByDate(String username, String fromDate, String toDate) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from BloodPressure as bp where bp.username=:username and bp.measurement_time between :fromDate and :toDate";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);

		@SuppressWarnings("unchecked")
		List<BloodPressure> bloodPressureList = query.list();
		session.clear();
		
		return bloodPressureList;
	}

	public void deletebloodPressure(String username, String measurement_time) {
		
		DataPK pk = new DataPK(username,measurement_time);
		Session session = sessionFactory.getCurrentSession();
		BloodPressure bloodPressure = session.get(BloodPressure.class,pk);
		
		System.out.println(bloodPressure.toString());
		
		session.delete(bloodPressure);
		session.flush();
		session.clear();
		
	}

	public void addBloodPressure(BloodPressure bloodPressure) {
		Session session = sessionFactory.getCurrentSession();
		session.save("BloodPressure",bloodPressure);
		session.flush();
		session.clear();
		
	}
}
