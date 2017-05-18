package com.mydoctor.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.mydoctor.module.DataPK;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "bloodpressure")
@IdClass(DataPK.class)
public class BloodPressure {
	// @Id
	// @GeneratedValue
	// private int id;

	private String username;

	@Id
	@Column(name = "date", nullable = false)
	private String measurement_time;

	@Column(nullable = false)
	private String HP; // ³ôÀºÇ÷¾Ð
	@Column(nullable = false)
	private String HR; // ³·ÀºÇ÷¾Ð

	@MapsId("username")
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "username")
	private User user;

	@Transient
	private String BPL;
	@Transient
	private String DataID;
	@Transient
	private String DataSource;

	@Transient
	private String IsArr;
	@Transient
	private String LP;
	@Transient
	private String LastChangeTime;
	@Transient
	private String Lat;
	@Transient
	private String Lon;
	@Transient
	private String MDate;
	@Transient
	private String Note;
	@Transient
	private String TimeZone;

	@Transient
	private String time_zone;

}
