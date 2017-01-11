package com.knongdai.tinh.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.knongdai.tinh.entities.SubTwoCategory;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.repositories.sqlbuilder.SubTwoCategorySqlBuilder;

@Repository
public interface SubTwoCategoryRepository {
	
	final String c_cate = "insert into phsar_sub_two_category(sub_fk_id, category_name) values(#{suboneCategory.subonecategoryid}, #{categoryname})";
	
	/*final String r_cate = "select "
							+ "st.sub_two_pk_id, "
							+ "st.category_name, "
							+ "st.date_create, "
							+ "so.sub_one_pk_id, "
							+ "so.category_name as main_category "
						+ "from phsar_sub_two_category st "
						+ "inner join phsar_sub_one_category so "
						+ "on st.sub_fk_id = so.sub_one_pk_id "
						+ "order by st.sub_two_pk_id DESC "
						+ "limit #{pagin.limit} offset #{pagin.offset}";*/
	
	/*final String r_count_cate = "select "
								+ "COUNT(*) "
							+ "from phsar_sub_two_category st "
							+ "inner join phsar_sub_one_category so "
							+ "on st.sub_fk_id = so.sub_one_pk_id ";*/
	
	final String r_by_sub_one_id = "select "
									+ "st.sub_two_pk_id, "
									+ "st.category_name, "
									+ "st.date_create, "
									+ "so.sub_one_pk_id, "
									+ "so.category_name as main_category "
								+ "from phsar_sub_two_category st "
								+ "inner join phsar_sub_one_category so "
								+ "on st.sub_fk_id = so.sub_one_pk_id "
								+ "where "
								+ "st.sub_fk_id = #{suboneCategory.subonecategoryid}";
	
	final String u_cate = "update phsar_sub_two_category set category_name = #{categoryname} where sub_two_pk_id = #{subtwocategoryid}";
	final String d_cate = "delete from phsar_sub_two_category where sub_two_pk_id = #{subtwocategoryid}";
	
	@Results(value={
		@Result(property="subtwocategoryid",column="sub_two_pk_id"),
		@Result(property="categoryname", column="category_name"),
		@Result(property="datecreate", column="date_create"),
		@Result(property="suboneCategory.subonecategoryid", column="sub_one_pk_id"),
		@Result(property="suboneCategory.categoryname", column="main_category")
	})
	
	@SelectProvider(type = SubTwoCategorySqlBuilder.class, method = "getAllSubTwoCategory")
	public ArrayList<SubTwoCategory> getAllSubTwoCategory(@Param("pagin") Pagination pagin);
	
	@SelectProvider(type = SubTwoCategorySqlBuilder.class, method = "countAllSubTwoCategory")
	public long countAllSubTwoCategory();
	
	
	@Results(value={
			@Result(property="subtwocategoryid",column="sub_two_pk_id"),
			@Result(property="categoryname", column="category_name"),
			@Result(property="datecreate", column="date_create"),
			@Result(property="suboneCategory.subonecategoryid", column="sub_one_pk_id"),
			@Result(property="suboneCategory.categoryname", column="main_category")
		})
	@Select(r_by_sub_one_id)
	public ArrayList<SubTwoCategory> getSubTwoCategoryByMainId(int mainid);
	
	@Insert(c_cate)
	public int createCategory(SubTwoCategory s);
	
	@Update(u_cate)
	public int updateCategory(SubTwoCategory s);
	
	@Delete(d_cate)
	public int deleteCategoryById(int id);
	
}
