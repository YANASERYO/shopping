package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Account;

@Controller
public class AdminController {
	
	@GetMapping("/admin")
	public String adminMenu(HttpSession session) {
		
		Account account = (Account) session.getAttribute("account");
		
		// 未ログインの場合
		if (account == null) {
			return "redirect:/login";
			}
		
		// 一般ユーザーは管理者画面に入れない
		if (!account.isAdmin()) {
			return "redirect:/Menu";
			}
		
		return "admin/admin-menu";
		}
}