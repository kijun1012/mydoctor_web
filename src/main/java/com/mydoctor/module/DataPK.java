package com.mydoctor.module;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataPK implements Serializable {
	protected String username;
	protected String measurement_time;

	public DataPK() {
	}

	public DataPK(String username, String measurement_time) {
		this.username = username;
		this.measurement_time = measurement_time;
	}

	// equals, hashCode
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		DataPK newPK = (DataPK) obj;

		if (this.username.equals(newPK.getUsername()))
			return false;
		if (this.measurement_time.equals(newPK.getMeasurement_time()))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int hsCode;
		hsCode = username.hashCode();
		hsCode = 19 * hsCode + measurement_time.hashCode();
		return hsCode;
	}

}