package com.knongdai.tinh.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knongdai.tinh.entities.Product;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.repositories.ProductReposity;
import com.knongdai.tinh.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductReposity ps;
	
	@Override
	public ArrayList<Product> getALLProducts(int userid, Pagination pagin) {
		try{
			pagin.setTotalCount(ps.countALLProducts());
			return ps.getALLProducts(userid, pagin);
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public int deleteProductById(int id) {
		return ps.deleteProductById(id);
	}
	
	@Override
	public ArrayList<Product> findProductsByTitle(int userid, int category_id, String title, Pagination pagin) {
		try{
			pagin.setTotalCount(ps.countProductsByTitle(category_id, title));
			return ps.findProductsByTitle(userid, category_id, title, pagin);
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public ArrayList<Product> findProductBySubCategory(int userid, int id, Pagination pagin) {

		try{
			pagin.setTotalCount(ps.countProductBySubCategory(id));
			return ps.findProductBySubCategory(userid, id, pagin);
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		return null;
	}
	
	/*@Override
	public ArrayList<Product> findProductBySetPrice(int subid, String start_price, String price, Pagination pagin) {
		try{
			pagin.setTotalCount(ps.countProductBySetPrice(subid, start_price,price));
			return ps.findProductBySetPrice(subid, start_price,price , pagin);
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		return null;
		
	}*/
	
	
	
	@Override
	public int addProducts(ArrayList<Product> products) {
		try{			
			return ps.addProducts(products);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public ArrayList<Product> getProductsByCurrentDate(String current_date) {
		return ps.getProductsByCurrentDate(current_date);
	}

	@Override
	public ArrayList<Product> findProductByWebSite(int userid, int id,int subid, Pagination pagin) {
		try{
			pagin.setTotalCount(ps.countProductByFindWebSite(id, subid));
			return ps.findProductByWebSite(userid, id, subid, pagin);
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Product> findProductByFilter(int userid, int mainid, String title, int subid, int sourceid, double start_price, double price,
			Pagination pagin) {
	
		try{
			pagin.setTotalCount(ps.countProductByFilter(mainid, title, subid, sourceid, start_price, price));
			return ps.findProductByFilter(userid, mainid, title,subid, sourceid, start_price, price, pagin);
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Product> getProductByHistory(int userId, Pagination pagin) {
		try{
			pagin.setTotalCount(ps.countProductByHistory());
			return ps.getProductByHistory(userId, pagin);
			
		}catch(Exception e){	
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Product getDetailProductById(int userid, int id) {
		return ps.getDetailProductById(userid, id);
	}


	@Override
	public ArrayList<Product> getProductToCompare(int productid) {
		return ps.getProductToCompare(productid);
	}
	
}
