package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Account;
import com.example.demo.model.Cart;
import com.example.demo.model.OrderInfo;
import com.example.demo.model.Product;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import com.example.demo.util.TaxUtil;


@Controller
public class OrderController {
	private final OrderService orderService;
	private final CartService cartService;
	private final ProductDAO productDAO;
	
	public OrderController(OrderService orderService,CartService cartService,ProductDAO productDAO){
		this.orderService = orderService;
		this.cartService = cartService;
	    this.productDAO = productDAO;
		}
	
	// 注文確定
	@PostMapping("/order/confirm")
	public String confirmOrder(
			@RequestParam String shippingName,
			@RequestParam String shippingPostalCode,
			@RequestParam String shippingAddress,
			@RequestParam String shippingPhone,
			@RequestParam String shippingEmail,
			@RequestParam String shippingPayment,
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		
		Account account = (Account) session.getAttribute("account");
		
		// 未ログインの場合
		if (account == null) {
			return "redirect:/login";
		}
		
		if (shippingName == null || shippingName.isBlank()
				|| shippingPostalCode == null || shippingPostalCode.isBlank()
				|| shippingAddress == null || shippingAddress.isBlank()
				|| shippingPhone == null || shippingPhone.isBlank()
				|| shippingEmail == null || shippingEmail.isBlank()
				|| shippingPayment == null || shippingPayment.isBlank()) {
			redirectAttributes.addFlashAttribute("orderError","未入力の項目があります。");
			
		    return "redirect:/order/buy";
		}
		
		if (!shippingPostalCode.matches("\\d{3}-?\\d{4}")) {
			
			redirectAttributes.addFlashAttribute("orderError","郵便番号は123-4567の形式で入力してください。");
			return "redirect:/order/buy";
		}
		
		if (!shippingPhone.matches("[0-9-]{10,13}")) {
			
			redirectAttributes.addFlashAttribute("orderError","電話番号を正しい形式で入力してください。");
			return "redirect:/order/buy";
		}
		
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setShoppingUser(account.getAccountId());
		orderInfo.setShippingName(shippingName);
		orderInfo.setShippingPostalCode(shippingPostalCode);
		orderInfo.setShippingAddress(shippingAddress);
		orderInfo.setShippingPhone(shippingPhone);
		orderInfo.setShippingEmail(shippingEmail);
		orderInfo.setShippingPayment(shippingPayment);
		
		int shoppingId;
		try {
		    shoppingId = orderService.createOrder(account,orderInfo);
		} catch (RuntimeException e) {
		    redirectAttributes.addFlashAttribute("orderError","注文を確定できませんでした。在庫状況をご確認のうえ、もう一度お試しください。"
		    		);
		    return "redirect:/order/buy";
		}
		
		if (shoppingId == 0) {
			redirectAttributes.addFlashAttribute("cartError","カートに商品がありません。");
			return "redirect:/cart";
		}
		
		redirectAttributes.addFlashAttribute("shoppingId",shoppingId);
		return "redirect:/order/complete";
	}
	
	// 注文完了画面
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