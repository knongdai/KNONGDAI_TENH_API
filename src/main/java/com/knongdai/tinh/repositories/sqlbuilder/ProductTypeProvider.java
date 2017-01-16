package com.knongdai.tinh.repositories.sqlbuilder;

import org.apache.ibatis.jdbc.SQL;

public class ProductTypeProvider {
	
	public String findAll(String type){
		return new SQL(){{
			SELECT("product_type_pk_id AS \"productid\", product_type AS \"productType\", tags AS \"tag\"");
			FROM("phsar_product_type");
			
			if(type!=null){
				WHERE("LOWER(product_type) LIKE '%' || #{type} || '%'");				
			}
			
			ORDER_BY("product_type ASC");
			
		}}.toString();
		
	}
}
