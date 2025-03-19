package com.example.eTicaret.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eTicaret.business.abstracts.ProductService;
import com.example.eTicaret.business.requests.CreateProductRequest;
import com.example.eTicaret.business.response.GetAllProductResponse;
import com.example.eTicaret.dataAccess.abstracts.ProductRepository;
import com.example.eTicaret.entities.concretes.Product;

/*
 * @Service public class ProductManager implements ProductService {
 * 
 * private ProductRepository productRepository;
 * 
 * 
 * @Autowired public ProductManager(ProductRepository productRepository) {
 * //ürünü kaydederken hangi yöntem kullanılacak (inMemory, HyberNet)
 * this.productRepository = productRepository; }
 * 
 * 
 * 
 * @Override public List<GetAllProductResponse> getAll() { //Ürün listelerken
 * gerekli kurallar
 * 
 * List<Product> products = productRepository.findAll();
 * List<GetAllProductResponse> productResponse = new
 * ArrayList<GetAllProductResponse>();
 * 
 * 
 * for (Product product : products) { GetAllProductResponse responseItem = new
 * GetAllProductResponse(); responseItem.setId(product.getId());
 * responseItem.setName(product.getName());
 * responseItem.setCategory_id(product.getCategory_id());
 * productResponse.add(responseItem); }
 * 
 * 
 * return productResponse; }
 * 
 * 
 * 
 * @Override public void add(CreateProductRequest createProductRequest) {
 * Product product = new Product();
 * product.setName(createProductRequest.getName());
 * product.setCategory_id(createProductRequest.getCategory_id());
 * 
 * this.productRepository.save(product);
 * 
 * }
 * 
 * }
 */


@Service
public class ProductManager implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<GetAllProductResponse> getAll() {
        return productRepository.getAllWithCategoryName();
    }

	@Override
	public void add(CreateProductRequest createProductRequest) {
		Product product = new Product();
		product.setName(createProductRequest.getName());
		product.setCategory_id(createProductRequest.getCategory_id());
		 
		this.productRepository.save(product);
		
	}
}













