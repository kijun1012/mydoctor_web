package com.mydoctor.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name="userchecklist")
public class UserCheckList {
	
	@Id
	private String username;
	
	@Column
	private String name;
	
	@Column
	private String birth;
	
	@Column
	private int age;
	@Column
	private int height;
	@Column
	private double weight;
	
	
	@Column
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean sex;
	
	@Column
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean issmoke;
	
	@Column
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean isalcohol;
	
	@Column
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean ishighpressure;
	
	@Column
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean isdiabetes;
	
	@Transient
	private int lastHeartrate;
	@Transient
	private int lastBloodoxygen;
	@Transient
	private int lastBloodsugar;
	@Transient
	private int lastStepcount;
	@Transient
	private int lastHP;
	@Transient
	private int lastHR;
	@Transient
	private int lastBloodOxygen;
	@Transient
	private String lastSleepingTime;
	
	@MapsId("username")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	private User user;

}
