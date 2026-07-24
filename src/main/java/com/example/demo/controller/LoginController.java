package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.AccountDAO;
import com.example.demo.model.Account;
import com.example.demo.util.PassEncoderUtil;

//処理の不足を修正

//ログイン
@Controller
public class LoginController {
	
	private final AccountDAO accountDAO;
	
	public LoginController(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	// アカウントIDで取得し、BCryptでパスワードを照合
	
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
		
	Account account = accountDAO.findByAccountId(accountId);
	
//	アカウントがない場合indexに返す
	if(account == null) {
		return "index";
	}
	
	boolean passwordMatches = PassEncoderUtil.matches(accountPass, account.getAccountPass());
	if (!passwordMatches) {
		return "index";
	}
	
	session.setAttribute("account", account); 
	
//	accountsのadminがtrueならAdminControllerに
//	Lombok使ってるのでisAdmin
	if(account.isAdmin() == true) {
		return"redirect:/admin";
	}
	
	return "redirect:/menu";
	}
}
