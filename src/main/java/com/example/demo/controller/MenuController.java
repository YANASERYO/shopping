package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.DetailDAO;
import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Account;
import com.example.demo.model.Detail;
import com.example.demo.model.Product;
import com.example.demo.service.AccountService;

@Controller
public class MenuController {
	private final ProductDAO productDAO;
	private final DetailDAO detailDAO;
	private final AccountService accountService;
    
	public MenuController(ProductDAO productDAO,DetailDAO detailDAO,AccountService accountService) {
		this.productDAO = productDAO;
		this.detailDAO = detailDAO;
		this.accountService = accountService;
	}
	
	// メニュー画面の表示
	@GetMapping("/menu")
	public String showMenu(HttpSession session) {
		if (session.getAttribute("account") == null) {
			return "redirect:/login";
		}
		return "menu";
		
	}
	
	// 商品を選択する→商品選択へ
	@GetMapping("/products")
	public String showProductList(HttpSession session, Model model) {
		// ログインチェック
		//	ログイン処理ではloginMenberはaccountで作成してました、DBの規則性に準じてaccountにします
		if (session.getAttribute("account") == null) {
			return "redirect:/login";
		}
		
		List<Product> productList = productDAO.getActiveProducts(); // productDAOで定義した全商品を取得
		
		model.addAttribute("productList", productList);
		
		return "product-list"; // product-list.jspへ遷移
	}
	
//	// ショッピングカートを見る→カート内容表示
//	@GetMapping("/cart")
//	public String showCartView(HttpSession session) {
//		if (session.getAttribute("account") == null) {
//			return "redirect:/login";
//		}
//		return "cartView";
//	}
	
	// 注文履歴を見る→注文履歴表示へ
	@GetMapping("/orders")
	public String showOrderHistory(HttpSession session, Model model) {
		Account account = (Account) session.getAttribute("account");
		
		// 未ログインの場合
		if (account == null) {
			return "redirect:/login";
			}
		
		List<Detail> detailList = detailDAO.findByAccountId(account.getAccountId());
		
		model.addAttribute("detailList",detailList);
		
		return "order-history";
	}
	
	// 会員情報の変更→会員情報変更へ
	@GetMapping("/account-edit")
	public String showAccountEdit(HttpSession session, Model model) {
		// セッションからログイン中の会員情報を取得
		Object loginAccount = session.getAttribute("account");
		if (loginAccount == null) {
			return "redirect:/login";
		}
		// JSPでaccountという名前で会員データを扱えるようにする。
		model.addAttribute("account", loginAccount);
		
		return "account-edit";
	}
	
	@PostMapping("/account-edit")
	public String updateMember(
			@RequestParam String accountName,
			@RequestParam String postalCode,
			@RequestParam String accountAddress,
			@RequestParam String accountPhone,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthday,
			@RequestParam String email,
			@RequestParam String payment,
			@RequestParam(required = false) String accountPass,
			HttpSession session,
			Model model) {
		
		Account loginAccount = (Account) session.getAttribute("account");
		
		if (loginAccount == null) {
			return "redirect:/login";
		}
		
		Account updateAccount = new Account();
		
		// IDはフォームから受け取らず、ログイン情報から設定
		updateAccount.setAccountId(loginAccount.getAccountId());
		updateAccount.setAccountName(accountName);
		updateAccount.setPostalCode(postalCode);
		updateAccount.setAccountAddress(accountAddress);
		updateAccount.setAccountPhone(accountPhone);
		updateAccount.setBirthday(birthday);
		updateAccount.setEmail(email);
		updateAccount.setPayment(payment);
		updateAccount.setAccountPass(accountPass);
		
		if (!accountService.update(updateAccount)) {
			model.addAttribute(
					"accountEditError",
					"会員情報の更新に失敗しました。");
			model.addAttribute("account", updateAccount);
			
			return "account-edit";
		}
		
		// DBから最新情報を取り直してセッションを更新
		Account refreshedAccount = accountService.findByAccountId(
				loginAccount.getAccountId());
		
		session.setAttribute("account", refreshedAccount);
		
		return "redirect:/menu";
	}
	
}
