package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Account;
import com.example.demo.service.OrderService;
@Controller
public class OrderController {
	private final OrderService orderService;
	public OrderController(OrderService orderService) {this.orderService = orderService;}
	
	// 注文確定
	@PostMapping("/order/confirm")
	public String confirmOrder(HttpSession session) {
		Account account = (Account) session.getAttribute("account");
		
		// 未ログインの場合
		if (account == null) {
			return "redirect:/login";
		}
		int shoppingId = orderService.createOrder(account);
		
		// カートが空、または注文登録失敗
		if (shoppingId == 0) {
			return "redirect:/cart";
		}
		
		return "redirect:/order/complete";
	}
	
	// 注文完了画面
	@GetMapping("/order/complete")
	public String showComplete(HttpSession session) {
		if (session.getAttribute("account") == null) {
			return "redirect:/login";
		}
		return "order-complete";
	}

	//menu.jspからorder-infoへ遷移	
	@GetMapping("/order/buy")
	public String showOrderbuy(HttpSession session) {

		Account account = (Account) session.getAttribute("account");

		if (account == null) {
			return "redirect:/login";
		}

	return "order-info";
}
}