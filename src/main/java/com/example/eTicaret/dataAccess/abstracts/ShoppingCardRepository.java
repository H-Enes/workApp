package com.example.eTicaret.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.eTicaret.entities.concretes.ShoppingCard;

public interface ShoppingCardRepository extends JpaRepository<ShoppingCard, Integer> {

}
