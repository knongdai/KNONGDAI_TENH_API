package com.knongdai.tinh.services.impl.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knongdai.tinh.entities.Product;
import com.knongdai.tinh.entities.filter.ProductTempFilter;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.repositories.v1.ProductTempRepository;
import com.knongdai.tinh.services.v1.ProductTempService;

@Service
public class ProductTempServiceImpl implements ProductTempService {
	
	@Autowired
	private ProductTempRepository productTempRepository;
	
	@Override
	public List<Product> findAllTemp(ProductTempFilter filter, Pagination paging) {
		paging.setTotalCount(productTempRepository.countAllTemp(filter));
		System.out.println("AA: " + productTempRepository.countAllTemp(filter));
		return productTempRepository.findAllTemp(filter, paging);
	}

	@Override
	public boolean save(List<Product> products) {
		return productTempRepository.saveAllTemp(products);
	}
	
}
