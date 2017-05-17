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
import com.mydoctor.model.BloodSugar;
import com.mydoctor.model.HeartRate;
import com.mydoctor.module.DataPK;

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

		
		session.clear();
		
		return bloodSugarList;

	}

	public void addBloodSugar(BloodSugar bloodSugar) {
		Session session = sessionFactory.getCurrentSession();
		session.save(bloodSugar);
		session.flush();
	}

	public List<String> addBloodSugar(List<BloodSugar> bgList) {

		Session session = sessionFactory.getCurrentSession();
		List<String> status = new ArrayList<String>();
		int lastCount = this.getBloodSugar(bgList.get(0).getUsername()).size();

		for (int i = 0; i < bgList.size(); i++) {
			session.saveOrUpdate(bgList.get(i));
		}

		session.flush();
		session.clear();

		int allDataCount = this.getBloodSugar(bgList.get(0).getUsername()).size();

		status.add(Integer.toString(allDataCount));
		status.add(Integer.toString(allDataCount - lastCount));
		status.add(Integer.toString(lastCount));
		return status;

	}
	
	public List<BloodSugar> getBloodSugarByDate(String username, String fromDate, String toDate) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from BloodSugar as hr where hr.username=:username and hr.measurement_time between :fromDate and :toDate";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);

		@SuppressWarnings("unchecked")
		List<BloodSugar> bloodSugarList = query.list();
		session.clear();
		
		return bloodSugarList;

	}
	
	public void deleteBloodSugar(String username, String measurement_time) {
		// TODO Auto-generated method stub
		DataPK pk = new DataPK(username,measurement_time);
		Session session = sessionFactory.getCurrentSession();
		BloodSugar bloodSugar = session.get(BloodSugar.class, pk);
		
		System.out.println(bloodSugar.toString());
		
		session.delete(bloodSugar);
		session.flush();
		session.clear();
		
		
	}

}
