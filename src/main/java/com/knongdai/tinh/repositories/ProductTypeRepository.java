package com.knongdai.tinh.repositories;

import java.util.ArrayList;
import java.util.Properties;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.knongdai.tinh.entities.ProductType;

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
}
