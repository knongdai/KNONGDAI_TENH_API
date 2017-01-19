package com.knongdai.tinh.repositories.v1.provider;

import org.apache.ibatis.jdbc.SQL;

import com.knongdai.tinh.entities.v1.ProductFilter;

public class ProductProviderV1 {

	public String findKeywords(){
		return new SQL(){{
			SELECT_DISTINCT("tags");
			FROM("phsar_product_type PT");
			INNER_JOIN("phsar_product P ON PT .product_type_pk_id = P .product_type_fk_id");
			WHERE("LOWER (PT.tags) LIKE '%' || LOWER (#{keyword}) || '%'");
		}}.toString();
	}
	
	public String findAll(ProductFilter filter){
		return new SQL(){{
			
			SELECT("p.product_pk_id AS \"productid\", p.product_type_fk_id AS \"productTypeId\", p.title, p.price, p.image, p.images, p.url, p.date_create AS \"datecreate\", p.description, s2.category_name AS \"categoryName\", sc.source_category AS \"sourceCategory\", s.logo AS \"sourceLogo\", s.domain AS \"sourceDomain\"");
			
			if(filter.getUserId() != null){
				SELECT("COALESCE(F.user_fk_id, 0) AS \"userId\"");
			}else{
				SELECT("0 AS \"userId\"");
			}
			
			FROM("phsar_product p");
			INNER_JOIN("phsar_sub_two_category s2 ON p.sub_two_fk_id = s2.sub_two_pk_id");
			INNER_JOIN("phsar_source_category sc ON p.source_category_fk_id = sc.source_category_pk_id");
			INNER_JOIN("phsar_source s ON s.source_pk_id = sc.source_fk_id");
			
			if(filter.getKeyword() != null){
				INNER_JOIN("phsar_product_type pt ON pt.product_type_pk_id = p.product_type_fk_id");
			}
			if (filter.getUserId() != null) {
				LEFT_OUTER_JOIN("phsar_farvorite F ON p.product_pk_id = F.product_fk_id AND F.user_fk_id =#{filter.userId}");
			}
			if(filter.getCategoryId() != null){
				WHERE("p.sub_two_fk_id = #{filter.categoryId}");
			}
			if(filter.getWebsiteId() != null){
				WHERE("sc.source_fk_id = #{filter.websiteId}");
			}
			if(filter.getTitle() != null){
				WHERE("LOWER(p.title) LIKE '%' || LOWER(#{filter.title}) || '%'");
			}
			if(filter.getProductTypeId() != null){
				WHERE("p.product_type_fk_id = #{filter.productTypeId}");
			}
			if(filter.getKeyword() != null){
				WHERE("LOWER(pt.tags) LIKE '%' || LOWER(#{filter.keyword}) || '%'");
			}
			if (filter.getMinPrice() != null) {
				WHERE("p.price >= #{filter.minPrice}");
			}
			if (filter.getMaxPrice() != null) {
				WHERE("p.price <= #{filter.maxPrice}");
			}
			
			WHERE("p.publish = true");
			
			ORDER_BY("p.product_pk_id DESC LIMIT #{paging.limit} OFFSET #{paging.offset}");
			
		}}.toString();
	}
	
	public String countAll(ProductFilter filter){
		return new SQL(){{
			
			SELECT("COUNT(*)");
			
			FROM("phsar_product p");
			
			//INNER_JOIN("phsar_sub_two_category s2 ON p.sub_two_fk_id = s2.sub_two_pk_id");
			//INNER_JOIN("phsar_source_category sc ON p.source_category_fk_id = sc.source_category_pk_id");
			//INNER_JOIN("phsar_source s ON s.source_pk_id = sc.source_fk_id");
			
			if(filter.getKeyword() != null){
				INNER_JOIN("phsar_product_type pt ON pt.product_type_pk_id = p.product_type_fk_id");
			}
			if (filter.getUserId() != null) {
				LEFT_OUTER_JOIN("phsar_farvorite F ON p.product_pk_id = F.product_fk_id AND F.user_fk_id =#{userId}");
			}
			if(filter.getCategoryId() != null){
				WHERE("p.sub_two_fk_id = #{categoryId}");
			}
			if(filter.getWebsiteId() != null){
				WHERE("p.source_category_fk_id = #{websiteId}");
			}
			if(filter.getTitle() != null){
				WHERE("LOWER(p.title) LIKE '%' || LOWER(#{title}) || '%'");
			}
			if(filter.getProductTypeId() != null){
				WHERE("p.product_type_fk_id = #{productTypeId}");
			}
			if(filter.getKeyword() != null){
				WHERE("LOWER(pt.tags) LIKE '%' || LOWER(#{keyword}) || '%'");
			}
			if (filter.getMinPrice() != null) {
				WHERE("p.price >= #{minPrice}");
			}
			if (filter.getMaxPrice() != null) {
				WHERE("p.price <= #{maxPrice}");
			}
			
			WHERE("p.publish = true");
		}}.toString();
	}
}
