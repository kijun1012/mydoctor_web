package com.mydoctor.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.UserCheckList;

@Repository
@Transactional
public class UserCheckListDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public UserCheckList findById(String id) {
		Session session = sessionFactory.getCurrentSession();
		UserCheckList userCheckList = (UserCheckList)session.get(UserCheckList.class, id);
		
		return userCheckList;
	}

	public void updateCheckList(UserCheckList currentUserCheckList) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(currentUserCheckList);
		session.flush();
	}

}
