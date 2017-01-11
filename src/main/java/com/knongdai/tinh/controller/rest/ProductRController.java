package com.knongdai.tinh.controller.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.knongdai.tinh.controller.rest.message.ResponseMessage;
import com.knongdai.tinh.entities.Product;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.services.ProductService;
import com.knongdai.tinh.services.ProductTmpService;



@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductRController {

	@Autowired
	private ProductService ps;
	
	@Autowired
	private ProductTmpService ptr;
	
	/**
	 * Call Pagination OBJECT to set limit page found... 
	 */
	Pagination pagin = new Pagination();

	/**
	 * Get product to compare
	 */
	@RequestMapping(path = "/product/get-compare/{product_type_id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getProductToCompare(@PathVariable("product_type_id") int productid ){
		ArrayList<Product> lists = ps.getProductToCompare(productid);
		Map<String, Object> map = new HashMap<>();
		try{
			if (!lists.isEmpty()){
				map.put("MESSAGE", "Success");
				map.put("STATUS", true);
				map.put("DATA", lists);
				map.put("PAGING", pagin);
			}else{
				map = ResponseMessage.onResponseMessageToClient(false, "Unsuccess");
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}	
	
	/**
	 * Get product Detail by id
	 */
	@RequestMapping(path = "/product/get-detail/{productid}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getDetailProduct(
			@RequestParam(required=false, defaultValue="0", name="userid") int userid,
			@PathVariable int productid ){
		Product productDetail = new Product();
		productDetail = ps.getDetailProductById(userid, productid);
		Map<String, Object> map = new HashMap<>();
		try{
			if (productDetail != null){
				map.put("MESSAGE", "Success");
				map.put("STATUS", true);
				map.put("DATA", productDetail);
				map.put("PAGING", pagin);
			}else{
				map = ResponseMessage.onResponseMessageToClient(false, "Unsuccess");
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	/**
	 * Allow client to get all products 
	 * @return product as json data
	 */
	@RequestMapping(path = "/product/get-all-products", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getALLProducts(
			@RequestParam(required=false, defaultValue="0", name="userid") int userid,
			@RequestParam(required=false, defaultValue="1", name="page") int page,
			@RequestParam(required=false, defaultValue="10", name="limit") int limit
												) {
		pagin.setPage(page);
		pagin.setLimit(limit);
		ArrayList<Product> lists = ps.getALLProducts(userid, pagin);
		Map<String, Object> map = new HashMap<>();
		try{
			if (!lists.isEmpty()){
				map.put("MESSAGE", "Success");
				map.put("STATUS", true);
				map.put("DATA", lists);
				map.put("PAGING", pagin);
			}else{
				map = ResponseMessage.onResponseMessageToClient(false, "Unsuccess");
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		
		System.out.println("ALL: " + lists);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	
	@RequestMapping(path = "/product/get-by-history", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getProductsByHistory(
			@RequestParam(required=false, defaultValue="0", name="userid") int userid,
			@RequestParam(required=false, defaultValue="1", name="page") int page,
			@RequestParam(required=false, defaultValue="10", name="limit") int limit
												) {
		pagin.setPage(page);
		pagin.setLimit(limit);
		ArrayList<Product> lists = ps.getProductByHistory(userid, pagin);
		Map<String, Object> map = new HashMap<>();
		try{
			if (!lists.isEmpty()){
				map.put("MESSAGE", "Success");
				map.put("STATUS", true);
				map.put("DATA", lists);
				map.put("PAGING", pagin);
			}else{
				map = ResponseMessage.onResponseMessageToClient(false, "Unsuccess");
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	/**
	 * All client to get search product in current date-time
	 * @param current_date
	 * @return product as json data
	 */
	@RequestMapping(path = "/product/get-by-current-date", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getProductsByCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String getdate = dateFormat.format(date).toString();
		ArrayList<Product> lists = ps.getProductsByCurrentDate(getdate);
		Map<String, Object> map = new HashMap<>();
		try{
			if (!lists.isEmpty()){
				map = ResponseMessage.onSuccess(lists);
			}else{
				map = ResponseMessage.onResponseMessageToClient(false, "Unsuccess");
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false, "Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	/**
	 * Allow client to get all products 
	 * @return product as json data
	 */
	@RequestMapping(path = "/product/find-product-by-sub-category", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> findProductBySubCategory(
												@RequestParam(required=false, defaultValue="0", name="userid") int userid, 
												@RequestParam(required=false, defaultValue="0", name="id") int id, 
												@RequestParam(required=false, defaultValue="1", name="page") int page,
												@RequestParam(required=false, defaultValue="10", name="limit") int limit){
		
		pagin.setPage(page);
		pagin.setLimit(limit);
		ArrayList<Product> lists = ps.findProductBySubCategory(userid, id, pagin);
		Map<String, Object> map = new HashMap<>();
		try{
			if (!lists.isEmpty()){
				map.put("MESSAGE", "Success");
				map.put("STATUS", true);
				map.put("DATA", lists);
				map.put("PAGING", pagin);
			}else{
				map = ResponseMessage.onResponseMessageToClient(false, "Unsuccess");
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	
	/**
	 * Allow client to find product by website 
	 * @return PRODUCT OBJECT AS JSON
	 */
	@RequestMapping(path = "/product/find-product-by-website", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> findProductByWebSite(
															@RequestParam(required=true, defaultValue="0", name="userid") int userid,
															@RequestParam(required=true, defaultValue="0", name="sourceid") int sourceid, 
															@RequestParam(required=true, defaultValue="0", name="subcategoryid") int subcategoryid,
															@RequestParam(required=false, defaultValue="1", name="page") int page,
															@RequestParam(required=false, defaultValue="10", name="limit") int limit
																	){
		pagin.setPage(page);
		pagin.setLimit(limit);
		ArrayList<Product> lists = ps.findProductByWebSite(userid, sourceid,subcategoryid, pagin);
		Map<String, Object> map = new HashMap<>();
		try{
			if (!lists.isEmpty()){
				map.put("MESSAGE", "Success");
				map.put("STATUS", true);
				map.put("DATA", lists);
				map.put("PAGING", pagin);
			}else{
				map = ResponseMessage.onResponseMessageToClient(false, "Unsuccess");
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	
	/**
	 * Allow client to find product by set price
	 * @return PRODUCT OBJECT AS JSON
	 */
	@RequestMapping(path = "/product/find-product-by-filter", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> findProductByFilter(
													@RequestParam(required=true, defaultValue="0", name="userid") int userid,
													@RequestParam(required=true, defaultValue="0", name="mainid") int mainid,
													@RequestParam(required=false, defaultValue="null", name="title") String title,
													@RequestParam(required=true, defaultValue="0", name="subid") int subid,
													@RequestParam(required=true, defaultValue="0", name="sourceid") int sourceid,
													@RequestParam(required=true, defaultValue="0.0", name="start_price") double start_price,
													@RequestParam(required=true, defaultValue="0.0", name="price") double price,
													@RequestParam(required=false, defaultValue="1", name="page") int page,
													@RequestParam(required=false, defaultValue="10", name="limit") int limit
												){
		
		pagin.setPage(page);
		pagin.setLimit(limit);
		ArrayList<Product> lists = ps.findProductByFilter(userid, mainid, title, subid, sourceid, start_price, price, pagin);
		Map<String, Object> map = new HashMap<>();
		try{
			if (!lists.isEmpty()){
				map.put("MESSAGE", "Success");
				map.put("STATUS", true);
				map.put("DATA", lists);
				map.put("PAGING", pagin);
			}else{
				map = ResponseMessage.onResponseMessageToClient(false, "Unsuccess");
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Allow client to find product by filter
	 * @return PRODUCT OBJECT AS JSON
	 */
	@RequestMapping(path = "/product/find-product-by-title", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> findProductsByTitle(
				@RequestParam(required=true, defaultValue="0", name="userid") int userid,
				@RequestParam(required=true, defaultValue="0", name="category_id") int category_id, 
				@RequestParam(required=false, defaultValue="null", name="title") String title, 
				@RequestParam(required=true, defaultValue="0", name="page") int page,
				@RequestParam(required=true, defaultValue="0", name="limit") int limit
				) {
		pagin.setPage(page);
		pagin.setLimit(limit);
		ArrayList<Product> lists = ps.findProductsByTitle(userid, category_id, title, pagin);
		Map<String, Object> map = new HashMap<>();
		try{
			if (!lists.isEmpty()){
				map.put("MESSAGE", "Success");
				map.put("STATUS", true);
				map.put("DATA", lists);
				map.put("PAGING", pagin);
			}else{
				map = ResponseMessage.onResponseMessageToClient(false, "Unsuccess");
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	} 
	
	
	/**
	 * Create new product with multiple OBJECT
	 * @param p
	 * @return PRODUCT OBJECT AS JSON
	 */
	
	@RequestMapping(path = "/product/", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> createProduct(@RequestBody ArrayList<Product> p) {
		
		Map<String, Object> map = new HashMap<>();
		try{
			if (ps.addProducts(p) >= 1){
				ptr.updateProductById(p);
				map = ResponseMessage.onResponseMessageToClient(true, "Success");
			}else{
				map = ResponseMessage.onResponseMessageToClient(false, "Unsuccess");
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	/**
	 * Delete product by id
	 * @param id
	 * @return PRODUCT OBJECT AS JSON
	 */
	@RequestMapping(path = "/product/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> deleteProductById(@PathVariable int id) {
		Map<String, Object> map = new HashMap<>();
		try{
			if (ps.deleteProductById(id) >= 1){
				map = ResponseMessage.onResponseMessageToClient(true, "Success");
			}else{
				map = ResponseMessage.onResponseMessageToClient(false, "Unsuccess");
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.CREATED);
	}
	
}
