package com.knongdai.tinh.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductTemperory {
	
	@JsonProperty("PRODUCT_TYPE_ID")
	private ProductType productType;
	
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	
	@JsonProperty("ID")
	private long productid;

	@JsonProperty("TITLE")
	private String title;
	
	@JsonProperty("PRICE")
	private double price;
	
	@JsonProperty("IMAGE")
	private String image;
	
	@JsonProperty("IMAGES")
	private String images;
	
	@JsonProperty("IMAGESS")
	private List<String> imagess;
	
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	
	public List<String> getImagess(){
		String[] slitedImg = images.split("\\s+");
		ArrayList<String> listImg = new ArrayList<>();
		for(int i = 0; i < slitedImg.length; i++){
			listImg.add(slitedImg[i]);
		}
		return listImg; 
	}
	@JsonProperty("URL")
	private String url;
	
	@JsonProperty("DESCRIPTION")
	private String description;
	
	
	@JsonProperty("DATE")
	private String datecreate;
	
	@JsonProperty("STATUS")
	private boolean status;
	
	@JsonProperty("CATEGORY")
	private SubTwoCategory subTwoCategory;
	
	@JsonProperty("SOURCE")
	private SourceCategory sourceCategory;

	public SourceCategory getSourceCategory() {
		return sourceCategory;
	}

	public void setSourceCategory(SourceCategory sourceCategory) {
		this.sourceCategory = sourceCategory;
	}

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public SubTwoCategory getSubTwoCategory() {
		return subTwoCategory;
	}

	public void setSubTwoCategory(SubTwoCategory subTwoCategory) {
		this.subTwoCategory = subTwoCategory;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}	
	
}
