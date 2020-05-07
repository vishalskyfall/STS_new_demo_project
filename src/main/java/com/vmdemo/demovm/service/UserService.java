package com.vmdemo.demovm.service;

import java.util.List;
import java.util.Optional;

import com.vmdemo.demovm.model.uservo;

public interface UserService {
	public void createUser(uservo user);

	public List<uservo> getUser();

	public Optional<uservo> findById(int id);

	public uservo update(uservo user, int l);

	public void deleteUserById(uservo id);
	/*
	 * * *public User updatePartially(User user, long id);
	 */
}
