package com.mydoctor.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfo {
	private String username;
	private int token;
	private String heartRate;
	private String bloodPressure;
	private String bloodSugar;
	private String stepCount;
	private Boolean isLogin;
}
