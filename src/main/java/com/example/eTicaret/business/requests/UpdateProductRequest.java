package com.example.eTicaret.business.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateProductRequest {
	@NotBlank(message = "Ürün adı boş olamaz")
	private String name;
	
	@Min(value = 0, message = "Ürün fiyatı 0 dan küçük olamaz")
    private Double price;
    
	@Min(value = 0, message = "Ürün sayısı 0 dan küçük olamaz")
    private Integer stock_quantity;
}
