package com.knongdai.tinh.repositories.v1;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.knongdai.tinh.entities.Product;
import com.knongdai.tinh.entities.filter.ProductTempFilter;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.repositories.v1.provider.ProductTempProvider;

@Repository
public interface ProductTempRepository {
	
	@SelectProvider(method = "findAllTemp", type = ProductTempProvider.class)
	@Results({
		@Result(property="productid", column="product_pk_id"),
		@Result(property="title", column="title"),
		@Result(property="price", column="price"),
		@Result(property="image", column="image"),
		@Result(property="url", column="url"),
		@Result(property="subtwoCategory.categoryname", column="category_name"),
		@Result(property="subtwoCategory.subtwocategoryid", column="categoryId"),
		@Result(property="sourceCategory.sourcecategory", column="source_category"),
		@Result(property="sourceCategory.sourcecategoryid", column="sourceId")
	})
	List<Product> findAllTemp(@Param("filter") ProductTempFilter filter, @Param("paging") Pagination paging);
	
	@SelectProvider(method = "countAllTemp", type = ProductTempProvider.class)
	Long countAllTemp(ProductTempFilter filter);
	
	@Update(ProductTempProvider.UPDATE_ALL)
	boolean saveAllTemp(@Param("products") List<Product> products);
	
}
