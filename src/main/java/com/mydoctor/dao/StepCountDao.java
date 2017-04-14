package com.mydoctor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mydoctor.model.StepCount;

@Repository
public class StepCountDao {
	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public List<StepCount> getStepCounts() {
		String sqlStatement = "select * from stepcount";
		return this.jdbcTemplateObject.query(sqlStatement, new RowMapper<StepCount>() {

			@Override
			public StepCount mapRow(ResultSet res, int rowNum) throws SQLException {
				StepCount stepCount = new StepCount();

				stepCount.setUsername(res.getString("username"));
				stepCount.setDate(res.getInt("date"));
				stepCount.setStepCount(res.getInt("stepcount"));
				return stepCount;
			}

		});
	}
}
