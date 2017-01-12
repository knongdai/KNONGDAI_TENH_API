package com.knongdai.tinh.repositories.v1.provider;

import org.apache.ibatis.jdbc.SQL;

import com.knongdai.tinh.entities.filter.ProductTempFilter;

public class ProductTempProvider {
	
	public static final String UPDATE_ALL = 
							"<script>"
								+ "<foreach collection='products' item='product' separator=';'> "
									+ "	UPDATE phsar_product SET	"
									+ "		sub_two_fk_id = #{product.subtwoCategory.subtwocategoryid}::integer, "
									+ "		product_type_fk_id = #{product.productType.productid}::integer,"
									+ "		publish = true"
									+ "	WHERE"
									+ "		product_pk_id = #{product.productid}"
								+ "</foreach>"
							+"</script>";
	
	public String findAllTemp(ProductTempFilter filter) {
		return new SQL() {{	
			SELECT("p.product_pk_id, p.title, p.price, p.image, p.url, s.category_name, sc.source_category, s.sub_two_pk_id AS \"categoryId\", sc.source_category_pk_id AS \"sourceId\"");
			FROM("phsar_product p");
			INNER_JOIN("phsar_sub_two_category s ON p.sub_two_fk_id = s.sub_two_pk_id");
			INNER_JOIN("phsar_source_category sc ON p.source_category_fk_id = sc.source_category_pk_id");
			
			if(filter.getTitle()!=null)
				WHERE("p.title = #{filter.title}");
			
			if(filter.getCategoryId()!=null)
				WHERE("p.sub_two_fk_id = #{filter.categoryId}");
			
			WHERE("p.publish = false");
			
			ORDER_BY("p.product_pk_id DESC LIMIT #{paging.limit} OFFSET #{paging.offset}");
			
		}}.toString();
	}

	public String countAllTemp(ProductTempFilter filter) {
		return new SQL() {{
			SELECT("COUNT(*)");
			FROM("phsar_product");
			if(filter.getTitle()!=null)
				WHERE("title = #{title}");
			
			if(filter.getCategoryId()!=null)
				WHERE("sub_two_fk_id = #{categoryId}");
			
			WHERE("publish = false");
		}}.toString();
	}
}
