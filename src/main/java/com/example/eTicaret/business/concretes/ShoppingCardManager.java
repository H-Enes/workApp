package com.example.eTicaret.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eTicaret.business.abstracts.ShoppingCartService;
import com.example.eTicaret.business.requests.CreateShoppingCardRequest;
import com.example.eTicaret.business.response.GetAllShoppingCardResponse;
import com.example.eTicaret.dataAccess.abstracts.ShoppingCardRepository;
import com.example.eTicaret.entities.concretes.ShoppingCard;

@Service
public class ShoppingCardManager implements ShoppingCartService {

	private ShoppingCardRepository shoppingCardRepository;
	
	@Autowired
	public ShoppingCardManager(ShoppingCardRepository shoppingCardRepository) {
		this.shoppingCardRepository = shoppingCardRepository;
	}
	
	@Override
	public List<GetAllShoppingCardResponse> getAll() {
		
		List<ShoppingCard> shoppingCarts = shoppingCardRepository.findAll();
		List<GetAllShoppingCardResponse> ShoppingCardResponse = new ArrayList<GetAllShoppingCardResponse>();

		for (ShoppingCard shoppingCart : shoppingCarts) {
			GetAllShoppingCardResponse shoppingCardItem = new GetAllShoppingCardResponse();
			shoppingCardItem.setId(shoppingCart.getId());
			shoppingCardItem.setUser_id(shoppingCart.getUser_id());
			shoppingCardItem.setProduct_id(shoppingCart.getProduct_id());
			
			ShoppingCardResponse.add(shoppingCardItem);
		
		
		}
		
		return ShoppingCardResponse;
	}

	@Override
	public void add(CreateShoppingCardRequest createShoppingCardRequest) {
		ShoppingCard shoppingCard = new ShoppingCard();
		
		shoppingCard.setProduct_id(createShoppingCardRequest.getProduct_id());
		shoppingCard.setUser_id(createShoppingCardRequest.getUser_id());
		
		this.shoppingCardRepository.save(shoppingCard);
		
	}

}
