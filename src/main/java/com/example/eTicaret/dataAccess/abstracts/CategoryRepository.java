package com.example.eTicaret.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eTicaret.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
