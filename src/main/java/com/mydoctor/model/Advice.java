package com.mydoctor.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class Advice {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false)
	private String doctorname;

	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String advice;
	

	@MapsId("doctorname")
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "doctorname")
	private User doctor;

	@MapsId("username")
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "username")
	private User user;
}
