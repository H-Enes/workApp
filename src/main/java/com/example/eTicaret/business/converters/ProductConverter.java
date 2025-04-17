package com.example.eTicaret.business.converters;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.eTicaret.business.response.GetAllProductResponse;
import com.example.eTicaret.entities.Category;
import com.example.eTicaret.entities.Product;

public class ProductConverter {

    // Tek bir Product nesnesini dönüştürme
	public static GetAllProductResponse convertToResponse(Product product) {
		Set<String> categoryNames = new HashSet<>();
	    if (product.getCategories() != null) {
	        for (Category category : product.getCategories()) { // Direkt olarak set üzerinde iterate ediyoruz
	            categoryNames.add(category.getName());
	        }
	    }

	    return new GetAllProductResponse(
	            product.getId(),
	            product.getName(),
	            product.getStock_quantity(),
	            product.getPrice(),
	            categoryNames
	    );
	}

    // Listeyi dönüştürme
    public static List<GetAllProductResponse> convertToResponseList(List<Product> productList) {
        return productList.stream()
                .map(ProductConverter::convertToResponse)
                .collect(Collectors.toList());
    }
}
