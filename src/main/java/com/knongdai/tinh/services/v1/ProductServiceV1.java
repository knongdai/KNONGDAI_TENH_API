package com.knongdai.tinh.services.v1;

import java.util.Collection;
import java.util.List;

import com.knongdai.tinh.entities.Product;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.entities.v1.ProductFilter;

public interface ProductServiceV1 {

	List<Product> findAll(ProductFilter filter, Pagination paging);
	
	Collection<String> findKeywords(String keyword);
}
