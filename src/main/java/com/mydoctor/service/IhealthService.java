package com.mydoctor.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.mydoctor.vo.IhealthData;

@Service
public class IhealthService {
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<IhealthData> idata;
	
//	public List<IhealthData> saveAccesToken() {
//		
//	}
}
