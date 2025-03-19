package com.example.eTicaret.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllShoppingCardResponse {
	private int id;
	private int user_id;
	private int product_id;
}
