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
import org.springframework.web.bind.annotation.RestController;

import com.knongdai.tinh.controller.rest.message.ResponseMessage;
import com.knongdai.tinh.entities.MainCategory;
import com.knongdai.tinh.services.MainCategoryService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MainCategoryRController {

	@Autowired
	private MainCategoryService mcService;

	@RequestMapping(method = RequestMethod.GET, path = "/main-category/", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getAllMainCategories() {
		ArrayList<MainCategory> lists = mcService.getAllMainCategory();
		Map< String , Object> map = new HashMap<String , Object>();
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
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	/**
	 * Allow client to get sub-category as lists
	 * @return main-category as JSON
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/main-category/get-sub-category-list/", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getSubCategoryAsLists() {
		List<MainCategory> lists = mcService.getSubCategoryAsLists();
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (lists != null){
				map.put("MESSAGE", "Success");
				map.put("STATUS", true);
				map.put("DATA", lists);
			}else{
				map = ResponseMessage.onResponseMessageToClient(false, "Unsuccess");
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/main-category/", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> addMainCategory(@RequestBody MainCategory mc) {
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (mcService.addMainCatetory(mc) >= 1){
				map = ResponseMessage.onResponseMessageToClient(true, "Success");
			}else{
				map = ResponseMessage.onResponseMessageToClient(false, "Unsuccess");
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/main-category/{id}", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> deleteMainCategoryById(@PathVariable int id) {
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (mcService.deleteMainCategoryById(id) >= 1){
				map = ResponseMessage.onResponseMessageToClient(true, "Success");
			}else{
				map = ResponseMessage.onResponseMessageToClient(false, "Unsuccess");
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/main-category/", headers = "Accept=application/json")
	public ResponseEntity<Map<String,Object>> updateMainCategory(@RequestBody MainCategory mc) {
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (mcService.updateMainCategory(mc) >= 1){
				map = ResponseMessage.onResponseMessageToClient(true, "Success");
			}else{
				map = ResponseMessage.onResponseMessageToClient(false, "Unsuccess");
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
	}
}
