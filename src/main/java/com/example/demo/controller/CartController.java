package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.model.Cart;
import com.example.demo.service.CartService;
@Controller
public class CartController {
	
	private final CartService cartService;
	
	public CartController(CartService cartService) {
		this.cartService = cartService;
		}
	
	// 商品をカートに追加
	@PostMapping("/cart/add")
	public String addCart(
			@RequestParam("productId") int productId,
			@RequestParam("quantity") int quantity,
			HttpSession session) {
		
		Account account = (Account) session.getAttribute("account");
		
		// 未ログインの場合
		if (account == null) {
			return "redirect:/login";
			}
		
		cartService.addCart(account.getAccountId(),productId,quantity);
			return "redirect:/Products";
	}
	
	//	カート一覧表示
	@GetMapping("/cart")
		public String showCart(HttpSession session,Model model) {
		
		Account account = (Account)session.getAttribute("account");
			
		if (account == null) {
			return "redirect:/login";
		}
		
		 List<Cart> cartList = cartService.getCartList(account.getAccountId());
		 
		 model.addAttribute("cartList", cartList);
		 
		 return "cart";
		}

	//カート一覧で数量変更
	@PostMapping("/cart/update")
	public String updateCart(
			@RequestParam long cartId,
			@RequestParam int quantity,
			HttpSession session){
		
		Account account = (Account)session.getAttribute("account");
		
		if (account == null) {
		    return "redirect:/login";
		}
		
		cartService.updateQuantity(cartId,account.getAccountId(),quantity);
		
		return "redirect:/cart";
	}
	
}