package com.example.eTicaret.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.eTicaret.business.abstracts.ProductService;
import com.example.eTicaret.business.converters.ProductConverter;
import com.example.eTicaret.business.requests.CreateProductRequest;
import com.example.eTicaret.business.response.GetAllProductResponse;
import com.example.eTicaret.dataAccess.abstracts.ProductRepository;
import com.example.eTicaret.entities.concretes.Product;

import lombok.RequiredArgsConstructor;


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
@RequiredArgsConstructor
public class ProductManager implements ProductService {

    private final ProductRepository productRepository;
    //requiredArgsConstructor ile otomatik dependency injection
	/*
	 * @Autowired public ProductManager(ProductRepository productRepository) {
	 * this.productRepository = productRepository; }
	 */

    @Override
    public List<GetAllProductResponse> getAll() {
    	List<Product> products = productRepository.findAll();
        return ProductConverter.convertToResponseList(products);
    }

	@Override
	public void add(CreateProductRequest createProductRequest) {
		Product product = new Product();
		product.setName(createProductRequest.getName());
		product.setStock_quantity(createProductRequest.getStock_quantity());
		 
		this.productRepository.save(product);
		
	}
	
	


	public void decreaseStock(int productId, int count) {
		System.out.println("Stok azaltma işlemi başlıyor, Product ID: " + productId);
	    Product product = productRepository.findById(productId)
	            .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));

	    if (product.getStock_quantity() > 0) {
	        product.setStock_quantity(product.getStock_quantity() - count);
	        this.productRepository.save(product);
	    } else {
	        throw new RuntimeException("Stok yetersiz!");
	    }
	}
	
	public void increasingStock(int productId, int count) {
		System.out.println("Stok artırma işlemi başlıyor, Product ID: " + productId);
	    Product product = productRepository.findById(productId)
	            .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));

	        product.setStock_quantity(product.getStock_quantity() + count);
	        this.productRepository.save(product);    
	}
	
	
	public void deleteProduct(int productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            throw new RuntimeException("Ürün bulunamadı!");
        }
    }

	@Override
	public void updatePrice(int id, double price) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));
		product.setPrice(price);
		productRepository.save(product);
		
		
	}


}













