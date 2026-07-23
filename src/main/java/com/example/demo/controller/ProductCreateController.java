package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@Controller
public class ProductCreateController {
	
	private final ProductService productService;
	
	public ProductCreateController(ProductService productService) {
		this.productService = productService;
	}
	
	// 商品登録画面の表示
	@GetMapping("/admin/products/create")
	public String showProductCreate(HttpSession session) {
		Account account = (Account) session.getAttribute("account");
		
		if (account == null) {
			return "redirect:/login";
			}
		
		if (!account.isAdmin()) {
			return "redirect:/Menu";
		}
		
		return "admin/product-create";
	}
	
	// 商品登録
	@PostMapping("/admin/products/create")
	public String createProduct(
			@RequestParam String productName,
			@RequestParam int productPrice,
			@RequestParam int productStock,
			@RequestParam String productCategory,
			@RequestParam(required = false) String productImgPath,
			@RequestParam(required = false) String productDescription,
			@RequestParam boolean productActive,
			HttpSession session,
			Model model) {
		
		Account account = (Account) session.getAttribute("account");
		
		if (account == null) {
			return "redirect:/login";
		}
		
		if (!account.isAdmin()) {
			return "redirect:/Menu";
		}
		
		// 入力チェック
		if (productName == null || productName.isBlank()) {
			model.addAttribute(
					"errorMessage",
					"商品名を入力してください。");
			
			return "admin/product-create";
		}
		
		if (productPrice < 0) {
			model.addAttribute(
					"errorMessage",
					"価格は0円以上で入力してください。");
			return "admin/product-create";
		}
		
		if (productStock < 0) {
			model.addAttribute(
					"errorMessage",
					"在庫数は0個以上で入力してください。");
			
			return "admin/product-create";
		}
		
		// Productオブジェクト作成
		Product product = new Product();
		
		product.setProductName(productName);
		product.setProductPrice(productPrice);
		product.setProductStock(productStock);
		product.setProductCategory(productCategory);
		product.setProductImgPath(productImgPath);
		product.setProductDescription(productDescription);
		product.setProductActive(productActive);
		
		// DBに登録
		boolean result = productService.create(product);
		
		if (!result) {
			model.addAttribute(
					"errorMessage",
					"商品の登録に失敗しました。");
			return "admin/product-create";
		}
		
		return "redirect:/admin/products";
	}
}