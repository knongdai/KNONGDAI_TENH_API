package com.knongdai.tinh.controller.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.knongdai.tinh.entities.ProductTemperory;
import com.knongdai.tinh.services.ProductTmpService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductTemperoryRController {
	@Autowired
	private ProductTmpService ps;

	/**
	 * Get all temporary products
	 * @return
	 */
	@RequestMapping(path = "/product-temperory/get-all", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getAllProductTemperory() {
		ArrayList<ProductTemperory> lists = ps.getALLProductsTemperory();
		Map<String, Object> map = new HashMap<>();
		try{
			if (!lists.isEmpty()){
				map = ResponseMessage.onSuccess(lists);
			}else{
				map = ResponseMessage.onResponseMessageToClient(false, "Unsuccess");
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/product-temperory/get-by-status/{status}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getProductsByStatus(@PathVariable boolean status) {
		ArrayList<ProductTemperory> lists = ps.getProductsByStatus(status);
		Map<String, Object> map = new HashMap<>();
		try{
			if (!lists.isEmpty()){
				map = ResponseMessage.onSuccess(lists);
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
	 * Find product by category 
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/product-temperory/get-by-id", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getProductTemperoryById(@RequestParam(required=true, defaultValue="0", name="id") int id){
		
		ArrayList<ProductTemperory> lists = ps.getProductsTemperoryById(id);
		Map<String, Object> map = new HashMap<>();
		try{
			if (!lists.isEmpty()){
				map = ResponseMessage.onSuccess(lists);
				
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
	 * Create new Product temperory
	 * @param p
	 * @return product OBJECT AS JSON
	 */
	@RequestMapping(path = "/product-temperory/create-product", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> createProduct(@RequestBody List<ProductTemperory> p) {
		Map<String, Object> map = new HashMap<>();
		try{
			if (ps.saveProductTmp(p) >= 1){
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
	 * Delete products by status
	 * @param status
	 * @return
	 */
	@RequestMapping(path = "/product-temperory/delete-by-status/{status}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> deleteProductByStatus(@PathVariable boolean status) {
		Map<String, Object> map = new HashMap<>();
		try{
			if (ps.deleteProductsByStatus(status) >= 1){
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
	 * Update product by id
	 * @param id
	 * @return OBJECT PRODUCT-TEMPERORY AS JSON
	 */
	@RequestMapping(path = "/product-temperory/update-by-source-id", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> updateProductById(List<ProductTemperory> productListId) {
		System.out.println(productListId.get(0).getProductid()+" id");
		Map<String, Object> map = new HashMap<>();
		/*try{
			if (ps.updateProductById(productListId) >= 1){
				map = ResponseMessage.onResponseMessageToClient(true, "Success");
			}else{
				map = ResponseMessage.onResponseMessageToClient(false, "Unsuccess");
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}*/
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
