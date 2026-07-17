package com.example.demo.controller;


import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Product;


@Controller
public class ProductListController {
	private final ProductDAO productDAO;
	
	public ProductListController(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
//	@GetMapping("/ここをURLに！！")
	@GetMapping("/ProductLIstServlet")
	public String showProductList(HttpSession session, Model model) {
		// ログインチェック
		//	ログイン処理ではloginMenberはaccountで作成してました、DBの規則性に準じてaccountにします
		if (session.getAttribute("account") == null) {
			return "redirect:/login";
		}
		
		List<Product> productList = productDAO.getAllProducts(); // shoppingDAOクラスで定義した全商品を取得する処理
		
		model.addAttribute("productList", productList);
		
		return "product-list"; // product-list.jspへ遷移
	}

}
