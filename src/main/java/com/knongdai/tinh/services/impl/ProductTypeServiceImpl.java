package com.knongdai.tinh.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knongdai.tinh.entities.ProductType;
import com.knongdai.tinh.repositories.ProductTypeRepository;
import com.knongdai.tinh.services.ProductTypeService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService{

	@Autowired
	private ProductTypeRepository ptr;
	
	@Override
	public ArrayList<ProductType> getAll() {
		return ptr.getAll();
	}

	@Override
	public boolean save(ProductType productType) {
		return ptr.save(productType);
	}

	@Override
	public boolean remove(int id) {
		return ptr.remove(id);
	}

	@Override
	public boolean update(ProductType productType) {
		return ptr.update(productType);
	}

	@Override
	public ProductType findOne(int id) {
		return ptr.findOne(id);
	}

	@Override
	public List<ProductType> findAll(String type) {
		return ptr.findAll(type);
	}

}
