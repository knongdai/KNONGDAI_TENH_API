package com.knongdai.tinh.services;

import java.util.ArrayList;

import com.knongdai.tinh.entities.User;

public interface UserService {

	public int createUser(User users);
	
	public ArrayList<User> findUserByName(String username);
	
	public ArrayList<User> getAllUsers();
	
	public ArrayList<User> getUserById(int userid);
	
	public int updateUser(User users);

	public int deleteUserById(int userid);
	
	/* Tola */
	
	public int countUserByUserHash(String userHash);
	
	public User findUserByUserHash(String userHash);
	
	
}
