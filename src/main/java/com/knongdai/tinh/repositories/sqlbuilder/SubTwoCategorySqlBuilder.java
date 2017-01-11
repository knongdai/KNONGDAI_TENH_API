package com.knongdai.tinh.repositories.sqlbuilder;

import org.springframework.stereotype.Repository;

import com.knongdai.tinh.entities.util.Pagination;

@Repository
public class SubTwoCategorySqlBuilder {
	
	
	
	public static String getAllSubTwoCategory(Pagination pagination)
	{
		StringBuilder buffer = new StringBuilder();
		
		String sql = "select "
						+ "st.sub_two_pk_id, "
						+ "st.category_name, "
						+ "st.date_create, "
						+ "so.sub_one_pk_id, "
						+ "so.category_name as main_category ";
		buffer.append(sql);
		if(pagination.getPage() <= 0 && pagination.getLimit() <= 0)
		{
			buffer.append(" from phsar_sub_two_category st "
					+ "inner join phsar_sub_one_category so "
					+ "on st.sub_fk_id = so.sub_one_pk_id ");
			return buffer.toString();
			
		}else{
			buffer.append(" from phsar_sub_two_category st "
					+ "inner join phsar_sub_one_category so "
					+ "on st.sub_fk_id = so.sub_one_pk_id "
					+ "order by st.sub_two_pk_id DESC "
					+ "limit #{pagin.limit} offset #{pagin.offset}");
			return buffer.toString();
		}
		
					
	}
	
	
	public static String countAllSubTwoCategory()
	{
			String sql = "select "
							+ "COUNT(*) "
						+ "from phsar_sub_two_category st "
						+ "inner join phsar_sub_one_category so "
						+ "on st.sub_fk_id = so.sub_one_pk_id ";	
			return sql;
	}
}
