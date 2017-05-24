package com.mydoctor.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.AssignedUser;
import com.mydoctor.model.Authority;

@Repository
@Transactional
public class ChooseDoctorDao {

	@Autowired
	SessionFactory sessionFactory;

	public void addDoctor(AssignedUser assignedUser) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate("AssignedUser", assignedUser);
		session.flush();
		session.clear();

	}

	public AssignedUser getAssignedUserById(String userId) {
		Session session = sessionFactory.getCurrentSession();
		AssignedUser assignedUser = (AssignedUser) session.get(AssignedUser.class, userId);
		session.clear();
		return assignedUser;
	}

	public boolean deleteDoctor(String userId) {
		Session session = sessionFactory.getCurrentSession();
		AssignedUser assignedUser = (AssignedUser) session.get(AssignedUser.class, userId);
		boolean result = false;
		
		if (assignedUser != null) {
			session.delete("AssignedUser", assignedUser);
			session.flush();
			session.clear();
			result = true;
		}

		return result;

	}

	public boolean checkDoctor(String doctorname) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Authority as au where au.username=:username and au.authority =:authority");
		query.setParameter("username", doctorname);
		query.setParameter("authority","ROLE_ADMIN");
		
		@SuppressWarnings("unchecked")
		List<Authority> auList = query.list();
	
		session.clear();
		
		return !(auList.isEmpty());
	}
}
