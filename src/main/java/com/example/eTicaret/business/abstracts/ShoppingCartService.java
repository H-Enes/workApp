package com.example.eTicaret.business.abstracts;

import java.util.List;

import com.example.eTicaret.business.requests.CreateShoppingCardRequest;
import com.example.eTicaret.business.response.GetAllShoppingCardResponse;

public interface ShoppingCartService {
	List<GetAllShoppingCardResponse> getAll();
	void add(CreateShoppingCardRequest createShoppingCardRequest);
}
