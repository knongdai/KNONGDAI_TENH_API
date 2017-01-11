package com.knongdai.tinh.services;

import java.util.ArrayList;
import java.util.List;

import com.knongdai.tinh.entities.Product;
import com.knongdai.tinh.entities.ProductTemperory;

public interface ProductTmpService {
	
	public ArrayList<ProductTemperory> getALLProductsTemperory();
	public int saveProductTmp(List<ProductTemperory> p);
	public int deleteProductsByStatus(boolean status);
	public ArrayList<ProductTemperory> getProductsTemperoryById(int id);
	public void updateProductById(ArrayList<Product> productListId);
	public ArrayList<ProductTemperory> getProductsByStatus(boolean status);
	
}
