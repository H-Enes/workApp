package com.example.eTicaret.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eTicaret.business.abstracts.ShoppingCartService;
import com.example.eTicaret.business.requests.CreateShoppingCardRequest;
import com.example.eTicaret.business.response.GetAllShoppingCardResponse;


@RestController
@RequestMapping("/api/products/shoppingCard")
public class ShoppingCartsController {

	private ShoppingCartService shoppingCartService;
	
	@Autowired
	public ShoppingCartsController(ShoppingCartService shoppingCartService) {
		this.shoppingCartService=shoppingCartService;
	}
	
	
	@GetMapping("/getAll")
	public List<GetAllShoppingCardResponse> getAll(){
		return shoppingCartService.getAll();
	}
	
	@PostMapping
	public void add(CreateShoppingCardRequest createShoppingCartRequest) {
		this.shoppingCartService.add(createShoppingCartRequest);
	}
}
