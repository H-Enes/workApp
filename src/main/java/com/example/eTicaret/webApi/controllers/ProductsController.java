package com.example.eTicaret.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eTicaret.business.abstracts.ProductService;
import com.example.eTicaret.business.requests.CreateProductRequest;
import com.example.eTicaret.business.response.GetAllProductResponse;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
	private ProductService productService;

	@Autowired
	public ProductsController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/getAll")
	public List<GetAllProductResponse> getAll(){	
		return productService.getAll();
	}	
	@PostMapping ("/add")
	public void add(CreateProductRequest createProductRequest) {
		this.productService.add(createProductRequest);
		
	
		
	}

}
