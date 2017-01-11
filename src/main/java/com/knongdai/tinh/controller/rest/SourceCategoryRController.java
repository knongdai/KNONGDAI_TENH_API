package com.knongdai.tinh.controller.rest;

import java.util.ArrayList;
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
import com.knongdai.tinh.entities.SourceCategory;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.services.SourceCategoryService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class SourceCategoryRController {
	@Autowired
	private SourceCategoryService scr;

	/**
	 * Get all source-category
	 * @return SOURCE-CATEGORY AS OBJECT JSON
	 */
	
	@RequestMapping(method = RequestMethod.GET, path = "/source-category", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getAllSourceCategory(
			@RequestParam(required=false, defaultValue="0", name="page") int page,
			@RequestParam(required=false, defaultValue="0", name="limit") int limit
												){
		Pagination pagin = new Pagination();
		pagin.setPage(page);
		pagin.setLimit(limit);
		ArrayList<SourceCategory> lists = scr.getAllSourceCategory(pagin);
		Map< String , Object> map = new HashMap<String , Object>();
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
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	/**
	 * Get By Id
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/source-category/get-by-id/{id}", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getById(@PathVariable int id){
		
		ArrayList<SourceCategory> lists = scr.getById(id);
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
	 * Create new source-category
	 * @return SOURCE-CATEGORY AS OBJECT JSON
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/source-category/", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> createSourceCategory(@RequestBody SourceCategory sc) {
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (scr.createSourceCategory(sc) >= 1){
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

	/**
	 * Delete source-category by id
	 * @return SOURCE-CATEGORY AS OBJECT JSON
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = "/source-category/{id}", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> deleteSourceCategoryById(@PathVariable int id) {
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (scr.deleteSourceCategoryById(id) >= 1){
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

	/**
	 * Update source-category
	 * @return SOURCE-CATEGORY AS OBJECT JSON
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/source-category/", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> updateSourceCategory(@RequestBody SourceCategory sc) {
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (scr.updateSourceCategory(sc) >= 1){
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
