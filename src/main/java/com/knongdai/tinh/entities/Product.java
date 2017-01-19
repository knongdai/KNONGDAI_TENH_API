package com.knongdai.tinh.entities;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
	
	@JsonProperty("ID")
	private long productid;
	
	@JsonProperty("PRODUCT_TYPE_ID")
	private ProductType productType;
	
	@JsonProperty("TITLE")
	private String title;
	
	@JsonProperty("PRICE")
	private double price;
	
	@JsonProperty("IMAGES")
	private ArrayList<String> lstImg;
	
	@JsonIgnore
	private String image;
	
	@JsonProperty("IMAGE")
	private String img;
	
	public String getImg() {
		return image;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	@JsonIgnore
	private String images;
	
	@JsonProperty("URL")
	private String url;
	
	@JsonProperty("DESCRIPTION")
	private String description;
	
	@JsonProperty("DATE")
	private String datecreate;
	
	@JsonProperty("FAVOURITE")
	private int userId;
	
	@JsonProperty("CATEGORY")
	private SubTwoCategory subtwoCategory;
	
	@JsonProperty("SOURCE")
	private SourceCategory sourceCategory;
	
	public long getProductid() {
		return productid;
	}
	public void setProductid(long productid) {
		this.productid = productid;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public SubTwoCategory getSubtwoCategory() {
		return subtwoCategory;
	}
	public void setSubtwoCategory(SubTwoCategory subtwoCategory) {
		this.subtwoCategory = subtwoCategory;
	}
	public SourceCategory getSourceCategory() {
		return sourceCategory;
	}
	public void setSourceCategory(SourceCategory sourceCategory) {
		this.sourceCategory = sourceCategory;
	}
	public String getTitle() {
		return title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		System.out.println("image:" + image);
		this.image = image;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public ArrayList<String> getLstImg() {
		lstImg = new ArrayList<>();
		if(this.images!=null){
			String[] str = this.images.split(" ");
			for(String s: str){
				this.lstImg.add(s);
			}
		}
		return lstImg;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDatecreate() {
		return datecreate;
	}
	public void setDatecreate(String datecreate) {
		this.datecreate = datecreate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "Product [productid=" + productid + ", productType=" + productType + ", title=" + title + ", price="
				+ price + ", lstImg=" + lstImg + ", image=" + image + ", img=" + img + ", images=" + images + ", url="
				+ url + ", description=" + description + ", datecreate=" + datecreate + ", userId=" + userId
				+ ", subtwoCategory=" + subtwoCategory + ", sourceCategory=" + sourceCategory + "]";
	}	
	
}
