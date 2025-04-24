package com.example.eTicaret.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eTicaret.business.concretes.ShoppingCardManager;
import com.example.eTicaret.business.requests.CreateShoppingCardRequest;
import com.example.eTicaret.business.response.GetAllShoppingCardResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/api/products/shoppingCard")
public class ShoppingCartsController {

	private ShoppingCardManager shoppingCartService;
	
	@Autowired
	public ShoppingCartsController(ShoppingCardManager shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<GetAllShoppingCardResponse>> getAll() {
		List<GetAllShoppingCardResponse> all = shoppingCartService.getAll();
		return ResponseEntity.ok(all);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> add(@Valid @RequestBody CreateShoppingCardRequest createShoppingCartRequest) {
		this.shoppingCartService.add(createShoppingCartRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body("Sepete ürün eklendi");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeItemFromCart(@PathVariable @Min(value = 1, message = "ID 1'den küçük olamaz") int id) {
		shoppingCartService.deleteShoppingCart(id);
		return ResponseEntity.ok("Sepetten ürün başarıyla silindi!");
	}
}

