package com.mydoctor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mydoctor.model.AnalysisData;
import com.mydoctor.service.AnalysisDataService;

@Controller
@RequestMapping("/analysis")
public class AnalysisDataController {
	
	@Autowired
	private AnalysisDataService analysisDataService;
	
	@ResponseBody
	@RequestMapping(value="/{username}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<AnalysisData> getAnalysisDataByUsername(@PathVariable(value="username") String username) {
		
		AnalysisData analysisData = analysisDataService.getAnalysisDataByUsername(username);
		

		return new ResponseEntity<AnalysisData>(analysisData, HttpStatus.OK);
	}

}
