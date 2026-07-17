package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.AccountDAO;
import com.example.demo.model.Account;

//処理の不足を修正

//ログイン
@Controller
public class LoginController {
	
	
	@GetMapping("/login")
	public String showLogin() {
		return "index";
	}
	
	@PostMapping("/login")
	public String login( 
			@RequestParam String accountId,
            @RequestParam String accountPass,
            HttpSession session
			) {
	AccountDAO dao = new AccountDAO();
	Account account = dao.login(accountId, accountPass);
	
//	アカウントがない場合indexに返す
	if(account == null) {
		return "index";
	}
	
	session.setAttribute("account", account); 
	
//	accountsのadminがtrueならadminのmenuに
//	Lombok使ってるのでisAdmin
	if(account.isAdmin() == true) {
		return"admin/admin-menu";
	}
	
	return "menu";
	}
}
