package com.springmvc;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	public User validateUser(Login login) {
		User user = null;
		if (login != null && login.getUsername() != null && (login.getUsername().startsWith("S") || login.getUsername().startsWith("s"))) {
			user = new User(login.getUsername(), login.getPassword());
		}
		return user;
	}

}
