package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org .springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.ShoppingDAO;
import com.example.demo.model.Product;
@Controller
public class ProductListController {
	private final ShoppingDAO shoppingDAO;
	
	public ProductListController(ShoppingDAO shoppingDAO) {
		this.shoppingDAO = shoppingDAO;
	}
	
	@GetMapping("/ProductLIstServlet")
	public String showProductList(HttpSession session, Model model) {
		// ログインチェック
		if (session.getAttribute("loginMember") == null) {
			return "redirect:/login";
		}
		
		List<Product> productList = shoppingDAO.getAllProducts(); // shoppingDAOクラスで定義した全商品を取得する処理
		
		model.addAttribute("productLIst", productList);
		
		return "product-list"; // product-list.jspへ遷移
	}

}
