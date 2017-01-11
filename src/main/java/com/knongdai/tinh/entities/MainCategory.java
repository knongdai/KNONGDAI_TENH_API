package com.knongdai.tinh.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MainCategory {
	
	@JsonProperty("ID")
	private int maincategoryid;
	
	@JsonProperty("CATEGORY")
	private String categoryname;
	
	@JsonProperty("DATE")
	private String datecreate;
	
	@JsonProperty("SUB_CATEGORY")
	private List<SubOneCategory> listsSubCategory;
	
	public int getMaincategoryid() {
		return maincategoryid;
	}
	public void setMaincategoryid(int maincategoryid) {
		this.maincategoryid = maincategoryid;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public String getDatecreate() {
		return datecreate;
	}
	public void setDatecreate(String datecreate) {
		this.datecreate = datecreate;
	}
	public List<SubOneCategory> getListsSubCategory() {
		return listsSubCategory;
	}
	public void setListsSubCategory(List<SubOneCategory> listsSubCategory) {
		this.listsSubCategory = listsSubCategory;
	}
	
	
	
	
	
	
	
}
