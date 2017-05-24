package com.mydoctor.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "analysis_data")
public class AnalysisData {
	
	@Id
	private String username;
	
	@Column(name="BTH_G")
	private String bth_g;
	
	@Column(name = "SBP")
	private String sbp;
	
	@Column(name = "DBP")
	private String dbp;
	
	@Column(name = "FBS")
	private String fbs;
	
	@Column(name = "SEX")
	private int sex;
	
	@Column(name = "BMI")
	private double bmi;
	
	@Column(name = "Class")
	private String dis;
	
	@MapsId("username")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	@JsonIgnore
	private User user;

}
