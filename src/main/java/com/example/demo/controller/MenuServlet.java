package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuServlet {
	
	// メニュー画面の表示
	@GetMapping("/Menu")
	public String showMenu(HttpSession session) {
		if (session.getAttribute("loginMember") == null) {
			return "redirect:/login";
		}
		return "menu";
		
	}

	// 商品を選択する→商品選択へ
	@GetMapping("/ProductListServlet")
	public String showProductList(HttpSession session) {
		if (session.getAttribute("loginMember") == null) {
			return "redirect:/login";
		}
		return "productList";
	}
	
	// ショッピングカートを見る→カート内容表示
	@GetMapping("/CartServlet")
	public String showCartView(HttpSession session) {
		if (session.getAttribute("loginMember") == null) {
			return "redirect:/login";
		}
		return "cartView";
	}
	
	// 注文履歴を見る→注文履歴表示へ
	@GetMapping("/OrderHistoryServlet")
	public String showOrderHistory(HttpSession session) {
		if (session.getAttribute("loginMember") == null) {
			return "redirect:/login";
		}
		return "orderHistory";
	}
	
	// 会員情報の変更→会員情報変更へ
	@GetMapping("/MemberEditServlet")
	public String showMemberEdit(HttpSession session) {
		if (session.getAttribute("loginMember") == null) {
			return "redirect:/login";
		}
		return "memberEdit";
	}
	
	// ログアウト
	@GetMapping("/LogoutServlet")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	
}
