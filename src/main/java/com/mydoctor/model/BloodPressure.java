package com.mydoctor.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BloodPressure {
	private String username;
	private int date;
	private int systolic_pressure;
	private int diastolic_pressure;
}
