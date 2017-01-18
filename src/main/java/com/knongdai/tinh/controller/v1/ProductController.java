package com.knongdai.tinh.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.knongdai.tinh.entities.Product;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.entities.v1.ProductFilter;
import com.knongdai.tinh.services.v1.ProductServiceV1;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.phearun.utility.ResponseList;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("/api/v1/tenh/product")
public class ProductController {

	@Autowired
	private ProductServiceV1 productService;
	
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "title", required = false, dataType = "int", paramType = "query", value="Filter by title"),
		@ApiImplicitParam(name = "keyword", required = false, dataType = "int", paramType = "query", value="Filter by tag"),
        @ApiImplicitParam(name = "categoryId", required = false, dataType = "int", paramType = "query",value="Filter by category id"),
        @ApiImplicitParam(name = "websiteId", required = false, dataType = "int", paramType = "query", value="Filter by website id"),
        @ApiImplicitParam(name = "userId", required = false, dataType = "int", paramType = "query", value="Filter by user id"),
        @ApiImplicitParam(name = "productTypeId", required = false, paramType = "query", dataType = "int", value="Filter by product type id"),
        @ApiImplicitParam(name = "maxPrice", required = false, paramType = "query", dataType = "double", value="Filter by max price"),
        @ApiImplicitParam(name = "minPrice", required = false, dataType = "double", paramType = "query", value="Filter by min price"),
        @ApiImplicitParam(name = "page", required = false, dataType = "int", paramType = "query", defaultValue="1", value="current page"),
        @ApiImplicitParam(name = "limit", required = false, dataType = "int", paramType = "query", defaultValue="10", value="limit of records"),
	})
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseList<Product> findAll(@ApiIgnore ProductFilter filter, @ApiIgnore Pagination paging){
		System.out.println("=>" + filter);
		List<Product> products = productService.findAll(filter, paging);
		return new ResponseList<>(products, paging);
	}
	
}
