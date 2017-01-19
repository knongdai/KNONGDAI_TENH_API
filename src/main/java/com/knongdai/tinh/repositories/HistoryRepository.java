package com.knongdai.tinh.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.knongdai.tinh.entities.History;

@Repository
public interface HistoryRepository {
	final String c_history = "insert into phsar_history(user_fk_id, product_fk_id) values(#{user.userid},#{product.productid})";
	final String r_history = "select "
							+ "		u.username, "
							+ "		u.user_pk_id, "
							+ "		p.product_pk_id,"
							+ "		p.title, "
							+ "		p.price, "
							+ "		p.image, "
							+ "		p.description, "
							+ "		p.images, "
							+ "		p.url, "
							+ "		h.history_pk_id, "
							+ "		h.date_create, "
							+ "		s.domain, "
							+ "		s.logo "
							+ "from "
							+ "		phsar_history h "
							+ "inner join "
							+ "		phsar_product p "
							+ "on "
							+ "		h.product_fk_id = p.product_pk_id "
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
							+ "		u.user_pk_id = h.user_fk_id "
							+ "WHERE "
							+ "		h.user_fk_id = #{user.userid}";
	
	final String d_history = "delete from phsar_history h where h.user_fk_id = #{user} AND h.history_pk_id = #{history}";
	
	@Results(value={
			@Result(property="historyid", column="history_pk_id"),
			@Result(property="datecreate", column="date_create"),
			@Result(property="user.username", column="username"),
			@Result(property="user.userid", column="user_pk_id"),
			@Result(property="product.productid", column="product_pk_id"),
			@Result(property="product.title", column="title"),
			@Result(property="product.price", column="price"),
			@Result(property="product.image", column="image"),
			@Result(property="product.images", column="images"),
			@Result(property="product.description", column="description"),
			@Result(property="product.url", column="url"),
			@Result(property="product.sourceCategory.source.logo", column="logo"),
			@Result(property="product.sourceCategory.source.domain", column="domain")
	})
	@Select(r_history)
	public ArrayList<History> getAllHistroy(int userid);
	
	@Insert(c_history)
	public int addHistory(History h);
	
	@Delete(d_history)
	public int deleteHistoryById(@Param("user") int user, @Param("history")int historyid);
}
