package com.mydoctor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydoctor.model.BloodPressure;
import com.mydoctor.model.StepCount;

@Repository
@Transactional
public class StepCountDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<StepCount> getStepCounts() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from StepCount");
		List<StepCount> stepCountList = query.list();

		return stepCountList;

	}
}
