package com.knongdai.tinh.controller.rest.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResponseMessage {
	
	/**
	 * Send Message to client when request success
	 * @param lists
	 * @return map object
	 */
	public static Map<String, Object> onSuccess(ArrayList lists){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("STATUS", true);
		map.put("MESSAGE", "Success");
		map.put("DATA", lists);
		return map;
	}
	
	/**
	 * Send Message to client when request
	 */
	public static Map<String, Object> onResponseMessageToClient(boolean status, String msg){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("STATUS", status);
		map.put("MESSAGE", msg);
		return map;
	}
	
}
