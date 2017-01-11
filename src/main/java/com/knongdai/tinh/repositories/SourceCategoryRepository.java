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

import com.knongdai.tinh.entities.SourceCategory;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.repositories.sqlbuilder.SourceCategorySqlBuilder;

@Repository
public interface SourceCategoryRepository {
	final String c_source = "insert into phsar_source_category(source_fk_id, source_category, status, sub_two_fk_id) "
							+ "values(#{source.sourceid}, #{sourcecategory}, #{status}, #{subCategory.subtwocategoryid})";
	
	final String r_url_scrap = "SELECT "
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
							+ "s.description_selector, "
							+ "s.multi_image_row_selector, "
							+ "s.multi_image_selector, "
							+ "s.multi_image_attribute, "
							+ "s.prefix_link, "
							+ "s.prefix_image, " 
							+ "sc.sub_two_fk_id, "
							+ "s2.category_name "
						+ "FROM "
							+ "phsar_source s  "
						+ "INNER JOIN "
							+ "phsar_source_category sc "
						+ "ON s.source_pk_id = sc.source_fk_id "
						+ "INNER JOIN "
							+ "phsar_sub_two_category s2 "
						+ "ON "
							+ "sc.sub_two_fk_id = s2.sub_two_pk_id";
	
	final String u_source = "update phsar_source_category set source_category = #{sourcecategory}, status = #{status} where source_category_pk_id = #{sourcecategoryid}";
	final String d_source = "delete from phsar_source_category where source_category_pk_id = #{sourcecategoryid}";
	
	@Results(value={
			@Result(property="sourcecategoryid", column="source_category_pk_id"),
			@Result(property="sourcecategory", column="source_category"),
			@Result(property="status", column="status"),
			@Result(property="source.sourceid", column="source_pk_id"),
			@Result(property="source.domain", column="domain"),
			@Result(property="source.rowsselector", column="rows_selector"),
			@Result(property="source.imageselector", column="image_selector"),
			@Result(property="source.imageattribute", column="image_attribute"),
			@Result(property="source.priceselector", column="price_selector"),
			@Result(property="source.linkselector", column="link_selector"),
			@Result(property="source.titleselector", column="title_selector"),
			@Result(property="source.priceselector", column="price_selector"),
			@Result(property="source.prefixlink", column="prefix_link"),
			@Result(property="source.prefiximage", column="prefix_image"),
			@Result(property="subCategory.subtwocategoryid", column="sub_two_fk_id"),
			@Result(property="subCategory.categoryname", column="category_name")
	})
	@SelectProvider(type = SourceCategorySqlBuilder.class, method = "getAllSourceCategory")
	public ArrayList<SourceCategory> getAllSourceCategory(@Param("pagin") Pagination pagin);
	
	@SelectProvider(type = SourceCategorySqlBuilder.class, method = "countAllSourceCategory")
	public long countAllSourceCategory();
	
	@Select(r_url_scrap)
	@Results(value={
			@Result(property="sourcecategoryid", column="source_category_pk_id"),
			@Result(property="sourcecategory", column="source_category"),
			@Result(property="status", column="status"),
			@Result(property="source.sourceid", column="source_pk_id"),
			@Result(property="source.domain", column="domain"),
			@Result(property="source.rowsselector", column="rows_selector"),
			@Result(property="source.imageselector", column="image_selector"),
			@Result(property="source.imageattribute", column="image_attribute"),
			@Result(property="source.priceselector", column="price_selector"),
			@Result(property="source.linkselector", column="link_selector"),
			@Result(property="source.titleselector", column="title_selector"),
			@Result(property="source.priceselector", column="price_selector"),
			@Result(property="source.description", column="description_selector"),
			@Result(property="source.multiImageRowSelector", column="multi_image_row_selector"),
			@Result(property="source.multiImageSelector", column="multi_image_selector"),
			@Result(property="source.multiImageAttribute", column="multi_image_attribute"),
			@Result(property="source.prefixlink", column="prefix_link"),
			@Result(property="source.prefiximage", column="prefix_image"),
			@Result(property="subCategory.subtwocategoryid", column="sub_two_fk_id"),
			@Result(property="subCategory.categoryname", column="category_name")
	})
	public ArrayList<SourceCategory> getAllUrlToScrap();
	
	@Select("SELECT sc.source_category "
			+ "FROM phsar_source_category sc "
			+ "INNER JOIN "
			+ "phsar_source s "
			+ "ON "
			+ "s.source_pk_id = sc.source_fk_id "
			+ "WHERE sc.source_fk_id = #{source.sourceid}")
	@Results(value={
			@Result(property="sourcecategory", column="source_category")
	})
	public ArrayList<SourceCategory> getById(int id);
	
	
	@Insert(c_source)
	public int createSourceCategory(SourceCategory sc);
	
	@Update(u_source)
	public int updateSourceCategory(SourceCategory sc);
	
	@Delete(d_source)
	public int deleteSourceCategoryById(int id);
	
}
