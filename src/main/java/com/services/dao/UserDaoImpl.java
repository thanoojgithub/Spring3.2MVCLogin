package com.services.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.services.pojo.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends AbstractDao implements UserDao {

	@Override
	public User validateUser(User login) {
		User user = (User) getSession().get(User.class, login.getUsername());
		if (user != null && (user.getUsername().equalsIgnoreCase(login.getUsername())
				&& user.getPassword().equalsIgnoreCase(login.getPassword()))) {
			return user;
		} else {
			return null;
		}
	}

}
