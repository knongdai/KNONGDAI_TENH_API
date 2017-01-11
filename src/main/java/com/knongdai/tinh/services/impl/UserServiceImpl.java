package com.knongdai.tinh.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knongdai.tinh.entities.User;
import com.knongdai.tinh.repositories.UserRopository;
import com.knongdai.tinh.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRopository userRepository;
	
	@Override
	public int createUser(User users) {
		return userRepository.createUser(users);
	}

	@Override
	public ArrayList<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

	@Override
	public ArrayList<User> getUserById(int userid) {
		return userRepository.getUserById(userid);
	}

	@Override
	public int updateUser(User users) {
		return userRepository.updateUser(users);
	}

	@Override
	public int deleteUserById(int userid) {
		return userRepository.deleteUserById(userid);
	}
	
	@Override
	public ArrayList<User> findUserByName(String username) {
		return userRepository.findUserByName(username);
	}

	@Override
	public int countUserByUserHash(String userHash) {
		return userRepository.countUserByUserHash(userHash);
	}

	@Override
	public User findUserByUserHash(String userHash) {
		return userRepository.findUserByUserHash(userHash);
	}

	
}
