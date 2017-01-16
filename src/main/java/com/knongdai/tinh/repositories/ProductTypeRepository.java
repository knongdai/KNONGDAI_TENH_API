package com.knongdai.tinh.repositories;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.knongdai.tinh.entities.ProductType;
import com.knongdai.tinh.repositories.sqlbuilder.ProductTypeProvider;

@Repository
public interface ProductTypeRepository {
	
	final String select_by_sub_two_category_id = " "
								+" SELECT "
								+"		pt.product_type_pk_id, "
								+"		pt.product_type "
								+" FROM phsar_product_type pt "
								+" INNER JOIN phsar_sub_two_category st "
								+" ON pt.sub_two_fk_id = st.sub_two_pk_id "
								+" WHERE pt.sub_two_fk_id = #{productType.productid}";
	
	final String select_all = "SELECT pt.product_type_pk_id, pt.product_type FROM phsar_product_type pt";
	
	@Results(value={	
			@Result(property="productid", column="product_type_pk_id"),
			@Result(property="productType", column="product_type")
	})
	@Select(select_all)
	public ArrayList<ProductType> getAll();	
	
	
	@Insert("INSERT INTO phsar_product_type(product_type, tags) VALUES(#{productType}, #{tag})")
	public boolean save(ProductType productType);
	
	@Delete("DELETE FROM phsar_product_type WHERE product_type_pk_id = #{id}")
	public boolean remove(int id);
	
	@Update("UPDATE phsar_product_type SET product_type = #{productType}, tags = #{tag} WHERE product_type_pk_id = #{productid}")
	public boolean update(ProductType productType);
	
	@Select("SELECT product_type_pk_id AS \"productid\", product_type AS \"productType\", tags AS \"tag\" FROM phsar_product_type WHERE product_type_pk_id=#{id}")
	public ProductType findOne(int id);
	
	@SelectProvider(method="findAll", type=ProductTypeProvider.class)
	public List<ProductType> findAll(String type);
	
}
