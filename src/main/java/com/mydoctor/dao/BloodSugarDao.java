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
import com.mydoctor.model.BloodSugar;

@Repository
public class BloodSugarDao {

	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public List<BloodSugar> getBloodSugar() {
		String sqlStatement = "select * from bloodsugar";
		return this.jdbcTemplateObject.query(sqlStatement, new RowMapper<BloodSugar>(){

			@Override
			public BloodSugar mapRow(ResultSet res, int rowNum) throws SQLException {
				BloodSugar bloodSugar = new BloodSugar();
				
				bloodSugar.setUsername(res.getString("username"));
				bloodSugar.setDate(res.getInt("date"));
				bloodSugar.setBloodsugar(res.getInt("bloodsugar"));
				
				return bloodSugar;
			}
			
		});
	}
}
