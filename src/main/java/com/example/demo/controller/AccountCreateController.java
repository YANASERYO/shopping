package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.service.AccountService;

@Controller
public class AccountCreateController {
	
	private final AccountService accountService =
			new AccountService();
	
//	アカウント作成画面に飛ぶ
	@GetMapping("/account-create")
	public String showAccountCreate() {
		return "account-create";
//		確認用
//		return "forward:/WEB-INF/jsp/account-create.jsp";
	}
	
//	アカウント登録
	@PostMapping("/account-create")
	public String createAccount(
			@RequestParam String accountId,
			@RequestParam String accountPass,
			@RequestParam String accountName,
			@RequestParam String postalCode,
			@RequestParam String accountAddress,
			@RequestParam String accountPhone,
			@RequestParam String birthday,
			@RequestParam String email,
			@RequestParam String payment) {
		
		Account account = new Account();
		
		account.setAccountId(accountId);
		account.setAccountPass(accountPass);
		account.setAccountName(accountName);
		account.setPostalCode(postalCode);
		account.setAccountAddress(accountAddress);
		account.setAccountPhone(accountPhone);
		account.setBirthday(LocalDate.parse(birthday));
		account.setEmail(email);
		account.setPayment(payment);
		account.setAdmin(false);
		
		boolean result = accountService.register(account);
		
		if (result) {
			return "redirect:/login";
		}
		
		return "account-create";
	}
}