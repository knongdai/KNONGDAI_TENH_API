package com.knongdai.tinh.services.v1;

import java.util.List;

import com.knongdai.tinh.entities.Product;
import com.knongdai.tinh.entities.filter.ProductTempFilter;
import com.knongdai.tinh.entities.util.Pagination;

public interface ProductTempService {
	
	List<Product> findAllTemp(ProductTempFilter filter, Pagination paging);
	boolean save(List<Product> products);
	
}
