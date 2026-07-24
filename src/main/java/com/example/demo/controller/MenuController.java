package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.OrderInfoDAO;
import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Account;
import com.example.demo.model.OrderInfo;
import com.example.demo.model.Product;

@Controller
public class MenuController {
	private final ProductDAO productDAO;
	private final OrderInfoDAO orderInfoDAO;
    
	public MenuController(ProductDAO productDAO,OrderInfoDAO orderInfoDAO) {
		this.productDAO = productDAO;
	this.orderInfoDAO = orderInfoDAO;
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
		
		List<Product> productList = productDAO.getActiveProducts(); // productDAOクラスで定義した全商品を取得する処理
		
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
		
		List<OrderInfo> orderInfoList = orderInfoDAO.findByAccountId(account.getAccountId());
		
		model.addAttribute("orderInfoList",orderInfoList);
		
		return "order-info";
	}
	
	// 会員情報の変更→会員情報変更へ
	@GetMapping("/account-edit")
	public String showMemberEdit(HttpSession session, Model model) {
		// セッションからログイン中の会員情報を取得
		Object loginMember = session.getAttribute("account");
		if (loginMember == null) {
			return "redirect:/login";
		}
		// JSPでmemberという名前で会員データを扱えるようにする。
		model.addAttribute("member", loginMember);
		
		return "account-edit";
	}
	
}
