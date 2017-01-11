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
import com.knongdai.tinh.entities.Farvorite;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.services.FarvoriteService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FarvoriteRController {
	
	@Autowired
	private FarvoriteService fs;
	Pagination pagin = new Pagination();
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/farvorite", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getFarvoriteByUserId(
				@RequestParam(required=true, defaultValue="0", name="usreid") int userid,
				@RequestParam(required=true, defaultValue="0", name="page") int page,
				@RequestParam(required=true, defaultValue="0", name="limit") int limit
			) {
		pagin.setPage(page);
		pagin.setLimit(limit);
		
		ArrayList<Farvorite> lists = fs.getFarvoriteByUserId(userid, pagin);
		System.out.println(pagin.getTotalCount()+"total");
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

	@RequestMapping(method = RequestMethod.POST, path = "/farvorite/add-new", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> addFarvorite(@RequestBody Farvorite f) {
		System.out.println(f.getUser().getUserid());
		Map<String, Object> map = new HashMap<>();
		try{
			if (fs.addFarvorite(f) >= 1){
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

	@RequestMapping(method = RequestMethod.DELETE, path = "/farvorite/{userid}/{farvoriteid}", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> deleteFarvoriteById(@PathVariable int userid,@PathVariable int farvoriteid) {
		Map<String, Object> map = new HashMap<>();
		System.out.println(userid+""+farvoriteid);
		try{
			if (fs.deleteFarvoriteById(userid, farvoriteid) >= 1){
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

}
