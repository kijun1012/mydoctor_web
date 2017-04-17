package com.mydoctor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
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
		try {
			return this.jdbcTemplateObject.query(sqlStatement, new RowMapper<HeartRate>() {

				@Override
				public HeartRate mapRow(ResultSet res, int rowNum) throws SQLException {
					HeartRate heartRate = new HeartRate();

					heartRate.setUsername(res.getString("username"));
					heartRate.setDate(res.getInt("date"));
					heartRate.setHeartRate(res.getInt("heartRate"));
					return heartRate;
				}

			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public HeartRate getRecentHeartRate(String userId) {
		String sqlStatement = "select * from heartrate where date = (select max(date) from heartrate) && username = ?";

		try {
			return this.jdbcTemplateObject.queryForObject(sqlStatement, new Object[] { userId },
					new RowMapper<HeartRate>() {

						@Override
						public HeartRate mapRow(ResultSet res, int rownum) throws SQLException {
							HeartRate heartRate = new HeartRate();

							heartRate.setUsername(res.getString("username"));
							heartRate.setDate(res.getInt("date"));
							heartRate.setHeartRate(res.getInt("heartrate"));

							return heartRate;
						}

					});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
}
