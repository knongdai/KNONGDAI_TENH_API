package com.knongdai.tinh.entities;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductType {
	
	@JsonProperty("ID")
	private long productid;
	
	@JsonProperty("SUB_TWO_CATEGORY_ID")
	private SubTwoCategory subTwoCategory;
	
	@JsonProperty("PRODUCT_TYPE")
	private String productType;
	
	@JsonProperty("TAGS")
	private ArrayList<String> tags;

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public SubTwoCategory getSubTwoCategory() {
		return subTwoCategory;
	}

	public void setSubTwoCategory(SubTwoCategory subTwoCategory) {
		this.subTwoCategory = subTwoCategory;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

}
