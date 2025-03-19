package com.example.eTicaret.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateShoppingCardRequest {
	
	private int user_id;
	private int product_id;

}
