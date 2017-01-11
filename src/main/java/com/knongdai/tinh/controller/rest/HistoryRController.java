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
import org.springframework.web.bind.annotation.RestController;

import com.knongdai.tinh.controller.rest.message.ResponseMessage;
import com.knongdai.tinh.entities.History;
import com.knongdai.tinh.services.HistoryService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class HistoryRController {
	@Autowired
	private HistoryService hs;

	@RequestMapping(method = RequestMethod.GET, path = "/history/{userid}", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getAllHistoryByID(@PathVariable int userid) {
		ArrayList<History> lists = hs.getAllHistroy(userid);
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

	@RequestMapping(method = RequestMethod.POST, path = "/history/", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> addHistory(@RequestBody History h) {
		Map<String, Object> map = new HashMap<>();
		try{
			if (hs.createHistory(h) >= 1){
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

	@RequestMapping(method = RequestMethod.DELETE, path = "/history/{userid}/{historyid}", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> deleteHistoryById(@PathVariable int userid, @PathVariable int historyid) {
		Map<String, Object> map = new HashMap<>();
		try{
			if (hs.deleteHistoryById(userid, historyid) >= 1){
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
