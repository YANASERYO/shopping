package com.example.demo.controller;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.service.AccountService;

@Controller
public class AccountCreateController {
	
	private final AccountService accountService;
	
	public AccountCreateController(AccountService accountService) {
		this.accountService = accountService;
	}
	
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
//			未入力でもOKに
			@RequestParam(required = false, defaultValue = "") String birthday,
			@RequestParam String email,
			@RequestParam String payment,
			Model model) {
//		テスト
		System.out.println("birthday受信値：" + birthday);
		
		accountId = accountId.trim();
		
//		エラー時にも保持するためモデル追加
		model.addAttribute("accountId", accountId);
		model.addAttribute("accountName", accountName);
		model.addAttribute("postalCode", postalCode);
		model.addAttribute("accountAddress", accountAddress);
		model.addAttribute("accountPhone", accountPhone);
		model.addAttribute("birthday", birthday);
		model.addAttribute("email", email);
		model.addAttribute("payment", payment);
		
		if (accountService.existsByAccountId(accountId)) {

			model.addAttribute(
					"accountIdError",
					"このアカウントIDは既に使用されています。");

			return "account-create";
		}
		
		LocalDate parsedBirthday = null;
		
		if (!birthday.isBlank()) {
			
			try {
				parsedBirthday = LocalDate.parse(birthday);
				
				LocalDate today = LocalDate.now();
				
				if (parsedBirthday.isAfter(today)) {
					model.addAttribute("birthdayError","生年月日に未来の日付は指定できません。");
				
					
					return "account-create";
				}
				
				if (parsedBirthday.isBefore(today.minusYears(120))) {
					model.addAttribute("birthdayError", "生年月日を正しく入力してください。");
					
					return "account-create";
				}
				
			} catch (DateTimeException e) {
				model.addAttribute("birthdayError", "生年月日を正しい日付で入力してください。");
				return "account-create";
			}
		}

		Account account = new Account();
		
		account.setAccountId(accountId.trim());
		account.setAccountPass(accountPass);
		account.setAccountName(accountName.trim());
		account.setPostalCode(postalCode);
		account.setAccountAddress(accountAddress.trim());
		account.setAccountPhone(accountPhone.trim());
		account.setBirthday(parsedBirthday);
		account.setEmail(email.trim());
		account.setPayment(payment);
		account.setAdmin(false);
		
		boolean result = accountService.register(account);
		
		if (result) {
			return "redirect:/login";
		}
		model.addAttribute(
				"registerError",
				"アカウントを登録できませんでした。" + "アカウントIDが既に使用されている可能性があります。");
		
		return "account-create";
	}
}