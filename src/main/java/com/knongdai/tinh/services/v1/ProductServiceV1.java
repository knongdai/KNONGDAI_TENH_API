package com.knongdai.tinh.services.v1;

import java.util.List;

import com.knongdai.tinh.entities.Product;
import com.knongdai.tinh.entities.v1.ProductFilter;
import com.phearun.utility.Paging;

public interface ProductServiceV1 {

	List<Product> findAll(ProductFilter filter, Paging paging);
	
}
