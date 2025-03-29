package com.example.eTicaret.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eTicaret.entities.concretes.Product;


/*public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}*/


public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAll();
}
