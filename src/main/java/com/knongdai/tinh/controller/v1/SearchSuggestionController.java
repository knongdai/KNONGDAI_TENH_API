package com.knongdai.tinh.controller.v1;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.knongdai.tinh.services.v1.ProductServiceV1;
import com.phearun.utility.ResponseCollection;

@RestController
@RequestMapping("/api/v1/tenh/keywords")
public class SearchSuggestionController {

	@Autowired
	private ProductServiceV1 productService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseCollection<String> findKeywords(@RequestParam("keyword") String keyword){
		Collection<String> keywords = productService.findKeywords(keyword);
		return new ResponseCollection<>(keywords);
	}
	
}
