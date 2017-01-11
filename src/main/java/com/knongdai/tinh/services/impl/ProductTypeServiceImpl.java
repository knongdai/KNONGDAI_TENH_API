package com.knongdai.tinh.services.impl;

import java.util.ArrayList;

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

}
