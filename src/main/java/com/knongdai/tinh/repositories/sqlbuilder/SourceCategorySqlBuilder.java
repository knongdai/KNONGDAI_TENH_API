package com.knongdai.tinh.repositories.sqlbuilder;

import org.springframework.stereotype.Repository;

import com.knongdai.tinh.entities.util.Pagination;

@Repository
public class SourceCategorySqlBuilder {
	
	
	public static String getAllSourceCategory(Pagination pagination)
	{
		StringBuffer buffer = new StringBuffer();
		String sql = "SELECT "
				+ "sc.source_category_pk_id, "
				+ "sc.source_category, "
				+ "sc.status, "
				+ "s.source_pk_id, "
				+ "s.domain, "
				+ "s.rows_selector, "
				+ "s.image_selector, "
				+ "s.image_attribute, "
				+ "s.link_selector, "
				+ "s.title_selector, "
				+ "s.price_selector, "
				+ "s.prefix_link, "
				+ "s.prefix_image, " 
				+ "sc.sub_two_fk_id, "
				+ "s2.category_name ";
				
		buffer.append(sql);
		if(pagination.getPage() <= 0 && pagination.getLimit() <= 0)
		{
			buffer.append("FROM "
					+ "phsar_source s  "
				+ "INNER JOIN "
					+ "phsar_source_category sc "
				+ "ON s.source_pk_id = sc.source_fk_id "
				+ "INNER JOIN "
					+ "phsar_sub_two_category s2 "
				+ "ON "
					+ "sc.sub_two_fk_id = s2.sub_two_pk_id");
			return buffer.toString();
		}else{
			buffer.append("FROM "
					+ "phsar_source s  "
				+ "INNER JOIN "
					+ "phsar_source_category sc "
				+ "ON s.source_pk_id = sc.source_fk_id "
				+ "INNER JOIN "
					+ "phsar_sub_two_category s2 "
				+ "ON "
					+ "sc.sub_two_fk_id = s2.sub_two_pk_id "
				+ "limit #{pagin.limit} "
				+ "OFFSET #{pagin.offset}");
			return buffer.toString();
		}
	}
	
	public static String countAllSourceCategory()
	{
		String sql = "SELECT "
				+ "COUNT(*) "
				+ "FROM "
					+ "phsar_source s  "
				+ "INNER JOIN "
					+ "phsar_source_category sc "
				+ "ON s.source_pk_id = sc.source_fk_id "
				+ "INNER JOIN "
					+ "phsar_sub_two_category s2 "
				+ "ON "
					+ "sc.sub_two_fk_id = s2.sub_two_pk_id";
		
			return sql;
			
		}
	
}
