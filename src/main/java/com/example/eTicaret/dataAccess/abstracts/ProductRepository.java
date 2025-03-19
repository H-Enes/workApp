package com.example.eTicaret.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.eTicaret.business.response.GetAllProductResponse;
import com.example.eTicaret.entities.concretes.Product;


/*public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}*/


public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT new com.example.eTicaret.business.response.GetAllProductResponse(p.id, p.name, c.name) " +
           "FROM Product p JOIN Category c ON p.category_id = c.id")
    List<GetAllProductResponse> getAllWithCategoryName();
}
