package com.example.eTicaret.business.requests;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateShoppingCardRequest {
	
	@Min(value = 1, message = "Geçerli bir ürün ID girilmelidir")
	private int user_id;
	
	@Min(value = 1, message = "Geçerli bir kullanıcı ID girilmelidir")
	private int product_id;
	
	@Min(value = 1, message = "Ürün adedi en az 1 olmalıdır")
	private int product_count;

}
