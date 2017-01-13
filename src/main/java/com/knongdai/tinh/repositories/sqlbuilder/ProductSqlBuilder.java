package com.knongdai.tinh.repositories.sqlbuilder;

import org.springframework.stereotype.Repository;

import com.knongdai.tinh.entities.util.Pagination;

/**
 * 
 * @author kpsec20
 * To provide SQL statement to Product Repository Interface
 *
 */
@Repository
public class ProductSqlBuilder {

	public static String getDetailProductById(int userId){
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT p.title, p.price, p.image, p.images, p.url, p.description, s.logo, ");
					if(userId > 0){
						buffer.append("COALESCE(F.user_fk_id, 0) AS user_fk_id ");
					}else{
						buffer.append("0 AS user_fk_id ");
					}
					buffer.append(" FROM phsar_product p "
					+" INNER JOIN phsar_source_category sc "
					+" ON p.source_category_fk_id = sc.source_category_pk_id "
					+" INNER JOIN phsar_source s "
					+" ON s.source_pk_id = sc.source_fk_id ");
					if(userId > 0){
						buffer.append("LEFT JOIN phsar_fa"
								+ "rvorite F ON p.product_pk_id = F.product_fk_id AND F.user_fk_id =#{userId} ");
					}
					buffer.append(" WHERE p.product_pk_id = #{productid}");
					return buffer.toString();
	}
	
	/**
	 * find product by filter category and product
	 * @param category
	 * @param title
	 * @return sql statement as String
	 */
	public static String findProductsByTitle(int userId, int category, String title, Pagination pagin){
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT "
							+ "p.product_pk_id, "
							+ "p.title, "
							+ "p.price, "
							+ "p.image, "
							+ "p.images, "
							+ "p.url, "
							+ "p.description, "
							+ "p.date_create, "
							+ "s2.category_name, ");
							if(userId > 0){
								buffer.append("COALESCE(F.user_fk_id, 0) AS user_fk_id ");
							}else{
								buffer.append("0 AS user_fk_id ");
							}
						buffer.append(" FROM "
							+ "phsar_product p "
						+ "INNER JOIN "
							+ "phsar_sub_two_category s2 "
						+ "ON "
							+ "s2.sub_two_pk_id = p.sub_two_fk_id "
						+ "INNER JOIN "
							+ "phsar_sub_one_category s1 "
						+ "ON "
							+ "s2.sub_fk_id = s1.sub_one_pk_id "
						+ "INNER JOIN "
							+ "phsar_main_category m "
						+ "ON "
							+ "s1.category_fk_id = m.category_pk_id ");
						if(userId > 0){
							buffer.append("LEFT JOIN phsar_farvorite F ON p.product_pk_id = F.product_fk_id AND F.user_fk_id =#{userId} ");
						}
						
		if(category > 0 && !title.equals("null")){
			buffer.append(" WHERE publish=true AND m.category_pk_id = " + category 
						+" AND "
						+ " LOWER(p.title) LIKE LOWER('%"+ title +"%') "
						+" ORDER BY p.product_pk_id DESC "
						+"	LIMIT "
						+"		#{pagin.limit} "			
						+"	OFFSET "
						+"		#{pagin.offset}");
			//return buffer.toString();
		}
		else if(category <= 0 && !title.equals("null"))
		{
			buffer.append(" WHERE publish=true AND LOWER(p.title) LIKE LOWER('%"+ title +"%')"
							+" ORDER BY p.product_pk_id DESC "
							+"	LIMIT "
							+"		#{pagin.limit} "
										
							+"	OFFSET "
							+"		#{pagin.offset}");
			//return buffer.toString();
		}
		else if(category > 0 && title.equals("null"))
		{
			buffer.append(" WHERE publish=true AND m.category_pk_id = " + category
						+" ORDER BY p.product_pk_id DESC "
						+"	LIMIT "
						+"		#{pagin.limit} "
									
						+"	OFFSET "
						+"		#{pagin.offset}");
			//return buffer.toString();
		}else{
			buffer.append(" WHERE publish=true ");
		}
		return buffer.toString();
	}
	
	
	/**
	 * Count product find by title
	 * @param category
	 * @param title
	 * @return
	 */
	public static String countProductsByTitle(int category, String title){
		StringBuffer buffer = new StringBuffer();
		String select = "SELECT "
							+ "COUNT(*) "
						+ "FROM "
							+ "phsar_product p "
						+ "INNER JOIN "
							+ "phsar_sub_two_category s2 "
						+ "ON "
							+ "s2.sub_two_pk_id = p.sub_two_fk_id "
						+ "INNER JOIN "
							+ "phsar_sub_one_category s1 "
						+ "ON "
							+ "s2.sub_fk_id = s1.sub_one_pk_id "
						+ "INNER JOIN "
							+ "phsar_main_category m "
						+ "ON "
							+ "s1.category_fk_id = m.category_pk_id ";

		buffer.append(select);
		if(category > 0 && !title.equals("null")){
			buffer.append("WHERE publish=true AND m.category_pk_id = "
						+ category 
						+" AND "
						+ " LOWER(p.title) LIKE LOWER('%"+ title +"%') ");
			return buffer.toString();
		}
		else if(category <= 0 && !title.equals("null"))
		{
			buffer.append("WHERE publish=true AND LOWER(p.title) LIKE LOWER('%"+ title +"%')");
			return buffer.toString();
		}
		else if(category > 0 && title.equals("null"))
		{
			buffer.append("WHERE publish=true AND m.category_pk_id = " + category);
			return buffer.toString();
		}else{
			buffer.append("WHERE publish=true");
		}
		
		return buffer.toString();
	}
	
	/**
	 * Allow client to get all products
	 * @return sql statement as String 
	 */
	public static String getALLProducts(int userId, Pagination pagin){
		
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("SELECT "
				+ "p.product_pk_id, "
				+ "p.product_type_fk_id, "
				+ "p.title, "
				+ "p.price, "
				+ "p.image, "
				+ "p.images, "
				+ "p.url, "
				+ "p.date_create, "
				+ "p.description, "
				+ "s2.category_name, "
				+ "sc.source_category, "
				+ "s.logo, "
				+ "s.domain, ");
		if(userId > 0){
			strBuilder.append("COALESCE(F.user_fk_id, 0) AS user_fk_id ");
		}else{
			strBuilder.append("0 AS user_fk_id ");
		}
				
		strBuilder.append("FROM "
				+ "phsar_product p "
			+ "INNER JOIN "
				+ "phsar_sub_two_category s2 "
			+ "ON "
				+ "p.sub_two_fk_id = s2.sub_two_pk_id  "
			+ "INNER JOIN "
				+ "phsar_source_category sc "
			+ "ON "
				+ "p.source_category_fk_id = sc.source_category_pk_id "
			+ "INNER JOIN "
				+ "phsar_source s "
			+ "ON "
				+ "s.source_pk_id = sc.source_fk_id	");
			if(userId > 0){
				strBuilder.append("LEFT JOIN phsar_farvorite F ON p.product_pk_id = F.product_fk_id AND F.user_fk_id =#{userId} ");
			}
			
			strBuilder.append(" WHERE publish=true ");
			
			strBuilder.append(" ORDER BY p.product_pk_id DESC LIMIT #{pagin.limit} OFFSET #{pagin.offset}");
			
		return strBuilder.toString();
	}
	
	
	/**
	 * Allow client to get all products
	 * @return sql statement as String 
	 */
	public static String countALLProducts(){
		String select = "SELECT "
							+ "COUNT(*) "
						+ "FROM "
							+ "phsar_product p "
						+ "INNER JOIN "
							+ "phsar_sub_two_category s "
						+ "ON "
							+ "p.sub_two_fk_id = s.sub_two_pk_id  "
						+ "INNER JOIN "
							+ "phsar_source_category sc "
						+ "ON "
							+ "p.source_category_fk_id = sc.source_category_pk_id "
						+ " WHERE publish=true ";
		return select;
	}
	
	/**
	 * Allow client to delete product by id
	 * @param id
	 * @return sql statement as String
	 */
	public static String deleteProductById(){
		String delete = "DELETE FROM phsar_product WHERE product_pk_id = #{productid}";
		return delete;
	}
	
	/**
	 * Allow client to find product by sub category name
	 * @return sql statement as String
	 */
	public static String findProductBySubCategory(int userId, int id, Pagination pagin){
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(" SELECT "
						+ "		p.product_pk_id," 
						+"		p.title, "
						+"		p.price, "
						+"		p.image, "
						+ "		p.images, "
						+"		p.url, "
						+"		p.date_create, "
						+"		s2.category_name, "
						+"		s.domain, "
						+ "		s.logo, ");
						if(userId > 0){
							buffer.append("COALESCE(F.user_fk_id, 0) AS user_fk_id ");
						}else{
							buffer.append("0 AS user_fk_id ");
						}
						buffer.append(" FROM "
						+"		phsar_product p "
						+" INNER JOIN "
						+"		phsar_sub_two_category s2 "
						+" ON "
						+"		p.sub_two_fk_id = s2.sub_two_pk_id "
						+" INNER JOIN "
						+"		phsar_source_category sc "
						+" ON "
						+"		p.source_category_fk_id = sc.source_category_pk_id "
						+" INNER JOIN "
						+"		phsar_source s "
						+" ON "
						+"		sc.source_fk_id = s.source_pk_id ");
						if(userId > 0){
							buffer.append("LEFT JOIN phsar_farvorite F ON p.product_pk_id = F.product_fk_id AND F.user_fk_id =#{userId} ");
						}
						buffer.append(" WHERE publish=true AND"
						+"		s2.sub_two_pk_id = "+id
						+" ORDER BY p.product_pk_id DESC "
						
						+"	LIMIT "
						+"		#{pagin.limit} "
						
						+"	OFFSET "
						+"		#{pagin.offset}");
		return buffer.toString();
	}
	
	
	/**
	 * Count product for method findProductBySubCategory
	 * @return sql statement as String
	 */
	public static String countProductBySubCategory(){

		String select = ""
						+" SELECT " 
						+"		COUNT(*) "
						+" FROM "
						+"		phsar_product p "
						+" INNER JOIN "
						+"		phsar_sub_two_category s2 "
						+" ON "
						+"		p.sub_two_fk_id = s2.sub_two_pk_id "
						+" INNER JOIN "
						+"		phsar_source_category sc "
						+" ON "
						+"		p.source_category_fk_id = sc.source_category_pk_id "
						+" INNER JOIN "
						+"		phsar_source s "
						+" ON "
						+"		sc.source_fk_id = s.source_pk_id "
						+" WHERE "
						+ "			publish=true "
						+ "		AND"
						+"			s2.sub_two_pk_id = #{subtwoCategory.subtwocategoryid} ";	
		return select;
	}
	
	/**
	 * Find Products by filter
	 * @param subid
	 * @param sourceid
	 * @param start_price
	 * @param price
	 * @param pagin
	 * @return
	 */
	public static String findProductByFilter(int userId, int mainid, String title, int subid,int sourceid, double start_price, double price)
	{
		StringBuffer buffer = new StringBuffer();
		
						buffer.append(" SELECT "
						+ "		p.product_pk_id," 
						+"		p.title, "
						+"		p.price, "
						+"		p.image, "
						+ "		p.images, "
						+"		p.url, "
						+"		p.date_create, "
						+"		s2.category_name, "
						+"		s.domain, "
						+ "		s.logo, ");
						if(userId > 0){
							buffer.append("COALESCE(F.user_fk_id, 0) AS user_fk_id ");
						}else{
							buffer.append("0 AS user_fk_id ");
						}
						buffer.append(" FROM "
						+"		phsar_product p "
						+" INNER JOIN "
						+"		phsar_sub_two_category s2 "
						+" ON "
						+"		p.sub_two_fk_id = s2.sub_two_pk_id "
						+"INNER JOIN "
						+"		phsar_sub_one_category s1 "
						+"ON "
						+"		s2.sub_fk_id = s1.sub_one_pk_id "
						+"INNER JOIN "
						+"		phsar_main_category m "
						+"ON "
						+"		s1.category_fk_id = m.category_pk_id "
						+" INNER JOIN "
						+"		phsar_source_category sc "
						+" ON "
						+"		p.source_category_fk_id = sc.source_category_pk_id "
						+" INNER JOIN "
						+"		phsar_source s "
						+" ON "
						+"		sc.source_fk_id = s.source_pk_id ");
						
						if(userId > 0){
							buffer.append("LEFT JOIN phsar_farvorite F ON p.product_pk_id = F.product_fk_id AND F.user_fk_id =#{userId} ");
						}
		
		if(mainid > 0 && title.equals("null") && price <= 0 && start_price <= 0 && sourceid <= 0 && subid >= 0)
		{
			buffer.append("WHERE publish=true AND m.category_pk_id = "
					+ mainid
					+" ORDER BY p.product_pk_id DESC "
					+"	LIMIT "
					+"		#{pagin.limit} "
					+"	OFFSET "
					+"		#{pagin.offset}");
			return buffer.toString();
		}
		else if(!title.equals("null") && mainid <= 0 && price <= 0 && start_price <= 0 && sourceid <= 0 && subid >= 0)
		{
			buffer.append("WHERE publish=true AND LOWER(p.title) LIKE LOWER('%"+ title +"%')"
					+" ORDER BY p.product_pk_id DESC "
					+"	LIMIT "
					+"		#{pagin.limit} "
								
					+"	OFFSET "
					+"		#{pagin.offset}");
			return buffer.toString();
		}
		else if(!title.equals("null") && mainid > 0 && price <= 0 && start_price <= 0 && sourceid <= 0 && subid >= 0)
		{
			buffer.append("WHERE publish=true AND m.category_pk_id = "
					+ mainid
					+" AND "
					+ " LOWER(p.title) LIKE LOWER('%"+ title +"%') "
					+" ORDER BY p.product_pk_id DESC "
					+"	LIMIT "
					+"		#{pagin.limit} "
								
					+"	OFFSET "
					+"		#{pagin.offset}");
			return buffer.toString();
		}
		
		
		//**
		else if(!title.equals("null") && sourceid > 0 && price <= 0 && start_price <= 0 &&  mainid <= 0 && subid >= 0)
		{
			buffer.append("WHERE publish=true AND s.source_pk_id = "
					+ sourceid
					+" AND "
					+ " LOWER(p.title) LIKE LOWER('%"+ title +"%') "
					+" ORDER BY p.product_pk_id DESC "
					+"	LIMIT "
					+"		#{pagin.limit} "
								
					+"	OFFSET "
					+"		#{pagin.offset}");
			return buffer.toString();
		}
		
		else if(!title.equals("null") && price > 0 && start_price >= 0 && mainid <= 0 && sourceid <= 0 && subid >= 0)
		{
			buffer.append("WHERE publish=true AND "
					+" LOWER(p.title) LIKE LOWER('%"+ title +"%') "
					+" AND "
					+" p.price BETWEEN "+ start_price +" "
					+ "AND "+ price +""
					+" ORDER BY p.product_pk_id DESC "
					+" LIMIT "
					+"		#{pagin.limit} "
								
					+"	OFFSET "
					+"		#{pagin.offset}");
			return buffer.toString();
		}
		
		else if(!title.equals("null") && sourceid > 0 && mainid > 0 && price <= 0 && start_price <= 0 && subid >= 0)
		{
			buffer.append("WHERE publish=true AND m.category_pk_id = "+ mainid
					+ " AND "
					+ " s.source_pk_id = "+ sourceid
					+" AND "
					+ " LOWER(p.title) LIKE LOWER('%"+ title +"%') "
					+" ORDER BY p.product_pk_id DESC "
					+"	LIMIT "
					+"		#{pagin.limit} "
								
					+"	OFFSET "
					+"		#{pagin.offset}");
			return buffer.toString();
		}
		
		else if(!title.equals("null") && price > 0 && start_price >= 0 && mainid > 0 && sourceid <= 0 && subid >= 0)
		{
			buffer.append("WHERE publish=true AND m.category_pk_id = "+ mainid
					+" AND "
					+" LOWER(p.title) LIKE LOWER('%"+ title +"%') "
					+" AND "
					+" p.price BETWEEN "+ start_price +" "
					+" AND "+ price +""
					+" ORDER BY p.product_pk_id DESC "
					+" LIMIT "
					+"		#{pagin.limit} "
								
					+"	OFFSET "
					+"		#{pagin.offset}");
			return buffer.toString();
		}
		
		else if(!title.equals("null") && price > 0 && start_price >= 0 && sourceid > 0 && mainid <= 0 && subid >= 0)
		{
			buffer.append("WHERE publish=true AND s.source_pk_id = "+ sourceid
					+" AND "
					+" LOWER(p.title) LIKE LOWER('%"+ title +"%') "
					+" AND "
					+" p.price BETWEEN "+ start_price +" "
					+" AND "+ price +""
					+" ORDER BY p.product_pk_id DESC "
					+" LIMIT "
					+"		#{pagin.limit} "
								
					+"	OFFSET "
					+"		#{pagin.offset}");
			return buffer.toString();
		}
		//**
		
		else if(subid > 0 && start_price > 0 && price <= 0 && sourceid <= 0 && title.equals("null") && mainid <= 0)
		{
			buffer.append(" WHERE publish=true AND "
								+ "p.sub_two_fk_id = "+subid
								+ " AND p.price = "+ start_price +" "
								+ "ORDER BY p.product_pk_id DESC "
								+ "LIMIT #{pagin.limit} "
								+ "OFFSET #{pagin.offset}");
			return buffer.toString();
		}
		else if(subid > 0 && start_price >= 0 & price > 0 && sourceid > 0 && title.equals("null") && mainid <= 0)
		{
			buffer.append(" WHERE publish=true AND "
								+ "p.sub_two_fk_id = "+subid
								+ " AND "
								+ " s.source_pk_id = "+sourceid
								+ " AND p.price BETWEEN "+ start_price +" "
								+ "AND "+ price +""
								+ "ORDER BY p.product_pk_id DESC "
								+ "LIMIT #{pagin.limit} "
								+ "OFFSET #{pagin.offset}");
			return buffer.toString();
		}	
		else if(subid > 0 && start_price >= 0 & price > 0 && sourceid <= 0 && title.equals("null") && mainid <= 0)
		{
			buffer.append(" WHERE publish=true AND "
								+ "p.sub_two_fk_id = "+subid
								+ " AND p.price BETWEEN "+ start_price +" "
								+ "AND "+ price +""
								+ "ORDER BY p.product_pk_id DESC "
								+ "LIMIT #{pagin.limit} "
								+ "OFFSET #{pagin.offset}");
			return buffer.toString();
		}	
		else if(sourceid > 0 && subid > 0 && start_price <= 0 && price <= 0 && title.equals("null") && mainid <= 0)
		{
			buffer.append(" WHERE publish=true AND "
							+ " p.sub_two_fk_id = "+subid
							+ " AND "
							+ "s.source_pk_id = "+sourceid								
							+" ORDER BY p.product_pk_id DESC "
						
							+"	LIMIT "
							+"		#{pagin.limit} "
							
							+"	OFFSET "
							+"		#{pagin.offset}");
			return buffer.toString();
		}
		else if(subid > 0 && sourceid <= 0 && start_price <= 0 && price <= 0 && title.equals("null") && mainid <= 0)
		{
			buffer.append(" WHERE publish=true AND "
					+ " p.sub_two_fk_id = "+subid							
					+" ORDER BY p.product_pk_id DESC "
				
					+"	LIMIT "
					+"		#{pagin.limit} "
					
					+"	OFFSET "
					+"		#{pagin.offset}");
			return buffer.toString();
		}else{
			buffer.append(" WHERE publish=true");
		}
		return buffer.toString();
	}
	
	/**
	 * Count products by filter
	 * @param subid
	 * @param sourceid
	 * @param start_price
	 * @param price
	 * @return
	 */
	public static String countProductByFilter(int mainid, String title, int subid,int sourceid, double start_price, double price)
	{
		StringBuffer buffer = new StringBuffer();
		String select = ""
						+" SELECT " 
						+"		COUNT(*) "
						+" FROM "
						+"		phsar_product p "
						+" INNER JOIN "
						+"		phsar_sub_two_category s2 "
						+" ON "
						+"		p.sub_two_fk_id = s2.sub_two_pk_id "
						+"INNER JOIN "
						+"		phsar_sub_one_category s1 "
						+"ON "
						+"		s2.sub_fk_id = s1.sub_one_pk_id "
						+"INNER JOIN "
						+"		phsar_main_category m "
						+"ON "
						+"		s1.category_fk_id = m.category_pk_id "
						+" INNER JOIN "
						+"		phsar_source_category sc "
						+" ON "
						+"		p.source_category_fk_id = sc.source_category_pk_id "
						+" INNER JOIN "
						+"		phsar_source s "
						+" ON "
						+"		sc.source_fk_id = s.source_pk_id ";
		buffer.append(select);
		if(mainid > 0 && title.equals("null") && price <= 0 && start_price <= 0 && sourceid <= 0 && subid >= 0)
		{
			buffer.append("WHERE publish=true AND m.category_pk_id = "
					+ mainid);
			return buffer.toString();
		}
		else if(!title.equals("null") && mainid <= 0 && price <= 0 && start_price <= 0 && sourceid <= 0 && subid >= 0)
		{
			buffer.append("WHERE publish=true AND LOWER(p.title) LIKE LOWER('%"+ title +"%')");
			return buffer.toString();
		}
		
		else if(!title.equals("null") && mainid > 0 && price <= 0 && start_price <= 0 && sourceid <= 0 && subid >= 0)
		{
			buffer.append("WHERE publish=true AND m.category_pk_id = "
					+ mainid
					+" AND "
					+ " LOWER(p.title) LIKE LOWER('%"+ title +"%') ");
			return buffer.toString();
		}
		
		
		//**
		else if(!title.equals("null") && sourceid > 0 && price <= 0 && start_price <= 0 &&  mainid <= 0 && subid >= 0)
		{
			buffer.append("WHERE publish=true AND s.source_pk_id = "
					+ sourceid
					+" AND "
					+ " LOWER(p.title) LIKE LOWER('%"+ title +"%') ");
			return buffer.toString();
		}
		
		else if(!title.equals("null") && price > 0 && start_price >= 0 && mainid <= 0 && sourceid <= 0 && subid >= 0)
		{
			buffer.append("WHERE publish=true AND "
					+" LOWER(p.title) LIKE LOWER('%"+ title +"%') "
					+" AND "
					+" p.price BETWEEN "+ start_price +" "
					+ "AND "+ price);
			return buffer.toString();
		}
		
		else if(!title.equals("null") && sourceid > 0 && mainid > 0 && price <= 0 && start_price <= 0 && subid >= 0)
		{
			buffer.append("WHERE publish=true AND m.category_pk_id = "+ mainid
					+ " AND "
					+ " s.source_pk_id = "+ sourceid
					+" AND "
					+ " LOWER(p.title) LIKE LOWER('%"+ title +"%') ");
			return buffer.toString();
		}
		
		else if(!title.equals("null") && price > 0 && start_price >= 0 && mainid > 0 && sourceid <= 0 && subid >= 0)
		{
			buffer.append("WHERE publish=true AND m.category_pk_id = "+ mainid
					+" AND "
					+" LOWER(p.title) LIKE LOWER('%"+ title +"%') "
					+" AND "
					+" p.price BETWEEN "+ start_price +" "
					+" AND "+ price);
			return buffer.toString();
		}
		else if(!title.equals("null") && price > 0 && start_price >= 0 && sourceid > 0 && mainid <= 0 && subid >= 0)
		{
			buffer.append("WHERE publish=true AND s.source_pk_id = "+ sourceid
					+" AND "
					+" LOWER(p.title) LIKE LOWER('%"+ title +"%') "
					+" AND "
					+" p.price BETWEEN "+ start_price +" "
					+" AND "+ price);
			return buffer.toString();
		}
		//**
		
		else if(subid > 0 && start_price > 0 && price <= 0 && sourceid <= 0 && title.equals("null") && mainid <= 0)
		{
			buffer.append(" WHERE publish=true AND "
								+ "p.sub_two_fk_id = "+subid
								+ " AND p.price = "+ start_price);
			return buffer.toString();
		}
		else if(subid > 0 && start_price >= 0 & price > 0 && sourceid > 0 && title.equals("null") && mainid <= 0)
		{
			buffer.append(" WHERE publish=true AND "
								+ "p.sub_two_fk_id = "+subid
								+ " AND "
								+ " s.source_pk_id = "+sourceid
								+ " AND p.price BETWEEN "+ start_price +" "
								+ "AND "+ price);
			return buffer.toString();
		}	
		else if(subid > 0 && start_price >= 0 & price > 0 && sourceid <= 0 && title.equals("null") && mainid <= 0)
		{
			buffer.append(" WHERE publish=true AND "
								+ "p.sub_two_fk_id = "+subid
								+ " AND p.price BETWEEN "+ start_price +" "
								+ "AND "+ price);
			return buffer.toString();
		}	
		else if(sourceid > 0 && subid > 0 && start_price <= 0 && price <= 0 && title.equals("null") && mainid <= 0)
		{
			buffer.append(" WHERE publish=true AND "
							+ " p.sub_two_fk_id = "+subid
							+ " AND "
							+ "s.source_pk_id = "+sourceid);
			return buffer.toString();
		}
		else if(subid > 0 && sourceid <= 0 && start_price <= 0 && price <= 0 && title.equals("null") && mainid <= 0)
		{
			buffer.append(" WHERE publish=true AND "
					+ " p.sub_two_fk_id = "+subid);
			return buffer.toString();
		}else{
			buffer.append(" WHERE publish=true");
		}
		return buffer.toString();
	}
	
	
	/**
	 * Allow client to find product by price
	 * @return sql statement as String
	 */
	public static String findProductByWebSite(int userId, int sourceid,int subcategoryid, Pagination pagin){
		StringBuilder buffer = new StringBuilder();
		
		buffer.append(" SELECT "
						+"		p.product_pk_id, " 
						+"		p.title, "
						+"		p.price, "
						+"		p.image, "
						+ "		p.images, "
						+"		p.url, "
						+"		p.date_create, "
						+"		s2.category_name, "
						+"		s.domain, "
						+ "		s.logo, ");
						if(userId > 0){
							buffer.append("COALESCE(F.user_fk_id, 0) AS user_fk_id ");
						}else{
							buffer.append("0 AS user_fk_id ");
						}
						buffer.append(" FROM "
						+"		phsar_product p "
						+" INNER JOIN "
						+"		phsar_sub_two_category s2 "
						+" ON "
						+"		p.sub_two_fk_id = s2.sub_two_pk_id "
						+" INNER JOIN "
						+"		phsar_source_category sc "
						+" ON "
						+"		p.source_category_fk_id = sc.source_category_pk_id "
						+" INNER JOIN "
						+"		phsar_source s "
						+" ON "
						+"		sc.source_fk_id = s.source_pk_id ");
						if(userId > 0){
							buffer.append("LEFT JOIN phsar_farvorite F ON p.product_pk_id = F.product_fk_id AND F.user_fk_id =#{userId} ");
						}
			
			if(subcategoryid <= 0 ){
				buffer.append(" WHERE publish=true AND s.source_pk_id = #{sourceid}"								
								+" ORDER BY p.product_pk_id DESC "
							
								+"	LIMIT "
								+"		#{pagin.limit} "
								
								+"	OFFSET "
								+"		#{pagin.offset}");
			}else{
				buffer.append(" WHERE publish=true AND s.source_pk_id = #{sourceid}"
						+" AND "
						+" sc.sub_two_fk_id = #{subcategoryid}"
						
						+" ORDER BY p.product_pk_id DESC "
					
						+"	LIMIT "
						+"		#{pagin.limit} "
						
						+"	OFFSET "
						+"		#{pagin.offset}");
			}
					
		return buffer.toString();
	} 
	
	
	/**
	 * count product for method findProductByWebSite
	 * @return sql statement as String
	 */
	public static String countProductByFindWebSite(){
		String select = ""
						+" SELECT " 
						+"		 "
						+"		"
						+"		 "
						+"		COUNT(*) "
						+"		 "
						+"		 "
						+"		 "
						+" FROM "
						+"		phsar_product p "
						+" INNER JOIN "
						+"		phsar_sub_two_category s2 "
						+" ON "
						+"		p.sub_two_fk_id = s2.sub_two_pk_id "
						+" INNER JOIN "
						+"		phsar_source_category sc "
						+" ON "
						+"		p.source_category_fk_id = sc.source_category_pk_id "
						+" INNER JOIN "
						+"		phsar_source s "
						+" ON "
						+"		sc.source_fk_id = s.source_pk_id "
						+" WHERE publish=true AND "
						+ "s.source_pk_id = #{sourceid} "
						+ " AND "
						+" sc.sub_two_fk_id = #{subcategoryid}";
		return select;
	} 
	
	/**
	 * Select All Products in Current-Date
	 * @return
	 */
	public static String getProductsByCurrentDate(String current_date)
	{
		String select = "SELECT "
						+"		p.product_pk_id," 
						+"		p.title, "
						+"		p.price, "
						+"		p.image ,"
						+ "		p.images, "
						+ "		s.logo "
						+" FROM "
						+"		phsar_product p "
						+" INNER JOIN "
						+"		phsar_sub_two_category s2 "
						+" ON "
						+"		p.sub_two_fk_id = s2.sub_two_pk_id "
						+" INNER JOIN "
						+"		phsar_source_category sc "
						+" ON "
						+"		p.source_category_fk_id = sc.source_category_pk_id "
						+" INNER JOIN "
						+"		phsar_source s "
						+" ON "
						+"		sc.source_fk_id = s.source_pk_id "
						+" WHERE publish=true AND "
						+"		p.date_create = '"+current_date+"'"
						+" ORDER BY p.product_pk_id DESC ";
		return select;
	}
	
	
	public static String getProductByHistory(int userId)
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append("SELECT "
					+ "DISTINCT p.product_pk_id, "
					+ "p.title, "
					+ "p.price, "
					+ "p.image, "
					+ "p.images, "
					+ "p.url, "
					+ "p.date_create, "
					+ "p.description, "
					+ "s2.category_name, "
					+ "sc.source_category, "
					+ "s.logo, "
					+ "s.domain, ");
					if(userId > 0){
						buffer.append("COALESCE(F.user_fk_id, 0) AS user_fk_id ");
					}else{
						buffer.append("0 AS user_fk_id ");
					}
		buffer.append(" FROM "
					+ "phsar_product p "
					+ "INNER JOIN "
					+ "phsar_history h "
					+ "ON "
					+ "p.product_pk_id = h.product_fk_id "
					+ "INNER JOIN "
						+ "phsar_sub_two_category s2 "
					+ "ON "
						+ "p.sub_two_fk_id = s2.sub_two_pk_id  "
					+ "INNER JOIN "
						+ "phsar_source_category sc "
					+ "ON "
						+ "p.source_category_fk_id = sc.source_category_pk_id "
					+ "INNER JOIN "
						+ "phsar_source s "
					+ "ON "
						+ "s.source_pk_id = sc.source_fk_id	");
					if(userId > 0){
						buffer.append("LEFT JOIN phsar_farvorite F ON p.product_pk_id = F.product_fk_id AND F.user_fk_id =#{userId} ");
					}
			buffer.append(" ORDER BY p.product_pk_id DESC "
						
					+"	LIMIT "
					+"		#{pagin.limit} "
								
					+"	OFFSET "
					+"		#{pagin.offset}");
		return buffer.toString();
	}
	
	public static String countProductByHistory()
	{
		String sql = "SELECT "
					+"COUNT(*) "
					+"FROM "
					+ "phsar_product p "
					+ "INNER JOIN "
					+ "phsar_history h "
					+ "ON "
					+ "p.product_pk_id = h.product_fk_id "
					+ "INNER JOIN "
						+ "phsar_sub_two_category s2 "
					+ "ON "
						+ "p.sub_two_fk_id = s2.sub_two_pk_id  "
					+ "INNER JOIN "
						+ "phsar_source_category sc "
					+ "ON "
						+ "p.source_category_fk_id = sc.source_category_pk_id "
					+ "INNER JOIN "
						+ "phsar_source s "
					+ "ON "
						+ "s.source_pk_id = sc.source_fk_id	";
	return sql;
	}
	
}
