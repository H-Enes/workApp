package com.example.eTicaret.business.concretes;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import com.example.eTicaret.business.converters.ProductConverter;
import com.example.eTicaret.business.requests.CreateProductRequest;
import com.example.eTicaret.business.requests.UpdateProductRequest;
import com.example.eTicaret.business.response.GetAllProductResponse;
import com.example.eTicaret.dataAccess.abstracts.CategoryRepository;
import com.example.eTicaret.dataAccess.abstracts.ProductCategoryRepository;
import com.example.eTicaret.dataAccess.abstracts.ProductRepository;
import com.example.eTicaret.entities.Category;
import com.example.eTicaret.entities.Product;
import com.example.eTicaret.entities.ProductCategory;
import com.example.eTicaret.exeptions.ProductNotFoundException;
import com.example.eTicaret.exeptions.StockNotEnoughException;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ProductManager {

	private static final Logger logger = LoggerFactory.getLogger(ProductManager.class);
    private final ProductRepository productRepository;
    private final EmailManager emailService;  // Admin'e bildirim gönderecek servis
    private final ProductCategoryRepository productCategoryRepository;
    private final CategoryRepository categoryRepository;
    
    //requiredArgsConstructor ile otomatik dependency injection
	/*
	 * @Autowired public ProductManager(ProductRepository productRepository) {
	 * this.productRepository = productRepository; }
	 */

    public List<GetAllProductResponse> getAll() {
    	List<Product> products = productRepository.findAll();
        return ProductConverter.convertToResponseList(products);
    }
    
    public List<GetAllProductResponse> getProductsByCategoryId(int categoryId) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ProductNotFoundException("Böyle bir kategori bulunamadı"));

        Set<Product> products = category.getProducts();  //join olmadan ilişkili ürünleri almak için

        return ProductConverter.convertToResponseList(new ArrayList<>(products));
    }


	public void add(CreateProductRequest createProductRequest) {
		Product product = new Product();
		product.setName(createProductRequest.getName());
		product.setStock_quantity(createProductRequest.getStock_quantity());
		product.setPrice(createProductRequest.getPrice());
		 
		Product savedProduct = productRepository.save(product);
		
		 for (Integer categoryId : createProductRequest.getCategoryIds()) {
		        ProductCategory pc = new ProductCategory();
		        pc.setProductId(savedProduct.getId());
		        pc.setCategoryId(categoryId);
		        productCategoryRepository.save(pc);
		    }
	}
	
	public void decreaseStock(int productId, int count) {
		System.out.println("Stok azaltma işlemi başlıyor, Product ID: " + productId);
	    Product product = productRepository.findById(productId)
	            .orElseThrow(() -> new ProductNotFoundException("Stoğu azaltılacak ürün bulunamadı"));
	    if (product.getStock_quantity() >= count) {
	        product.setStock_quantity(product.getStock_quantity() - count);
	        this.productRepository.save(product);
	    } else {
	        throw new StockNotEnoughException("Yeterli stok yok. Mevcut: " + product.getStock_quantity());
	    }
	}
	
	public void increasingStock(int productId, int count) {
		System.out.println("Stok artırma işlemi başlıyor, Product ID: " + productId);
	    Product product = productRepository.findById(productId)
	            .orElseThrow(() -> new ProductNotFoundException("Stoğu artıralacak ürün bulunamadı"));

	        product.setStock_quantity(product.getStock_quantity() + count);
	        this.productRepository.save(product);    
	}
	
	public void deleteProduct(int productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            throw new ProductNotFoundException("Silinecek ürün bulunamadı!");
        }
    }

	public void updatePrice(int id, double price) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Fiyatı güncellenecek ürün bulunamadı"));
		product.setPrice(price);
		productRepository.save(product);	
	}

	public void updateProduct(int id, UpdateProductRequest product) {
	    Product toUpdateProduct = productRepository.findById(id)
	            .orElseThrow(() -> new ProductNotFoundException("Güncellenecek ürün bulunamadı"));

	    StringBuilder logMessage = new StringBuilder("Ürün ID: " + id + " güncelleme işlemi: ");
	    boolean updated = false;

	    if (product.getName() != null) {
	        logMessage.append("[İsim: ")
	                .append(toUpdateProduct.getName())
	                .append(" -> ")
	                .append(product.getName()).append("] ");
	        toUpdateProduct.setName(product.getName());
	        updated = true;
	    }

	    if (product.getPrice() != null) {
	        logMessage.append("[Fiyat: ")
	                .append(toUpdateProduct.getPrice())
	                .append(" -> ")
	                .append(product.getPrice()).append("] ");
	        toUpdateProduct.setPrice(product.getPrice());
	        updated = true;
	    }

	    if (product.getStock_quantity() != null) {
	        logMessage.append("[Stok: ")
	                .append(toUpdateProduct.getStock_quantity())
	                .append(" -> ")
	                .append(product.getStock_quantity()).append("] ");
	        toUpdateProduct.setStock_quantity(product.getStock_quantity());
	        updated = true;
	    }

	    if (updated) {
	        productRepository.save(toUpdateProduct);
	        logger.info(logMessage.toString());
	    } else {
	        logger.warn("Ürün ID: {} için güncelleme isteği geldi fakat hiçbir alan değiştirilmedi.", id);
	        
	    }
	}
	
	
	@Scheduled(cron = "0 0 0 * * ?")  // Her gün gece 12'de kontrol et
    public void checkStockLevels() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.getStock_quantity() < 10) {
                emailService.sendStockAlertEmail(product);
            }
        }
    }

}













