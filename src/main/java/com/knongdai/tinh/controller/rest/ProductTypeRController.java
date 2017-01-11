package com.knongdai.tinh.controller.rest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.knongdai.tinh.controller.rest.message.ResponseMessage;
import com.knongdai.tinh.entities.ProductType;
import com.knongdai.tinh.services.ProductTypeService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductTypeRController {
	
	@Autowired
	private ProductTypeService pts;
	
	@RequestMapping(path = "/product-type/get-all", method = RequestMethod.GET, headers = "Accept=application/json")
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
}
