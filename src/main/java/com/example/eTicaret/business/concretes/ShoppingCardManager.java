package com.example.eTicaret.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.eTicaret.business.requests.CreateShoppingCardRequest;
import com.example.eTicaret.business.response.GetAllShoppingCardResponse;
import com.example.eTicaret.dataAccess.abstracts.ShoppingCardRepository;
import com.example.eTicaret.entities.ShoppingCard;
import com.example.eTicaret.exeptions.ProductNotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShoppingCardManager  {

	private final ShoppingCardRepository shoppingCardRepository;
	private final ProductManager productManager;
	
	
	/*
	 * @Autowired public ShoppingCardManager(ShoppingCardRepository
	 * shoppingCardRepository, ProductManager productManager) {
	 * this.shoppingCardRepository = shoppingCardRepository; this.productManager =
	 * productManager; }
	 */
	 
	
	public List<GetAllShoppingCardResponse> getAll() {	
		return shoppingCardRepository.getAllShoppingCardsWithTotalPrice();
	}

	public void add(CreateShoppingCardRequest createShoppingCardRequest) {
		ShoppingCard shoppingCard = new ShoppingCard();
		
		shoppingCard.setProduct_id(createShoppingCardRequest.getProduct_id());
		shoppingCard.setUser_id(createShoppingCardRequest.getUser_id());
		shoppingCard.setProduct_count(createShoppingCardRequest.getProduct_count());
		
		this.shoppingCardRepository.save(shoppingCard);
		
		// Stok azaltma işlemi ProductManager üzerinden çağrılmalı
	    productManager.decreaseStock(createShoppingCardRequest.getProduct_id(), createShoppingCardRequest.getProduct_count());
		
	}
	
	@Transactional
	public void deleteShoppingCart(int shoppingCartId) {
	    ShoppingCard shoppingCard = shoppingCardRepository.findById(shoppingCartId)
	            .orElseThrow(() -> new ProductNotFoundException("Sepette ürün bulunamadı!"));

	    int productId = shoppingCard.getProduct_id();
	    int count = shoppingCard.getProduct_count();

	    shoppingCardRepository.deleteById(shoppingCartId);

	    productManager.increasingStock(productId, count);
	}




}
