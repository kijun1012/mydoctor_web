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

}
