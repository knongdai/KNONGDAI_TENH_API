package com.knongdai.tinh.services.impl.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knongdai.tinh.entities.Product;
import com.knongdai.tinh.entities.v1.ProductFilter;
import com.knongdai.tinh.repositories.v1.ProductRepositoryV1;
import com.knongdai.tinh.services.v1.ProductServiceV1;
import com.phearun.utility.Paging;

@Service
public class ProductServiceImplV1 implements ProductServiceV1{

	@Autowired
	private ProductRepositoryV1 productRepository;
	
	@Override
	public List<Product> findAll(ProductFilter filter, Paging paging) {
		paging.setTotalCount(productRepository.countAll(filter));
		return productRepository.findAll(filter, paging);
	}

}
