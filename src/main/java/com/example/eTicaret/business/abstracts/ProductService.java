package com.example.eTicaret.business.abstracts;

import java.util.List;

import com.example.eTicaret.business.requests.CreateProductRequest;
import com.example.eTicaret.business.requests.UpdateProductRequest;
import com.example.eTicaret.business.response.GetAllProductResponse;

public interface ProductService {
	
	List<GetAllProductResponse> getAll();
	void add(CreateProductRequest createProductRequest);
	void deleteProduct(int id);
	void updatePrice(int id, double price);
	void updateProduct(int id, UpdateProductRequest updateProductRequest);

}
