package com.knongdai.tinh.services;

import java.util.ArrayList;
import java.util.List;

import com.knongdai.tinh.entities.ProductType;

public interface ProductTypeService {
	public ArrayList<ProductType> getAll();
	
	public boolean save(ProductType productType);
	
	public boolean remove(int id);
	
	public boolean update(ProductType productType);
	
	public ProductType findOne(int id);
	
	public List<ProductType> findAll(String type);
	
}
