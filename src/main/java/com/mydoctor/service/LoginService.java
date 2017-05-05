package com.mydoctor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydoctor.dao.UserDao;
import com.mydoctor.model.User;

@Service
public class LoginService {
	
	@Autowired
	private UserDao userDao;

	public User getUserById(String id, String password) {
		return userDao.getUserById(id, password);
	}

}
