package com.example.eTicaret.business.requests;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
	@NotBlank(message = "Ürün adı boş olamaz")
	private String name;
	
	@Min(value = 0, message = "Ürün sayısı 0'dan küçük olamaz")
	private int stock_quantity;
	
	@Min(value = 0, message = "Ürün fiyatı 0'dan küçük olamaz")
	private double price;
	

    @NotEmpty(message = "Kategori listesi boş olamaz")
    private List<@Min(value = 1, message = "Kategori ID 1'den küçük olamaz") Integer> categoryIds;
}
