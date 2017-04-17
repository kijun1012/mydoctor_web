package com.mydoctor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
		try {
			return this.jdbcTemplateObject.query(sqlStatement, new RowMapper<BloodPressure>() {

				@Override
				public BloodPressure mapRow(ResultSet res, int rowNum) throws SQLException {
					BloodPressure bloodPressure = new BloodPressure();

					bloodPressure.setUsername(res.getString("username"));
					bloodPressure.setDate(res.getInt("date"));
					bloodPressure.setSystolic_pressure(res.getInt("systolic_pressure"));
					bloodPressure.setDiastolic_pressure(res.getInt("diastolic_pressure"));

					return bloodPressure;
				}

			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public BloodPressure getRecentBloodPressure(String userId) {
		String sqlStatement = "select * from bloodpressure where date = (select max(date) from bloodpressure) && username = ?";
		try {
			return this.jdbcTemplateObject.queryForObject(sqlStatement, new Object[] { userId },
					new RowMapper<BloodPressure>() {

						@Override
						public BloodPressure mapRow(ResultSet res, int rownum) throws SQLException {
							BloodPressure bloodPressure = new BloodPressure();

							bloodPressure.setUsername(res.getString("username"));
							bloodPressure.setDate(res.getInt("date"));
							bloodPressure.setDiastolic_pressure(res.getInt("diastolic_pressure"));
							bloodPressure.setSystolic_pressure(res.getInt("systolic_pressure"));

							return bloodPressure;
						}

					});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
