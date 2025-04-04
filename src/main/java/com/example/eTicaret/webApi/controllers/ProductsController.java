package com.example.eTicaret.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.eTicaret.business.abstracts.ProductService;
import com.example.eTicaret.business.requests.CreateProductRequest;
import com.example.eTicaret.business.requests.UpdateProductRequest;
import com.example.eTicaret.business.response.GetAllProductResponse;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
	private ProductService productService;

	//@Autowired kullanmaya gerek yok constructor ile dependency injection
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
	
	@DeleteMapping("/{id}")
	   public String deleteProduct(@PathVariable int id) {
	   productService.deleteProduct(id);
	   return "Ürün başarıyla silindi!";
	}
	@PutMapping("/{id}/update-price")
	public String updateProductPrice(@PathVariable int id, @RequestParam double price) {
		productService.updatePrice(id, price);
		return "Ürün fiyatı güncelleme başarılı";
	}
	
	@PutMapping("{id}/update")
	public String updateProduct(@PathVariable int id, @RequestBody UpdateProductRequest product) {
		productService.updateProduct(id, product);
		return "Ürün başarıyla güncellendi";
	}
	

}
