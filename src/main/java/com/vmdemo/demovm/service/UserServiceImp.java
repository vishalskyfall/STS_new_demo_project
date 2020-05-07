package com.vmdemo.demovm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmdemo.demovm.model.uservo;
import com.vmdemo.demovm.repository.UserRepository;

@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	UserRepository userRepository;

	public void createUser(uservo user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

	public List<uservo> getUser() { // TODO Auto-generated method stub
		return (List<uservo>) userRepository.findAll();
	}

	public Optional<uservo> findById(int id) { // TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	public uservo update(uservo user, int l) { // TODO Auto-generated method stub
		return userRepository.save(user);
	}

	public void deleteUserById(uservo id) { // TODO Auto-generated method stub
		userRepository.delete(id);
	}
	/*
	 * public User updatePartially(User user, long id) { // TODO Auto-generated
	 * method stub User usr = findById(id); usr.setCountry(user.getCountry());
	 * return userRepository.save(usr); }
	 */

}
