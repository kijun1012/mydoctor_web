package com.mydoctor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.BloodSugar;

@Repository
@Transactional
public class BloodSugarDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<BloodSugar> getBloodSugar() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from BloodSugar");
		List<BloodSugar> bloodSugarList = query.list();

		return bloodSugarList;

	}
}
