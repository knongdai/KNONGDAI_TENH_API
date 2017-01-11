package com.knongdai.tinh.repositories.sqlbuilder;

import org.springframework.stereotype.Repository;

import com.knongdai.tinh.entities.util.Pagination;

@Repository
public class SubOneCategorySqlBuilder {
	
	public static String getAllSubCategory(Pagination pagination)
	{
		StringBuffer buffer = new StringBuffer();
		String sql = "select "
				+ "		s.sub_one_pk_id, "
				+ "		s.category_name, "
				+ "		s.date_create, "
				+ "		c.category_name as main_category_name, "
				+ "		c.category_pk_id ";
				
		buffer.append(sql);
		if(pagination.getPage() <= 0 && pagination.getLimit() <= 0)
		{
			buffer.append("from "
							+ "		phsar_sub_one_category s "
							+ "inner join "
							+ "		phsar_main_category c "
							+ "on "
							+ "		s.category_fk_id = c.category_pk_id ");
			return buffer.toString();
		}
		else
		{
			buffer.append("from "
					+ "		phsar_sub_one_category s "
					+ "inner join "
					+ "		phsar_main_category c "
					+ "on "
					+ "		s.category_fk_id = c.category_pk_id "
					+ "ORDER BY "
					+ "		s.sub_one_pk_id "
					+ "limit #{pagin.limit} offset #{pagin.offset}");
	return buffer.toString();
		}
				
	}
	
	
	public static String countAllSubCategory()
	{
		String sql = "select "
					+ "		count(*) "
					+ "from "
					+ "		phsar_sub_one_category s "
					+ "inner join "
					+ "		phsar_main_category c "
					+ "on "
					+ "		s.category_fk_id = c.category_pk_id ";
		return sql;
				
	}
	
	
}
