package com.services.service;

import com.services.pojo.Login;
import com.services.pojo.User;

public interface UserService {
	
	User validateUser(Login login);
}
