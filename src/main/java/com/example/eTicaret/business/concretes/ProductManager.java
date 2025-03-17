package com.example.eTicaret.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eTicaret.business.abstracts.ProductService;
import com.example.eTicaret.dataAccess.abstracts.ProductRepository;
import com.example.eTicaret.entities.concretes.Product;

@Service
public class ProductManager implements ProductService {

	private ProductRepository productRepository;
	
	
	@Autowired
	public ProductManager(ProductRepository productRepository) {
		//ürünü kaydederken hangi yöntem kullanılacak (inMemory, HyberNet)
		this.productRepository = productRepository;
	}



	@Override
	public List<Product> getAll() {
		//Ürün listelerken gerekli kurallar
		
		
		
		return productRepository.findAll();
	}

}
