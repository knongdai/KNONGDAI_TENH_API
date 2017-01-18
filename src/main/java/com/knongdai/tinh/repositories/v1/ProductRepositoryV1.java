package com.knongdai.tinh.repositories.v1;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.knongdai.tinh.entities.Product;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.entities.v1.ProductFilter;
import com.knongdai.tinh.repositories.v1.provider.ProductProviderV1;

@Repository
public interface ProductRepositoryV1 {
	
	@SelectProvider(method = "findAll", type = ProductProviderV1.class)
	@Results(value={
			@Result(property="productType.productid", column="productTypeId"),	
			@Result(property="subtwoCategory.categoryname", column="categoryName"),
			@Result(property="sourceCategory.source.logo", column="sourceLogo"),
			@Result(property="sourceCategory.source.domain", column="sourceDomain")
	})
	List<Product> findAll(@Param("filter") ProductFilter filter, @Param("paging") Pagination paging);
	
	@SelectProvider(method = "countAll", type = ProductProviderV1.class)
	long countAll(ProductFilter filter);
	
	@SelectProvider(method = "findKeywords", type = ProductProviderV1.class)
	List<String> findKeywords(String keyword);
}
