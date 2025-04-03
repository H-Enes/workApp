package com.example.eTicaret.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.eTicaret.business.response.GetAllShoppingCardResponse;
import com.example.eTicaret.entities.concretes.ShoppingCard;

public interface ShoppingCardRepository extends JpaRepository<ShoppingCard, Integer> {
	/*
	 * @Query("SELECT new com.example.eTicaret.business.response.GetAllShoppingCardResponse( "
	 * +
	 * "sc.id, sc.product_id, sc.user_id, sc.product_count, (sc.product_count * p.price) ) "
	 * + "FROM ShoppingCard sc JOIN Product p ON sc.product_id = p.id")
	 * List<GetAllShoppingCardResponse> getAllShoppingCardsWithTotalPrice();
	 */
	
	@Query("SELECT new com.example.eTicaret.business.response.GetAllShoppingCardResponse("
            +
		"sc.id, sc.user_id, sc.product_id, sc.product_count, p.price * sc.product_count) "
	+
        "FROM ShoppingCard sc " +
        "JOIN Product p ON sc.product_id = p.id")
List<GetAllShoppingCardResponse> getAllShoppingCardsWithTotalPrice();
}
