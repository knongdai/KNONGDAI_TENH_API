package com.knongdai.tinh.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knongdai.tinh.entities.Product;
import com.knongdai.tinh.entities.ProductTemperory;
import com.knongdai.tinh.repositories.ProductTemperoryRepository;
import com.knongdai.tinh.services.ProductTmpService;

@Service
public class ProductTemperoryServiceImpl implements ProductTmpService{

	@Autowired
	private ProductTemperoryRepository ps;
	
	@Override
	public ArrayList<ProductTemperory> getALLProductsTemperory() {
		return ps.getALLProductsTemperory();
	}

	@Override
	public int saveProductTmp(List<ProductTemperory> products) {
		return ps.createProductTemperory(products);
	}

	@Override
	public int deleteProductsByStatus(boolean status) {
		return ps.deleteProductsByStatus(status);
	}
	
	@Override
	public ArrayList<ProductTemperory> getProductsTemperoryById(int id) {
		try{
			return ps.getProductsTemperoryById(id);
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void updateProductById(ArrayList<Product> productListId) {
		try{
			for(Product pid:productListId){
				ps.updateProductById(pid.getProductid());
			}
		}catch(Exception e){	
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<ProductTemperory> getProductsByStatus(boolean status) {
		return ps.getProductsByStatus(status);
	}
}	
