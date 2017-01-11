package com.knongdai.tinh.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SourceCategory {
	
	@JsonProperty("ID")
	private int sourcecategoryid;
	
	@JsonProperty("SOURCE_CATEGORY")
	private String sourcecategory;
	
	@JsonProperty("STATUS")
	private boolean status;
	
	@JsonProperty("SOURCE")
	private Source source;
	
	@JsonProperty("SUB_CATEGORY")
	private SubTwoCategory subCategory;

	@JsonProperty("LIST_PRODUCT")
	private List<Product> listsProducts;
	
	@JsonProperty("LIST_PRODUCT_TMP")
	private List<ProductTemperory> listsProductsTmp;
	
	
	public int getSourcecategoryid() {
		return sourcecategoryid;
	}

	public void setSourcecategoryid(int sourcecategoryid) {
		this.sourcecategoryid = sourcecategoryid;
	}

	public String getSourcecategory() {
		return sourcecategory;
	}

	public void setSourcecategory(String sourcecategory) {
		this.sourcecategory = sourcecategory;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public SubTwoCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubTwoCategory subCategory) {
		this.subCategory = subCategory;
	}

	public List<Product> getListsProducts() {
		return listsProducts;
	}

	public void setListsProducts(List<Product> listsProducts) {
		this.listsProducts = listsProducts;
	}

	public List<ProductTemperory> getListsProductsTmp() {
		return listsProductsTmp;
	}

	public void setListsProductsTmp(List<ProductTemperory> listsProductsTmp) {
		this.listsProductsTmp = listsProductsTmp;
	}


	
	
}
