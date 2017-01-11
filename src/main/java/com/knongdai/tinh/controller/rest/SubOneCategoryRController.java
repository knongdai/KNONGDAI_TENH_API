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
import com.knongdai.tinh.entities.SubOneCategory;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.services.SubOneCategoryService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class SubOneCategoryRController {
	@Autowired
	private SubOneCategoryService scService;

	@RequestMapping(method = RequestMethod.GET, path = "/sub-one-category", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getAllSubOneCategory(
									@RequestParam(required=false, defaultValue="0", name="page") int page,
									@RequestParam(required=false, defaultValue="0", name="limit") int limit
																	){
		Pagination pagin = new Pagination();
		pagin.setPage(page);
		pagin.setLimit(limit);
		ArrayList<SubOneCategory> lists = scService.getAllSubCategory(pagin);
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

	@RequestMapping(method = RequestMethod.POST, path = "/sub-one-category/", headers = "Accept=application/json")
	public ResponseEntity<Map<String,Object>> createSubCategory(@RequestBody SubOneCategory sc) {
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (scService.createSubCatetory(sc) >= 1){
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

	@RequestMapping(method = RequestMethod.DELETE, path = "/sub-one-category/{id}", headers = "Accept=application/json")
	public ResponseEntity<Map<String,Object>> deleteSubCategoryById(@PathVariable int id) {
		
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (scService.deleteSubOneCategoryById(id) >= 1){
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

	@RequestMapping(method = RequestMethod.PUT, path = "/sub-one-category/", headers = "Accept=application/json")
	public ResponseEntity<Map<String,Object>> updateSubOneCategory(@RequestBody SubOneCategory sc) {
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (scService.updateSubCategory(sc) >= 1){
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
