package com.mydoctor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.BloodPressure;
import com.mydoctor.model.HeartRate;

@Repository
@Transactional
public class HeartRateDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<HeartRate> getHeartRate(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from HeartRate as hr where hr.username=:username");
		query.setParameter("username",userId);
		List<HeartRate> heartRateList = query.list();
		
		System.out.println("심박수들  " + heartRateList);
		return heartRateList;

	}

	public HeartRate getRecentHeartRate(String userId) {
			return null;
	
	}
}
