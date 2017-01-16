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
import com.knongdai.tinh.entities.ProductType;
import com.knongdai.tinh.services.ProductTypeService;
import com.phearun.utility.Response;
import com.phearun.utility.ResponseList2;
import com.phearun.utility.ResponseRecord;

@RestController
@CrossOrigin
@RequestMapping("/api/product-type")
public class ProductTypeRController {
	
	@Autowired
	private ProductTypeService pts;
	
	@RequestMapping(path = "/get-all", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getAllProductType() {
		ArrayList<ProductType> lists = pts.getAll();
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
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseList2<ProductType> findAll(@RequestParam(value="type", required=false) String type){
		List<ProductType> data = pts.findAll(type);
		return new ResponseList2<>(data);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Response save(@RequestBody ProductType productType){
		boolean status = pts.save(productType);
		return new Response(status);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public Response remove(@PathVariable("id") int id){
		boolean status = pts.remove(id);
		return new Response(status);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Response update(@RequestBody ProductType productType){
		boolean status = pts.update(productType);
		return new Response(status);
	}
	

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseRecord<ProductType> findOne(@PathVariable("id") int id){
		ProductType data = pts.findOne(id);
		return new ResponseRecord<ProductType>(data);
	}
	
	
	
}
