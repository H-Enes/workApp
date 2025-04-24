package com.example.eTicaret.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eTicaret.entities.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
