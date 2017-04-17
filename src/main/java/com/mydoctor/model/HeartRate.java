package com.mydoctor.model;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HeartRate {
	
	private String username;
	
	private Timestamp date;
	
	//private Date date;
	//private int date;
	private int heartRate;
}
