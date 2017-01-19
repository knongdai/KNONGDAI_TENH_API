package com.knongdai.tinh.controller.v1;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.knongdai.tinh.services.v1.ProductServiceV1;
import com.phearun.utility.ResponseSet2;

@RestController
@RequestMapping("/api/v1/tenh/keywords")
public class SearchSuggestionController {

	@Autowired
	private ProductServiceV1 productService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseSet2<String> findKeywords(@RequestParam("keyword") String keyword){
		Set<String> keywords = productService.findKeywords(keyword);
		return new ResponseSet2<>(keywords);
	}
	
}
