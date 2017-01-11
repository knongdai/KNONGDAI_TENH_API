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
import com.knongdai.tinh.entities.Source;
import com.knongdai.tinh.services.SourceService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class SourceRController {

	@Autowired
	private SourceService ss;

	@RequestMapping(method = RequestMethod.GET, path = "/source/", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getAllSources() {
		ArrayList<Source> lists = ss.getAllSources();
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

	
	@RequestMapping(method = RequestMethod.GET, path = "/source/get-object", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getAll() {
		List<Source> lists = ss.getAll();
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (lists != null){
				//map = ResponseMessage.onSuccess(lists);
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
	
	@RequestMapping(method = RequestMethod.GET, path = "/source/get-join-onetomany", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getSourceJoinOneToMany() {
		List<Source> lists = ss.getSourceJoinOneToMany();
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (!lists.isEmpty()){
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
	
	@RequestMapping(method = RequestMethod.POST, path = "/source/", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> createSource(@RequestBody Source s) {
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (ss.createSource(s) >= 1){
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

	@RequestMapping(method = RequestMethod.DELETE, path = "/source/{id}", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> deleteSourceById(@PathVariable int id) {
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (ss.deleteSourceById(id) >= 1){
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

	@RequestMapping(method = RequestMethod.PUT, path = "/source/", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> updateSource(@RequestBody Source s) {
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (ss.updateSource(s) >= 1){
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
