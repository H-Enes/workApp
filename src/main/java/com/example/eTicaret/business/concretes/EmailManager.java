package com.example.eTicaret.business.concretes;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.eTicaret.business.abstracts.EmailService;
import com.example.eTicaret.entities.concretes.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailManager implements EmailService {

	private final JavaMailSender javaMailSender;
	
	
	


	@Override
	public void sendEmail(String to, String subject, String body) {
		try {
            MimeMessageHelper message = new MimeMessageHelper(javaMailSender.createMimeMessage());
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body, true);
            javaMailSender.send(message.getMimeMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
			
	}
	public void sendStockAlertEmail(Product product) {
	        String to = "admin@example.com";  // Admin email adresi
	        String subject = "Stok Uyarısı: " + product.getName();
	        String body = "Stok miktarı azalan ürün:\n" +
	                      "Ürün Adı: " + product.getName() + "\n" +
	                      "Mevcut Stok Miktarı: " + product.getStock_quantity();
	        sendEmail(to, subject, body);
	    }
	

}
