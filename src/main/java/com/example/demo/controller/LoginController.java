package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dao.AccountDAO;
import com.example.demo.model.Account;

//ログイン
@Controller
public class LoginController {
	@PostMapping("/login")
	public String login( 
			@RequestParam String accountName,
            @RequestParam String accountPass,
            HttpSession session
			) {
	AccountDAO dao = new AccountDAO();
	
	Account account = dao.login(accountName, accountPass);
	
	if(account != null) {
		return"menu";
	}else {
		return"index";
	}
	}
}
