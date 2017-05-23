package com.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.services.dao.UserDao;
import com.services.pojo.Login;
import com.services.pojo.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	public User validateUser(Login login) {
		User user = null;
		if (login != null && login.getUsername() != null) {
			user = new User(login.getUsername(), login.getPassword());
			user = userDao.validateUser(user);
		}
		return user;
	}

}
