package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Account;
import com.example.demo.model.Cart;
import com.example.demo.model.OrderInfo;
import com.example.demo.model.Product;
import com.example.demo.service.CartService;
import com.example.demo.service.MailService;
import com.example.demo.service.OrderService;
import com.example.demo.util.TaxUtil;


@Controller
public class OrderController {
	private final OrderService orderService;
	private final CartService cartService;
	private final ProductDAO productDAO;
	private final MailService mailService;
	
	public OrderController(OrderService orderService,CartService cartService,ProductDAO productDAO,MailService mailService){
		this.orderService = orderService;
		this.cartService = cartService;
	    this.productDAO = productDAO;
	    this.mailService = mailService;
		}
	
	// 次へ
	@PostMapping("/order/confirm")
	public String confirmOrder(
			@ModelAttribute OrderInfo orderInfo,
			HttpSession session,
			Model model) {
		
		Account account = (Account) session.getAttribute("account");
		
		// 未ログインの場合
		if (account == null) {
			return "redirect:/login";
		}
		

		List<Cart> cartList =
	            cartService.getCartList(account.getAccountId());
		
		if (cartList == null || cartList.isEmpty()) {
	        return "redirect:/cart";
	    }

	    int shoppingTotalPrice = 0;
		
		for (Cart cart : cartList) {
	        shoppingTotalPrice +=
	                cart.getProductPrice() * cart.getCartQuantity();
	    }

	    int taxPrice = TaxUtil.inflictTax(shoppingTotalPrice);
	    int taxAndShoppingPrice =
	            TaxUtil.inflictPriceAndTax(shoppingTotalPrice);

	    model.addAttribute("cartList", cartList);
	    model.addAttribute("orderInfo", orderInfo);
	    model.addAttribute("shoppingTotalPrice", shoppingTotalPrice);
	    model.addAttribute("taxPrice", taxPrice);
	    model.addAttribute("taxAndShoppingPrice", taxAndShoppingPrice);

	    return "order-confirm";

	}
	
	// 注文完了画面
	@PostMapping("/order/complete")
	public String completeOrder(HttpSession session,OrderInfo orderInfo,RedirectAttributes redirectAttributes) {
		Account account = (Account) session.getAttribute("account");

	    if (account == null) {
	        return "redirect:/login";
	    }
	    
	    
	    orderInfo.setShoppingUser(account.getAccountId());

	    int shoppingId = orderService.createOrder(account,orderInfo);

	    if (shoppingId == 0) {
	        return "redirect:/cart";
	    }
	    boolean mailSent =mailService.sendOrderCompleteMail(orderInfo);
	    
	    redirectAttributes.addFlashAttribute("mailSent",mailSent);
	    redirectAttributes.addFlashAttribute("shippingEmail",orderInfo.getShippingEmail());
	    redirectAttributes.addFlashAttribute("shoppingId",shoppingId);
	    
	    return "redirect:/order/complete";
	}
	
	@GetMapping("/order/complete")
	public String showComplete(HttpSession session) {

	    if (session.getAttribute("account") == null) {
	        return "redirect:/login";
	    }

	    return "order-complete";
	}

	//menu.jspからorder-infoへ遷移	
	@GetMapping("/order/buy")
	public String showOrderbuy(HttpSession session, Model model) {

		Account account = (Account) session.getAttribute("account");

		if (account == null) {
			return "redirect:/login";
		}
		
		List<Cart> cartList = cartService.getCartList(account.getAccountId());
		
		if (cartList == null || cartList.isEmpty()) {
			return "redirect:/cart";
			}
		
		int shoppingTotalPrice = 0;
		
		for (Cart cart : cartList) {
			Product product = productDAO.findById(Long.valueOf(cart.getProductId()));
			
			if (product != null) {
				shoppingTotalPrice += product.getProductPrice() * cart.getCartQuantity();
				}
		}
		
		int taxPrice = TaxUtil.inflictTax(shoppingTotalPrice);
		int taxAndShoppingPrice = TaxUtil.inflictPriceAndTax(shoppingTotalPrice);
		
		
		model.addAttribute("account", account);
		model.addAttribute("cartList", cartList);
		model.addAttribute("shoppingTotalPrice",shoppingTotalPrice);
		model.addAttribute("taxPrice", taxPrice);
		model.addAttribute("taxAndShoppingPrice",taxAndShoppingPrice
		);
		
		
	return "order-info";
}
}