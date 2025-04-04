package com.example.eTicaret.business.requests;

import lombok.Data;

@Data
public class UpdateProductRequest {
	private String name;
    private Double price;
    private Integer stock_quantity;
}
