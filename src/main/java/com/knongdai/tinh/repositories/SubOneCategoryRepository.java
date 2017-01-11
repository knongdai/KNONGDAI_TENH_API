package com.knongdai.tinh.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.knongdai.tinh.entities.SubOneCategory;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.repositories.sqlbuilder.SubOneCategorySqlBuilder;

@Repository
public interface SubOneCategoryRepository {
	
	final String c_category = "insert into phsar_sub_one_category (category_fk_id, category_name) values(#{mainCategory.maincategoryid},#{categoryname})";
	
	final String u_category = "update phsar_sub_one_category set category_name = #{categoryname} where sub_one_pk_id = #{subonecategoryid}";
	final String d_category = "delete from phsar_sub_one_category where sub_one_pk_id = #{subonecategoryid}";
	
	@Results(value={
			@Result(property="subonecategoryid", column="sub_one_pk_id"),
			@Result(property="categoryname", column="category_name"),
			@Result(property="datecreate", column="date_create"),
			@Result(property="mainCategory.categoryname", column="main_category_name"),
			@Result(property="mainCategory.maincategoryid", column="category_pk_id"),
	})
	@SelectProvider(type = SubOneCategorySqlBuilder.class, method = "getAllSubCategory")
	public ArrayList<SubOneCategory> getAllSubCategory(@Param("pagin") Pagination pagin);
	
	@SelectProvider(type = SubOneCategorySqlBuilder.class, method = "countAllSubCategory")
	public long countAllSubCategory();

	@Insert(c_category)
	public int createSubCatetory(SubOneCategory sCategory);
	
	
	@Update(u_category)
	public int updateSubCategory(SubOneCategory sCategory);
	
	@Delete(d_category)
	public int deleteSubOneCategoryById(int id);
}
