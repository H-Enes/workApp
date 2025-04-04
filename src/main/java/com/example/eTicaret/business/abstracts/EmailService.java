package com.example.eTicaret.business.abstracts;

import com.example.eTicaret.entities.concretes.Product;

public interface EmailService {
	void sendEmail(String to, String subject, String body);
	void sendStockAlertEmail(Product product);
}
