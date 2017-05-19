package com.mydoctor.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.AssignedUser;

@Repository
@Transactional
public class ChooseDoctorDao {

	@Autowired
	SessionFactory sessionFactory;

	public void addDoctor(AssignedUser assignedUser) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate("AssignedUser",assignedUser);
		session.flush();
		session.clear();
		
	}

	public AssignedUser getAssignedUserById(String userId) {
		Session session = sessionFactory.getCurrentSession();
		AssignedUser assignedUser =  (AssignedUser) session.get(AssignedUser.class, userId);
		
		return assignedUser;
	}
}
