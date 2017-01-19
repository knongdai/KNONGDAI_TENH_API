package com.knongdai.tinh.services.impl.v1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knongdai.tinh.entities.Product;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.entities.v1.ProductFilter;
import com.knongdai.tinh.repositories.v1.ProductRepositoryV1;
import com.knongdai.tinh.services.v1.ProductServiceV1;

@Service
public class ProductServiceImplV1 implements ProductServiceV1{

	@Autowired
	private ProductRepositoryV1 productRepository;
	
	@Override
	public List<Product> findAll(ProductFilter filter, Pagination paging) {
		paging.setTotalCount(productRepository.countAll(filter));
		return productRepository.findAll(filter, paging);
	}

	@Override
	public Set<String> findKeywords(String keyword) {
		Set<String> keywords = new HashSet<>();
		for(String key: productRepository.findKeywords(keyword)){
			for(String kw: key.split(",")){
				keywords.add(kw.trim());
			}
		}
		System.out.println(keywords);
		
		return keywords;
	}

}
