package com.knongdai.tinh.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class History {
	
	@JsonProperty("ID")
	private long historyid;
	
	@JsonProperty("PRODUCT")
	private Product product;
	
	@JsonProperty("DATE")
	private String datecreate;
	
	@JsonProperty("USER")
	private User user;

	public long getHistoryid() {
		return historyid;
	}

	public void setHistoryid(long historyid) {
		this.historyid = historyid;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
