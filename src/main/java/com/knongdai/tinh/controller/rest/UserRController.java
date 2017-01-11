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
import com.knongdai.tinh.entities.User;
import com.knongdai.tinh.services.UserService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserRController {

	@Autowired
	private UserService userService;

	
	@RequestMapping(method = RequestMethod.GET, path = "/user/", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> getAllUsers() {
		ArrayList<User> lists = userService.getAllUsers();
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

	@RequestMapping(method = RequestMethod.GET, path = "/user/{id}", headers = "Accept=application/json")
	public ResponseEntity<ArrayList<User>> getUserById(@PathVariable int id) {
		ArrayList<User> singleUser = userService.getUserById(id);
		if (singleUser.isEmpty())
			return new ResponseEntity<ArrayList<User>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<ArrayList<User>>(singleUser, HttpStatus.OK);

	}
	
/*	@RequestMapping(path="/user/", method = RequestMethod.POST , headers = "Accept=application/json")
	public ResponseEntity<Map<String , Object>> findUserByName(@RequestBody User login){
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			map.put("STATUS",true);
			map.put("MESSAGE","Success");
			map.put("DATA", userService.findUserByName(login.getUsername()) );
				
		}catch(Exception e){
			map.put("STATUS",false);
			map.put("MESSAGE", "Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}*/

	@RequestMapping(method = RequestMethod.POST, path = "/user/register", headers = "Accept=application/json")
	public ResponseEntity<Map<String,Object>> createUser(@RequestBody User user) {
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (userService.createUser(user) >= 1){
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

	@RequestMapping(method = RequestMethod.DELETE, path = "/user/{id}", headers = "Accept=application/json")
	public ResponseEntity<Map<String,Object>> deleteUserById(@PathVariable int id) {
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (userService.deleteUserById(id) >= 1){
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

	@RequestMapping(method = RequestMethod.PUT, path = "/user/", headers = "Accept=application/json")
	public ResponseEntity<Map<String,Object>> updateUser(@RequestBody User user) {
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			if (userService.updateUser(user) >= 1){
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
	
	@RequestMapping(method = RequestMethod.GET, path = "/user/find-by-name/{username}", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> findUserByName(@PathVariable("username") String username){
		ArrayList<User> lists = userService.findUserByName(username);
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if(!lists.isEmpty()){
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
	
	// Count number of user by user hash
	@RequestMapping(method = RequestMethod.POST, path = "/user/count-user-by-user-hash/{user-hash}", headers = "Accept=application/json")
	public ResponseEntity<Map<String,Object>> countUserByUserHash(@PathVariable("user-hash") String userHash) {
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			map.put("DATA", userService.countUserByUserHash(userHash));
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/user/find-user-by-user-hash/{user-hash}", headers = "Accept=application/json")
	public ResponseEntity<Map<String, Object>> findUserByUserHash(@PathVariable("user-hash") String userHash){
		User user = userService.findUserByUserHash(userHash);
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if(user != null){
				map.put("DATA", user);
				map.put("STATUS", true);
				map.put("MESSAGE", "User found");
			}else{
				map.put("MESSAGE", "User not found");
				map.put("STATUS", false);
			}
		}catch(Exception e){
			map = ResponseMessage.onResponseMessageToClient(false,"Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
