package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		
		Account account = (Account) session.getAttribute("account");
		
		// 未ログインの場合
		if (account == null) {
			return "redirect:/login";
			}
		
		// 数量が不正な場合
		if (quantity <= 0) {
			redirectAttributes.addFlashAttribute("cartError","数量は1個以上を指定してください。");
			return "redirect:/products";
			}
		
		boolean added = cartService.addCart(account.getAccountId(),productId,quantity);
		
		if (!added) {
			redirectAttributes.addFlashAttribute("cartError","商品をカートに追加できませんでした。在庫数または販売状況をご確認ください。");
			return "redirect:/products";
		}
		
			redirectAttributes.addFlashAttribute("cartMessage","商品をカートに追加しました。");
			return "redirect:/products";
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
			@RequestParam int productId,
			@RequestParam int quantity,
			HttpSession session,
			RedirectAttributes redirectAttributes){
		
		Account account = (Account)session.getAttribute("account");
		
		if (account == null) {
		    return "redirect:/login";
		}
		
		if (quantity <= 0) {
			redirectAttributes.addFlashAttribute("cartError","数量は1個以上を指定してください。");
	        return "redirect:/cart";
	    }
		
		boolean updated = cartService.updateQuantity(
				cartId,
				account.getAccountId(),
				productId,
				quantity
				);
		
		if (!updated) {
			redirectAttributes.addFlashAttribute("cartError","数量を変更できませんでした。在庫数をご確認ください。");
			return "redirect:/cart";
		}
		
		redirectAttributes.addFlashAttribute("cartMessage","商品の数量を変更しました。");
	    return "redirect:/cart";
	}

	// カートから商品削除
	@PostMapping("/cart/delete")
	public String deleteCart(@RequestParam long cartId,HttpSession session,
			RedirectAttributes redirectAttributes) {
		
		Account account =(Account) session.getAttribute("account");
		
		// 未ログインの場合
		if (account == null) {
			return "redirect:/login";
		}
		
		
		boolean removed = cartService.removeCart(cartId,account.getAccountId());
		
		if (!removed) {
			redirectAttributes.addFlashAttribute("cartError","商品をカートから削除できませんでした。");
			return "redirect:/cart";
			}
		
		redirectAttributes.addFlashAttribute("cartMessage","商品をカートから削除しました。");
		
		return "redirect:/cart";
	}

}