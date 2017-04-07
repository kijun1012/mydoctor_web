package com.mydoctor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mydoctor.model.HeartRate;

@Repository
public class HeartRateDao {
	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public List<HeartRate> getHeartRates() {
		String sqlStatement = "select * from heartrate";
		return this.jdbcTemplateObject.query(sqlStatement, new RowMapper<HeartRate>(){

			@Override
			public HeartRate mapRow(ResultSet res, int rowNum) throws SQLException {
				HeartRate heartRate = new HeartRate();
				
				heartRate.setId(res.getInt("id"));
				heartRate.setHeartRate(res.getInt("heartRate"));
				return heartRate;
			}
			
		});
	}
}
