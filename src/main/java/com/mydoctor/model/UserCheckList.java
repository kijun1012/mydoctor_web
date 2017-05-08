package com.mydoctor.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

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
	private int age;
	@Column
	private int height;
	@Column
	private int weight;
	
	@Column
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean issmoke;
	
	@Column
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean isalcohol;
	
	@MapsId("username")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username")
	private User user;

}
