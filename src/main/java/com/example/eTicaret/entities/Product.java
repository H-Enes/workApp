package com.example.eTicaret.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="products")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //SEQUENCE(eş zamanlı isteklerse daha iyidir artış miktarını kullanıcı verebilir),
    													//TABLE(önerilmez en yavaşı), AUTO(otomatik seçer)
    private int id;

    private String name;

    private int stock_quantity;

    private double price;

    @ManyToMany
    @JoinTable(
        name = "product_categories",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories; // Product sınıfının içinde bir kategori listesi barındırdığını gösterir
}
