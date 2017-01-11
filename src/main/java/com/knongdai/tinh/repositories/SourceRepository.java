package com.knongdai.tinh.repositories;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.knongdai.tinh.entities.Product;
import com.knongdai.tinh.entities.ProductTemperory;
import com.knongdai.tinh.entities.Source;
import com.knongdai.tinh.entities.SourceCategory;

@Repository
public interface SourceRepository {
	
	final String c_source = "INSERT INTO phsar_source "
								+ "(domain, "
								+ "logo, "
								+ "rows_selector, "
								+ "image_selector, "
								+ "image_attribute, "
								+ "link_selector, "
								+ "title_selector, "
								+ "price_selector, "
								+ "description, "
								+ "multi_image_row_selector, "
								+ "multi_image_selector, "
								+ "multi_image_attribute, "
								+ "prefix_link, "
								+ "prefix_image) "
							+ "VALUES(#{domain},"
								+ "#{logo}, "
								+ "#{rowsselector}, "
								+ "#{imageselector}, "
								+ "#{imageattribute}, "
								+ "#{linkselector}, "
								+ "#{titleselector}, "
								+ "#{priceselector}, "
								+ "#{description}, "
								+ "#{multiImageRowSelector}, "
								+ "#{multiImageSelector}, "
								+ "#{multiImageAttribute}, "
								+ "#{prefixlink}, "
								+ "#{prefiximage})";
	
	final String r_source = "SELECT "
							+ "s.source_pk_id,"
							+"s.domain, "
							+"s.logo,"
							+"s.url, "
							+"s.rows_selector,"
							+"s.image_selector,"
							+"s.image_attribute,"
							+"s.link_selector,"
							+"s.title_selector, "
							+"s.price_selector, "
							+"s.description_selector, "
							+"s.multi_image_row_selector, "
							+"s.multi_image_selector, "
							+"s.multi_image_attribute, "
							+"s.prefix_link, "
							+"s.prefix_image "
							+ "FROM phsar_source s ";
	
	final String u_source = "update phsar_source "
							+ "set "
							+ "domain = #{domain}, "
							+ "logo = #{logo}, "
							+ "rows_selector = #{rowsselector}, "
							+ "image_selector = #{imageselector}, "
							+ "image_attribute = #{imageattribute}, "
							+ "link_selector = #{linkselector}, "
							+ "title_selector = #{titleselector}, "
							+ "price_selector = #{priceselector}, "
							+ "prefix_link = #{prefixlink}, "
							+ "prefix_image = #{prefiximage} "
							+ "WHERE source_pk_id = #{sourceid}" ;
	
	
	final String d_source = "delete from phsar_source where source_pk_id = #{sourceid}";
	
	@Results(value={
		@Result(property="sourceid", column="source_pk_id"),
		@Result(property="domain", column="domain"),
		@Result(property="logo", column="logo"),
		@Result(property="rowsselector", column="rows_selector"),
		@Result(property="imageselector", column="image_selector"),
		@Result(property="imageattribute", column="image_attribute"),
		@Result(property="linkselector", column="link_selector"),
		@Result(property="titleselector", column="title_selector"),
		@Result(property="priceselector", column="price_selector"),
		@Result(property="description", column="description_selector"),
		@Result(property="multiImageRowSelector", column="multi_image_row_selector"),
		@Result(property="multiImageSelector", column="multi_image_selector"),
		@Result(property="multiImageAttribute", column="multi_image_attribute"),
		@Result(property="prefixlink", column="prefix_link"),
		@Result(property="prefiximage", column="prefix_image")
	})
	@Select(r_source)
	public ArrayList<Source> getAllSources();
	
	
	
	@Select("SELECT DISTINCT s.* FROM phsar_source s INNER JOIN phsar_source_category sc ON s.source_pk_id = sc.source_fk_id")
	//@Select("SELECT * FROM phsar_source ")
	@Results(value={
			@Result(property="sourceid", column="source_pk_id"),
			@Result(property="domain", column="domain"),
			@Result(property="logo", column="logo"),
			@Result(property="rowsselector", column="rows_selector"),
			@Result(property="imageselector", column="image_selector"),
			@Result(property="imageattribute", column="image_attribute"),
			@Result(property="linkselector", column="link_selector"),
			@Result(property="titleselector", column="title_selector"),
			@Result(property="priceselector", column="price_selector"),
			@Result(property="sourceCategory", column="source_pk_id",
					many=@Many(select = "findSourceById")
			)
			
	})
	public List<Source> getAll();
	
	
	@Select("SELECT "
			+ "		sc.source_category_pk_id, "
			+ "		sc.source_category, "
			+ "		sc.status, "
			+ "		s2.category_name "
			+ "FROM "
			+ "		phsar_source_category sc "
			+ "INNER JOIN "
			+ "		phsar_source s "
			+ "ON "
			+ "		s.source_pk_id = sc.source_fk_id "
			+ "INNER JOIN "
			+ "		phsar_sub_two_category s2 "
			+ "ON "
			+ "		s2.sub_two_pk_id = sc.sub_two_fk_id "
			+ "WHERE "
			+ "		sc.source_fk_id = #{sourceid}")
	
	@Results(value={
			@Result(property="sourcecategoryid", column="source_category_pk_id"),
			@Result(property="sourcecategory", column="source_category"),
			@Result(property="status", column="status"),
			@Result(property="subCategory.categoryname", column="category_name"),
			@Result(property="listsProductsTmp", column="source_category_pk_id",
							many = @Many(select = "findProductTmpBySubCategoryId")
					)
	})
	public List<SourceCategory> findSourceById(int sourceid);
	
	@Select("SELECT "
			+ "p.product_pk_id, "
			+ "p.title, "
			+ "p.price, "
			+ "p.image, "
			+ "p.url, "
			+ "p.date_create, "
			+ "p.description "
		+ "FROM "
			+ "phsar_product_temperory p "
		+ "INNER JOIN "
			+ "phsar_source_category sc "
		+ "ON "
			+ "p.source_category_fk_id = sc.source_category_pk_id "
		+ "WHERE "
			+ "p.source_category_fk_id = #{sourcecategoryid} "
		+ "AND p.status = true")
	@Results(value={
			
			@Result(property="productid", column="product_pk_id"),
			@Result(property="title", column="title"),
			@Result(property="price", column="price"),
			@Result(property="image", column="image"),
			@Result(property="url", column="url"),
			@Result(property="description", column="description"),
			@Result(property="datecreate", column="date_create")
	})
	public List<ProductTemperory> findProductTmpBySubCategoryId(int subid);
	
	
	/**
	 * Join source with source_category one to many
	 * @return
	 */
	
	@Select("SELECT * FROM phsar_source")
	@Results(value={
			@Result(property="sourceid", column="source_pk_id"),
			@Result(property="domain", column="domain"),
			@Result(property="logo", column="logo"),
			@Result(property="rowsselector", column="rows_selector"),
			@Result(property="imageselector", column="image_selector"),
			@Result(property="imageattribute", column="image_attribute"),
			@Result(property="linkselector", column="link_selector"),
			@Result(property="titleselector", column="title_selector"),
			@Result(property="priceselector", column="price_selector"),
			@Result(property="sourceCategory", column="source_pk_id",
					many=@Many(select = "findSourceCategoryByfK")
			)
			
	})
	public List<Source> getSourceJoinOneToMany();
	
	
	@Select("SELECT "
			+ "		sc.source_category_pk_id, "
			+ "		sc.source_category, "
			+ "		sc.status, "
			+ "		s2.category_name "
			+ "FROM "
			+ "		phsar_source_category sc "
			+ "INNER JOIN "
			+ "		phsar_sub_two_category s2 "
			+ "ON "
			+ "		s2.sub_two_pk_id = sc.sub_two_fk_id "
			+ "WHERE "
			+ "		sc.source_fk_id = #{sourceid} ")
	
	@Results(value={
			@Result(property="sourcecategoryid", column="source_category_pk_id"),
			@Result(property="sourcecategory", column="source_category"),
			@Result(property="status", column="status"),
			@Result(property="subCategory.categoryname", column="category_name"),
			@Result(property="listsProducts", column="source_category_pk_id",
							many = @Many(select = "findProductBySourceCategoryfK")
					)
	})
	public List<SourceCategory> findSourceCategoryByfK(int sourceid);
	
	@Select("SELECT "
			+ "p.product_pk_id, "
			+ "p.title, "
			+ "p.price, "
			+ "p.image, "
			+ "p.url "
		+ "FROM "
			+ "phsar_product p "
		+ "INNER JOIN "
			+ "phsar_source_category sc "
		+ "ON "
			+ "p.source_category_fk_id = sc.source_category_pk_id "
		+ "WHERE "
			+ "p.source_category_fk_id = #{sourcecategoryid}")
	@Results(value={
			
			@Result(property="productid", column="product_pk_id"),
			@Result(property="title", column="title"),
			@Result(property="price", column="price"),
			@Result(property="images", column="image"),
			@Result(property="url", column="url")
	})
	public List<Product> findProductBySourceCategoryfK(int sourcecategoryid);
	
	
	@Insert(c_source)
	public int createSource(Source s);
	
	@Update(u_source)
	public int updateSource(Source s);
	
	@Delete(d_source)
	public int deleteSourceById(int id);
	
}
