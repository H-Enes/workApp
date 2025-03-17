package com.example.eTicaret.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.eTicaret.entities.concretes.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}
