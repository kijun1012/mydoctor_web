package com.mydoctor.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IhealthDataListStatus {
	private String dataName;
	private String allData;
	private String newData;
	private String oldData;
}
