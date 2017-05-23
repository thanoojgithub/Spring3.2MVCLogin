package com.services.dao;

import com.services.pojo.User;


public interface UserDao {
	
	User validateUser(User login);
}
