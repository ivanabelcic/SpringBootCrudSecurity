package com.met.login.services;

import com.met.login.entities.User;

public interface UserService {
	User findUserByEmail(String email);
	void saveUser(User user);
}
