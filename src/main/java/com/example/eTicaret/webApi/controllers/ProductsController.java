package com.example.eTicaret.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.eTicaret.business.concretes.ProductManager;
import com.example.eTicaret.business.requests.CreateProductRequest;
import com.example.eTicaret.business.requests.UpdateProductRequest;
import com.example.eTicaret.business.response.GetAllProductResponse;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
	private ProductManager productService;

	//@Autowired kullanmaya gerek yok constructor ile dependency injection
	public ProductsController(ProductManager productService) {
		this.productService = productService;
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<GetAllProductResponse>> getAll() {
		List<GetAllProductResponse> products = productService.getAll();
		return ResponseEntity.ok(products); // 200 OK
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> add(@RequestBody CreateProductRequest createProductRequest) {
		productService.add(createProductRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body("Ürün başarıyla eklendi");
	}	
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Ürün başarıyla silindi!");
    }
	
	@PutMapping("/{id}/update-price")
    public ResponseEntity<String> updateProductPrice(@PathVariable int id, @RequestParam double price) {
        productService.updatePrice(id, price);
        return ResponseEntity.ok("Ürün fiyatı güncelleme başarılı");
    }
	
	@PutMapping("{id}/update")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody UpdateProductRequest product) {
        productService.updateProduct(id, product);
        return ResponseEntity.ok("Ürün başarıyla güncellendi");
    }
	

}
