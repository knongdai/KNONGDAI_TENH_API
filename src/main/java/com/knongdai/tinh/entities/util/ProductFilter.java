package com.knongdai.tinh.entities.util;

public class ProductFilter {
	private int categoryid;
	private String productprice;
	private int domainid;
	
	
	public ProductFilter() {
		categoryid = 0;
		productprice = "";
		domainid = 0;
	}
	public ProductFilter(int categoryid, String productprice, int domainid) {
		super();
		this.categoryid = categoryid;
		this.productprice = productprice;
		this.domainid = domainid;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getProductprice() {
		return productprice;
	}
	public void setProductprice(String productprice) {
		this.productprice = productprice;
	}
	public int getDomainid() {
		return domainid;
	}
	public void setDomainid(int domainid) {
		this.domainid = domainid;
	}
	
	@Override
	public String toString() {
		return "ProductFilter [categoryid=" + categoryid + ", productprice=" + productprice + ", domainid=" + domainid  + "]";
	}
	
	
}
