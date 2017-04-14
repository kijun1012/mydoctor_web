package com.mydoctor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mydoctor.model.BloodPressure;

@Repository
public class BloodPressureDao {

	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public List<BloodPressure> getBloodPressure() {
		String sqlStatement = "select * from bloodpressure";
		return this.jdbcTemplateObject.query(sqlStatement, new RowMapper<BloodPressure>(){

			@Override
			public BloodPressure mapRow(ResultSet res, int rowNum) throws SQLException {
				BloodPressure bloodPressure = new BloodPressure();
				
				bloodPressure.setDate(res.getInt("date"));
				bloodPressure.setSystolic_pressure(res.getInt("systolic_pressure"));
				bloodPressure.setDiastolic_pressure(res.getInt("diastolic_pressure"));
				
				return bloodPressure;
			}
			
		});
	}
}
