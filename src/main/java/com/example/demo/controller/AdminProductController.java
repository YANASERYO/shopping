package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Account;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@Controller
public class AdminProductController {
	
	private final ProductService productService;
	
	public AdminProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/admin/products")
	public String showProductList(HttpSession session, Model model) {
		
		Account account = (Account) session.getAttribute("account");

		if (account == null) {
			return "redirect:/login";
		}
		
		if (!account.isAdmin()) {
			return "redirect:/Menu";
		}
		
		List<Product> productList =
				productService.getAllProducts();
		
		model.addAttribute(
				"productList",
				productList);
		
		return "admin/product-list";
	}
}