package com.mydoctor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(name = "username")
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private int enabled;

}
