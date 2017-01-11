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

import com.knongdai.tinh.entities.MainCategory;
import com.knongdai.tinh.entities.ProductType;
import com.knongdai.tinh.entities.SubOneCategory;
import com.knongdai.tinh.entities.SubTwoCategory;

@Repository
public interface MainCategoryRepository {
	
	/**
	 * SQL to in ibatis-annotations
	 */
	final String c_category = "insert into phsar_main_category (category_name) values(#{categoryname})";
	final String r_category = "select * from phsar_main_category order by category_pk_id desc";
	final String r_id_category = "select * from phsar_main_category where category_pk_id = #{maincategoryid}";
	final String u_category = "update phsar_main_category set category_name = #{categoryname} where category_pk_id = #{maincategoryid}";
	final String d_category = "delete from phsar_main_category where category_pk_id = #{maincategoryid}";
	
	/**
	 * Add new main-category
	 * @param mCategory
	 * @return integer
	 */
	@Insert(c_category)
	public int addMainCatetory(MainCategory mCategory);
	
	/**
	 * Retriev all main-category
	 * @return arraylist OBJECT
	 */
	@Results(value={
			@Result(property="maincategoryid", column="category_pk_id"),
			@Result(property="categoryname", column="category_name"),
			@Result(property="datecreate", column="date_create")
	})
	@Select(r_category)
	public ArrayList<MainCategory> getAllMainCategory();
	
	/**
	 * Retriev all sub-one-category have in main-category
	 * @return list OBJECT
	 */
	@Select("SELECT m.category_pk_id, m.category_name FROM phsar_main_category m")
	@Results(value={
			@Result(property="maincategoryid", column="category_pk_id"),
			@Result(property="categoryname", column="category_name"),
			@Result(property="listsSubCategory", column="category_pk_id",
						many = @Many(select = "findSubOneCategoryById")
					)
	})
	public List<MainCategory> getSubCategoryAsLists();
	
	/**
	 * Retriev sub-one-category by main-category-id
	 * @param mainid
	 * @return list OBJECT
	 */
	@Select("SELECT "
			+"s.sub_one_pk_id, "
			+"s.category_name "
		+"FROM "
			+"phsar_sub_one_category s "
		+"WHERE "
			+"category_fk_id =  #{maincategoryid}")
	@Results(value={
			@Result(property="subonecategoryid", column="sub_one_pk_id"),
			@Result(property="categoryname", column="category_name"),
			@Result(property="listsSubTwoCategory", column="sub_one_pk_id",
						many = @Many(select = "findSubTwoCategoryByMainId")
					)
	})
	public List<SubOneCategory> findSubOneCategoryById(int mainid);
	
	/**
	 * Retriev sub-one-category by main-category-id
	 * @param mainid
	 * @return list OBJECT
	 */
	@Select("SELECT "
				+"s2.sub_two_pk_id, "
				+"s2.category_name, "
				+ "s2.date_create "
			+"FROM "
				+"phsar_sub_two_category s2 "
			+"WHERE sub_fk_id = #{subonecategoryid}")
	@Results(value={
			@Result(property="subtwocategoryid",column="sub_two_pk_id"),
			@Result(property="categoryname", column="category_name"),
			@Result(property="datecreate", column="date_create"), 
			@Result(property="listProductType", column="sub_two_pk_id",
						many = @Many(select = "findProductTypeBySubTwoId")
					)
		})
	public List<SubTwoCategory> findSubTwoCategoryByMainId(int mainid);
	
	@Select("SELECT "
			+"pt.product_type_pk_id, "
			+"pt.product_type "
		+"FROM "
			+"phsar_product_type pt "
		+"WHERE pt.sub_two_fk_id = #{subtwocategoryid}")
	@Results(value={
			@Result(property="productid",column="product_type_pk_id"),
			@Result(property="productType", column="product_type")
		})
	public List<ProductType> findProductTypeBySubTwoId(int id);
	/**
	 * Retriev main-category-by-id
	 * @param id
	 * @return arraylist OBJECT
	 */
	@Results(value={
			@Result(property="maincategoryid", column="category_pk_id"),
			@Result(property="categoryname", column="category_name"),
			@Result(property="datecreate", column="date_create")
	})
	@Select(r_id_category)
	public ArrayList<MainCategory> getMainCategoryById(int id);
	
	/**
	 * Update main-category
	 * @param mCategory
	 * @return integer
	 */
	@Update(u_category)
	public int updateMainCategory(MainCategory mCategory);
	
	/**
	 * Delete main-category-by-id
	 * @param id
	 * @return integer
	 */
	@Delete(d_category)
	public int deleteMainCategoryById(int id);
	
	
}
