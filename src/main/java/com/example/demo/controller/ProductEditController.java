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
public class ProductEditController {
	
	private final ProductService productService;
	
	public ProductEditController(ProductService productService) {
		this.productService = productService;
	}
	
	// 商品編集画面を表示
	@GetMapping("/admin/products/edit")
	public String showProductEdit(
			@RequestParam Long id,
			HttpSession session,
			Model model) {
		
		Account account =
				(Account) session.getAttribute("account");
		
		if (account == null) {
			return "redirect:/login";
		}
		
		if (!account.isAdmin()) {
			return "redirect:/Menu";
		}
		
		Product product = productService.findById(id);
		
		if (product == null) {
			return "redirect:/admin/products";
		}
		
		model.addAttribute("product", product);
		
		return "admin/product-edit";
	}
	
	// 商品更新処理
	@PostMapping("/admin/products/edit")
	public String updateProduct(
			@RequestParam Long productId,
			@RequestParam String productName,
			@RequestParam int productPrice,
			@RequestParam int productStock,
			@RequestParam String productCategory,
			@RequestParam(required = false) String productImgPath,
			@RequestParam(required = false) String productDescription,
			@RequestParam boolean productActive,
			@RequestParam String action,
			HttpSession session,
			Model model) {
		
		Account account = (Account) session.getAttribute("account");
		
		if (account == null) {
			return "redirect:/login";
		}
		
		if (!account.isAdmin()) {
			return "redirect:/Menu";
		}
		
		if ("削除".equals(action)) {
			productService.delete(productId);
			return "redirect:/admin/products";
		}
		
		if (productName == null || productName.isBlank()) {
			model.addAttribute("errorMessage", "商品名を入力してください。");
			return showProductEdit(productId, session, model);
		}
		
		if (productPrice < 0) {
			model.addAttribute("errorMessage", "価格は0円以上で入力してください。");
			return showProductEdit(productId, session, model);
		}
		
		if (productStock < 0) {
			model.addAttribute("errorMessage", "在庫数は0個以上で入力してください。");
			return showProductEdit(productId, session, model);
		}
		
		Product product = new Product();
		
		product.setProductId(productId);
		product.setProductName(productName);
		product.setProductPrice(productPrice);
		product.setProductStock(productStock);
		product.setProductCategory(productCategory);
		product.setProductImgPath(productImgPath);
		product.setProductDescription(productDescription);
		product.setProductActive(productActive);
		
		boolean result = productService.update(product);
		
		if (!result) {
			model.addAttribute("errorMessage", "商品の更新に失敗しました。");
			model.addAttribute("product", product);
			return "admin/product-edit";
		}
		
		return "redirect:/admin/products";
	}
	
	
}