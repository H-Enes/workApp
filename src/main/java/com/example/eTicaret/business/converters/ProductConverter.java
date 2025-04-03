package com.example.eTicaret.business.converters;
import java.util.List;
import java.util.stream.Collectors;

import com.example.eTicaret.business.response.GetAllProductResponse;
import com.example.eTicaret.entities.concretes.Product;

public class ProductConverter {

	    // Tek bir Product nesnesini dönüştürme
	    public static GetAllProductResponse convertToResponse(Product product) {
	        return new GetAllProductResponse(
	                product.getId(),
	                product.getName(),
	                product.getStock_quantity(),
	                product.getPrice()       
	        );
	    }

	    // Listeyi dönüştürme
	    public static List<GetAllProductResponse> convertToResponseList(List<Product> productList) {
	        return productList.stream()
	                .map(ProductConverter::convertToResponse)
	                .collect(Collectors.toList());
	    }
}
	

