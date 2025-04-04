package com.example.eTicaret.webApi.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.eTicaret.business.abstracts.EmailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/email")
public class EmailController {
	private final EmailService emailService;
	
	 @PostMapping("/send")
	    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
	        emailService.sendEmail(to, subject, body);
	        return "Email sent successfully!";
	    }
}
