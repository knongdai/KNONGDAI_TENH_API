package com.knongdai.tinh.entities;

/*
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/

import com.fasterxml.jackson.annotation.JsonProperty;

public class User{
	
	@JsonProperty("ID")
	private int userid;
	
	@JsonProperty("USERNAME")
	private String username;
	
	@JsonProperty("PASSWORD")
	private String password;
	
	@JsonProperty("DESCRIPTION")
	private String description;
	
	@JsonProperty("USER_HASH")
	private String user_hash;
	
	@JsonProperty("USER_IMAGE")
	private String user_image;
	
	@JsonProperty("ROLE")
	private String role;
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUser_hash() {
		return user_hash;
	}

	public void setUser_hash(String user_hash) {
		this.user_hash = user_hash;
	}

	public String getUser_image() {
		return user_image;
	}

	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}

	
	
}
