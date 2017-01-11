package com.knongdai.tinh.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Farvorite {
	
	@JsonProperty("ID")
	private long farvoriteid;
	
	@JsonProperty("USER")
	private User user;
	
	@JsonProperty("PRODUCT")
	private Product product;
	
	@JsonProperty("DATE")
	private String datecreate;
	
	public long getFarvoriteid() {
		return farvoriteid;
	}
	public void setFarvoriteid(long farvoriteid) {
		this.farvoriteid = farvoriteid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getDatecreate() {
		return datecreate;
	}
	public void setDatecreate(String datecreate) {
		this.datecreate = datecreate;
	}
	
	
	
}
