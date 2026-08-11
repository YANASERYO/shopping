package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.service.AccountService;

@Controller
public class AccountDeleteController {

	private final AccountService accountService;

	public AccountDeleteController(AccountService accountService) {
		this.accountService = accountService;
	}

	// アカウント削除確認画面
	@GetMapping("/account-delete-confirm")
	public String showAccountDeleteConfirm(
			HttpSession session,
			Model model) {
		
		Account account = (Account) session.getAttribute("account");

		if (account == null) {
			return "redirect:/login";
		}

		model.addAttribute("account", account);

		return "account-delete-confirm";
	}

	// アカウント削除
	@PostMapping("/account-delete")
	public String deleteAccount(
			@RequestParam(name = "deleteConfirmed", required = false) String deleteConfirmed,
			HttpSession session,
			Model model) {
		Account account = (Account) session.getAttribute("account");
		
		if (account == null) {
			return "redirect:/login";
		}
		
		// チェックボックスをサーバー側で
		if (!"true".equals(deleteConfirmed)) {
			model.addAttribute(
					"account",
					account);
			
			model.addAttribute(
					"accountDeleteError",
					"重要事項を確認し、チェックを入れてください。");
			
			return "account-delete-confirm";
		}

		boolean deleted = accountService.delete(account.getAccountId());

		if (!deleted) {
			model.addAttribute("account", account);
			model.addAttribute("accountDeleteError", "アカウントの削除に失敗しました。");
			return "account-delete-confirm";
		}
		// ログイン情報を破棄
		session.invalidate();
		// index.jspでアラートを表示するための値
		model.addAttribute("accountDeleted", true);
		return "index";
	}
}