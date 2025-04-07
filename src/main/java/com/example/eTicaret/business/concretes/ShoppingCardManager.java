package com.example.eTicaret.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.eTicaret.business.abstracts.ShoppingCartService;
import com.example.eTicaret.business.requests.CreateShoppingCardRequest;
import com.example.eTicaret.business.response.GetAllShoppingCardResponse;
import com.example.eTicaret.dataAccess.abstracts.ShoppingCardRepository;
import com.example.eTicaret.entities.concretes.ShoppingCard;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShoppingCardManager implements ShoppingCartService {

	private final ShoppingCardRepository shoppingCardRepository;
	private final ProductManager productManager;
	
	
	/*
	 * @Autowired public ShoppingCardManager(ShoppingCardRepository
	 * shoppingCardRepository, ProductManager productManager) {
	 * this.shoppingCardRepository = shoppingCardRepository; this.productManager =
	 * productManager; }
	 */
	 
	
	@Override
	public List<GetAllShoppingCardResponse> getAll() {	
		return shoppingCardRepository.getAllShoppingCardsWithTotalPrice();
	}

	@Override
	public void add(CreateShoppingCardRequest createShoppingCardRequest) {
		ShoppingCard shoppingCard = new ShoppingCard();
		
		shoppingCard.setProduct_id(createShoppingCardRequest.getProduct_id());
		shoppingCard.setUser_id(createShoppingCardRequest.getUser_id());
		shoppingCard.setProduct_count(createShoppingCardRequest.getProduct_count());
		
		this.shoppingCardRepository.save(shoppingCard);
		
		// Stok azaltma işlemi ProductManager üzerinden çağrılmalı
	    productManager.decreaseStock(createShoppingCardRequest.getProduct_id(), createShoppingCardRequest.getProduct_count());
		
	}
	
	@Transactional    // veri tabanında ilk işlemde hata olursa diğer işlemler yapılmaz veya geri alının-r. tutarsızlık önlenir
	@Override
	public void deleteShoppingCart(int shoppingCartId) {
	    if (shoppingCardRepository.existsById(shoppingCartId)) {
	        // Önce sepetten ilgili ürünü bul
	        ShoppingCard shoppingCard = shoppingCardRepository.findById(shoppingCartId)
	                .orElseThrow(() -> new RuntimeException("Sepette ürün bulunamadı!"));

	        int productId = shoppingCard.getProduct_id();
	        int count = shoppingCard.getProduct_count();

	        // Sepetten ürünü sil
	        shoppingCardRepository.deleteById(shoppingCartId);

	        // Stok artırma işlemi
	        productManager.increasingStock(productId, count);
	    } else {
	        throw new RuntimeException("Sepette ürün bulunamadı!");
	    }
	}



}
