package com.mydoctor.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.Advice;

@Repository
@Transactional
public class AdviceDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Advice> getAdvice(String userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Advice as a where a.username =:username");
		query.setParameter("username", userId);
		
	
		
		List<Advice> adviceList = query.list();
		
		
		return adviceList;
	}

	public void deleteAdvice(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Advice advice = session.get(Advice.class, id);
		
		session.delete(advice);
		
		session.flush();
		session.clear();
		
	}

}
