package com.knongdai.tinh.controller.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.knongdai.tinh.entities.Product;
import com.knongdai.tinh.entities.ProductType;
import com.knongdai.tinh.entities.SubTwoCategory;
import com.knongdai.tinh.entities.filter.ProductTempFilter;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.services.ProductTypeService;
import com.knongdai.tinh.services.SubTwoCategoryService;
import com.knongdai.tinh.services.v1.ProductTempService;

@RestController
@RequestMapping("/api/v1/tenh/temp")
public class ProductTempController {
	
	@Autowired
	private SubTwoCategoryService subTwoService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private ProductTempService productTempService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findAllTemp(ProductTempFilter filter, Pagination paging){
		System.out.println(paging + ", " + filter);
		List<Product> data = productTempService.findAllTemp(filter, paging);
		
		System.out.println("DATA: " + data);
		
		Map<String, Object> response = new HashMap<>();
		response.put("pagination", paging);
		response.put("data", data);
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findAllTempDashboard(ProductTempFilter filter, Pagination paging){
		System.out.println(paging + ", " + filter);
		
		List<SubTwoCategory> categories = subTwoService.getAllSubTwoCategory(new Pagination(1, 100));
		
		List<ProductType> productTypes = productTypeService.getAll();
		
		List<Product> data = productTempService.findAllTemp(filter, paging);
		
		System.out.println("DATA: " + data);
		
		Map<String, Object> response = new HashMap<>();
		response.put("pagination", paging);
		response.put("data", data);
		response.put("categories", categories);
		response.put("productTypes", productTypes);
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> saveAllProducts(@RequestBody List<Product> products){
		System.out.println("UPDATE ALL:" + products);
		Map<String, Object> response = new HashMap<>();
		if(productTempService.save(products)){
			response.put("STATUS", true);
			response.put("MESSAGE", "Updated successfully!");
		}else{
			response.put("STATUS", false);
			response.put("MESSAGE", "Failed to update!");
		}
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
}
