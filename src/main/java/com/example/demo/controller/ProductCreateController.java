package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Account;

public class ProductCreateController {
	
	@GetMapping("/admin/products/create")
	public String showProductCreate(
			HttpSession session) {
		Account account = (Account) session.getAttribute("account");
		
		if(account == null) {
			return "redirect;/login";
		}
		
		if(!account.isAdmin()) {
			return "redirect:/Menu";
		}
		
		return "admin/product-create";
	}

}
