package com.mydoctor.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class AssignedUser {

	@Column(nullable = false)
	private String doctorname;

	@Id
	private String username;

	@MapsId("doctorname")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "doctorname")
	private User doctor;

	@MapsId("username")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	private User user;
}
