package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.service.LoginService;


//ログイン
@Controller
public class LoginController {
	private final LoginService loginService;
	
	public LoginController(LoginService loginService) {
	    this.loginService = loginService;
	}
	
	// アカウントIDで取得し、BCryptでパスワードを照合
	@GetMapping("/login")
	public String showLogin(HttpSession session) {
		
		Account account = (Account) session.getAttribute("account");
		
		if(account != null) {
			if(account.isAdmin()) {
				return "redirect:/admin";
			}
			return "redirect:/menu";
		}
		return "index";
	}
	
	@PostMapping("/login")
	public String login(
			@RequestParam(name = "accountId", defaultValue = "") String accountId,
			@RequestParam(name = "accountPass", defaultValue = "") String accountPass,
			HttpServletRequest request,
			Model model) {
		
		accountId = accountId.trim();
		
		// 入力したIDを保持
		model.addAttribute("accountId", accountId);
		
		boolean hasError = false;
		
		// IDの未入力チェック
		if (accountId.isBlank()) {
			model.addAttribute(
					"accountIdError",
					"アカウントIDを入力してください。");
			
			hasError = true;
		} else if (accountId.length() < 4 || accountId.length() > 20) {
			model.addAttribute(
					"accountIdError",
					"アカウントIDは4文字以上20文字以内で入力してください。");
			
			hasError = true;
		} else if (!accountId.matches("^[a-zA-Z0-9_-]+$")) {
			model.addAttribute(
					"accountIdError",
					"アカウントIDは半角英数字で入力してください。");
			
			hasError = true;
		}
		
		// パスワードの未入力チェック
		if (accountPass.isBlank()) {
			model.addAttribute(
					"accountPassError",
					"パスワードを入力してください。");
			
			hasError = true;
		} else if (accountPass.length() > 100) {
			model.addAttribute(
					"accountPassError",
					"パスワードが長すぎます。");
			
			hasError = true;
		}
		
		// 入力エラーがある場合
		if (hasError) {
			return "index";
		}
		
		try {
			Account account = loginService.login(accountId, accountPass);
			
			if (account == null) {
			    model.addAttribute(
			            "loginError",
			            "アカウントIDまたはパスワードが正しくありません。");
			    
			    return "index";
			}
			
			 if (!account.isAccountActive()) {
			     model.addAttribute(
			            "loginError",
			             "このアカウントは現在利用できません。");
			
			     return "index";
			 }
			 
			HttpSession oldSession = request.getSession(false);
			
			if (oldSession != null) {
				oldSession.invalidate();
			}
			
			HttpSession newSession = request.getSession(true);
			
			newSession.setAttribute("account", account);
			// セッション有効期限を1800秒（30分）にした
			newSession.setMaxInactiveInterval(1800);
			
			if (account.isAdmin()) {
				return "redirect:/admin";
			}
			
			return "redirect:/menu";
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			
			model.addAttribute(
					"loginError",
					"アカウントIDまたはパスワードが正しくありません。");
			
			return "index";
			
		} catch (Exception e) {
			e.printStackTrace();
			
			model.addAttribute(
					"loginError",
					"ログイン処理中にエラーが発生しました。");
			
			return "index";
		}
	}
}
