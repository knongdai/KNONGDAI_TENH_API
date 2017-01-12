package com.knongdai.tinh.repositories.sqlbuilder;

public class ProductTmpSqlBuilder {

	public static String findProductBySubCategoryId(){
		return "SELECT "
					+ "p.product_pk_id, "
					+ "p.title, "
					+ "p.price, "
					+ "p.image, "
					+ "p.images, "
					+ "p.url, "
					+ "p.description, "
					+ "p.sub_two_fk_id, "
					+ "p.source_category_fk_id, "
					+ "source.logo "
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
					+ "INNER JOIN "
						+ "phsar_source source "
					+ "ON "
						+ "source.source_pk_id = sc.source_fk_id "
					+ "WHERE "
					+ "	s.sub_two_pk_id = #{subTwoCategory.subtwocategoryid} "
					+ "AND"
					+ "		p.publish = false"
					+ " ORDER BY "
					+ "		p.product_pk_id DESC ";
		
	}

	
	/**
	 * Search product by sub-category id
	 * @param id
	 * @return sql statement
	 */
	/*public static String findProductBySubCategoryId()
	{
		String select  = "SELECT "
						+ "p.product_pk_id, "
						+ "p.title, "
						+ "p.price, "
						+ "p.image, "
						+ "p.images, "
						+ "p.url, "
						+ "p.description, "
						+ "p.sub_two_fk_id, "
						+ "p.source_category_fk_id, "
						+ "source.logo "
					+ "FROM "
						+ "phsar_product_temperory p "
					+ "INNER JOIN "
						+ "phsar_sub_two_category s "
					+ "ON "
						+ "p.sub_two_fk_id = s.sub_two_pk_id  "
					+ "INNER JOIN "
						+ "phsar_source_category sc "
					+ "ON "
						+ "p.source_category_fk_id = sc.source_category_pk_id "
					+ "INNER JOIN "
						+ "phsar_source source "
					+ "ON "
						+ "source.source_pk_id = sc.source_fk_id "
					+ "WHERE "
					+ "	s.sub_two_pk_id = #{subTwoCategory.subtwocategoryid} "
					+ "AND "
					+ "	p.status = true"
						
					+ " ORDER BY p.product_pk_id DESC ";
		return select;
	}
*/
}
