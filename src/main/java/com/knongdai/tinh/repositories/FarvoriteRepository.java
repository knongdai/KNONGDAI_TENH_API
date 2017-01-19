package com.knongdai.tinh.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.knongdai.tinh.entities.Farvorite;
import com.knongdai.tinh.entities.util.Pagination;

@Repository
public interface FarvoriteRepository {
	final String c_far = " insert "
						+ "into "
						+ "phsar_farvorite (user_fk_id, product_fk_id) "
						+ "select #{user.userid},#{product.productid} "
						+ "where "
						+ "not exists("
						+ "	select product_fk_id "
						+ "	from "
						+ "	phsar_farvorite "
						+ "	where "
						+ "	product_fk_id = #{product.productid}"
						+ "	and "
						+ "	user_fk_id = #{user.userid})";
	
		final String r_far = "SELECT "
							+ "u.username, "
							+ "u.user_pk_id, "
							+ "p.product_pk_id,"
							+ "p.title, "
							+ "p.description, "
							+ "p.price, "
							+ "p.image, "
							+ "p.images, "
							+ "p.url, "
							+ "f.farvorite_pk_id, "
							+ "f.date_create ,"
							+ "s.domain, "
							+ "s.logo "
						+ "FROM "
						+ "		phsar_farvorite f "
						+ "inner join "
						+ "		phsar_product p "
						+ "on "
						+ "		f.product_fk_id = p.product_pk_id "
						+ "INNER JOIN "
						+ "		phsar_source_category sc "
						+ "ON "
						+ "		sc.source_category_pk_id = p.source_category_fk_id "
						+ "INNER JOIN "
						+ "		phsar_source s "
						+ "ON "
						+ "		s.source_pk_id = sc.source_fk_id "
						+ "inner join "
						+ "		phsar_user u "
						+ "on "
						+ "		u.user_pk_id = f.user_fk_id "
						+ "WHERE "
						+ "		f.user_fk_id = #{userid} "
						+ "ORDER BY "
						+ "p.product_pk_id DESC "
						+ "LIMIT #{pagin.limit} "
						+ "OFFSET #{pagin.offset}";
	
	
	final String r_far_by_id = "SELECT "
								
								+ "COUNT(*) "
							+ "FROM "
							+ "		phsar_farvorite f "
							+ "inner join "
							+ "		phsar_product p "
							+ "on "
							+ "		f.product_fk_id = p.product_pk_id "
							+ "INNER JOIN "
							+ "		phsar_source_category sc "
							+ "ON "
							+ "		sc.source_category_pk_id = p.source_category_fk_id "
							+ "INNER JOIN "
							+ "		phsar_source s "
							+ "ON "
							+ "		s.source_pk_id = sc.source_fk_id "
							+ "inner join "
							+ "		phsar_user u "
							+ "on "
							+ "		u.user_pk_id = f.user_fk_id "
							+ "WHERE "
							+ "		f.user_fk_id = #{user.userid} ";
	
	final String d_far = "delete from phsar_farvorite f where f.user_fk_id = #{users} AND f.farvorite_pk_id = #{farvorites}";
	
	
	@Results(value={
			@Result(property="farvoriteid", column="farvorite_pk_id"),
			@Result(property="datecreate", column="date_create"),
			@Result(property="user.username", column="username"),
			@Result(property="user.userid", column="user_pk_id"),
			@Result(property="product.productid", column="product_pk_id"),
			@Result(property="product.title", column="title"),
			@Result(property="product.price", column="price"),
			@Result(property="product.description", column="description"),
			@Result(property="product.images", column="images"),
			@Result(property="product.image", column="image"),
			@Result(property="product.url", column="url"),
			@Result(property="product.sourceCategory.source.logo", column="logo"),
			@Result(property="product.sourceCategory.source.domain", column="domain")
	})
	@Select(r_far)
	public ArrayList<Farvorite> getFarvoriteByUserId(@Param("userid") int userid, @Param("pagin") Pagination pagin);
	
	@Select(r_far_by_id)
	public long countFarvoriteByUserId(int userid);
	
	@Insert(c_far)
	public int addFarvorite(Farvorite f);
	
	@Delete(d_far)
	public int deleteFarvoriteById(@Param("users") int user, @Param("farvorites")int farvorite);

}
