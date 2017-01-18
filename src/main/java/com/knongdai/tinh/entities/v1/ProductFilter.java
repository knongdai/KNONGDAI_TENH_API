package com.knongdai.tinh.entities.v1;

public class ProductFilter {

	private Integer categoryId;
	private Integer websiteId;
	private String keyword;
	private Double minPrice;
	private Double maxPrice;
	private String title;
	private Integer userId;
	private Integer productTypeId;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getWebsiteId() {
		return websiteId;
	}
	public void setWebsiteId(Integer websiteId) {
		this.websiteId = websiteId;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}
	@Override
	public String toString() {
		return "ProductFilter [categoryId=" + categoryId + ", websiteId=" + websiteId + ", keyword=" + keyword
				+ ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", title=" + title + ", userId=" + userId
				+ ", productTypeId=" + productTypeId + "]";
	}
	
}
