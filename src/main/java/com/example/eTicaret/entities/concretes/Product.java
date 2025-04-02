package com.example.eTicaret.entities.concretes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="products")  // tablo ismi ile entity sınıfının ismi farklıysa @Table anatasyonu kullanılması gerek
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name="id") sütun ismi ve değişken ismi aynı ise anatasyonu kullanmaya gerek yok
	private int id;
	
	//@Column(name="name")
	private String name;
	
	//@Column(name = "stock_quantity")
	private int stock_quantity;
	
	

	
	
}
