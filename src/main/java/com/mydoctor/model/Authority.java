package com.mydoctor.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "authorities")
public class Authority {

	@Id
	private String username;

	@Column(nullable = false)
	private String authority;

	@MapsId("username")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	private User user;

}
