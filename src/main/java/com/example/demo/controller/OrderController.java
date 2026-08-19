package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.CartDAO;
import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Account;
import com.example.demo.model.Cart;
import com.example.demo.model.OrderInfo;
import com.example.demo.model.Product;
import com.example.demo.service.CartService;
import com.example.demo.service.MailService;
import com.example.demo.service.OrderService;
import com.example.demo.util.PriceUtil;
import com.example.demo.util.TaxUtil;


@Controller
public class OrderController {
	private final OrderService orderService;
	private final CartService cartService;
	private final ProductDAO productDAO;
	private final MailService mailService;
	private final CartDAO cartDAO;


	public OrderController(OrderService orderService, CartService cartService, ProductDAO productDAO,
			MailService mailService, CartDAO cartDAO) {
		this.orderService = orderService;
		this.cartService = cartService;
		this.productDAO = productDAO;
		this.mailService = mailService;
		this.cartDAO = cartDAO;
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


		List<Cart> cartList = cartService.getCartList(account.getAccountId());


		if (cartList == null || cartList.isEmpty()) {
			return "redirect:/cart";
		}

		int shoppingTotalPrice = 0;

		for (Cart cart : cartList) {
			shoppingTotalPrice += cart.getProductPrice() * cart.getCartQuantity();
		}

		int taxPrice = TaxUtil.inflictTax(shoppingTotalPrice);
		int taxAndShoppingPrice = TaxUtil.inflictPriceAndTax(shoppingTotalPrice);


		model.addAttribute("cartList", cartList);
		model.addAttribute("orderInfo", orderInfo);
		model.addAttribute("shoppingTotalPrice", PriceUtil.formatWithCommas(shoppingTotalPrice));
		model.addAttribute("taxPrice", PriceUtil.formatWithCommas(taxPrice));
		model.addAttribute("taxAndShoppingPrice", PriceUtil.formatWithCommas(taxAndShoppingPrice));

		String shippingAddress = orderInfo.getShippingAddress();

		if (shippingAddress != null) {
			shippingAddress = shippingAddress.trim();
			orderInfo.setShippingAddress(shippingAddress);
		}

		if (shippingAddress == null || shippingAddress.isBlank()) {
			model.addAttribute("shippingAddressError", "発送先住所を入力してください。");
			return "order-info";
		}

		boolean hasNumber = shippingAddress.matches(".*[0-9０-９].*");
		if (!hasNumber) {
			model.addAttribute("shippingAddressError", "番地まで含めた住所を入力してください。");
			return "order-info";
		}
		
		return "order-confirm";

	}

	// 注文完了画面
	@PostMapping("/order/complete")
	public String completeOrder(
			HttpSession session,
			@ModelAttribute OrderInfo orderInfo,
			RedirectAttributes redirectAttributes) {

		Account account = (Account) session.getAttribute("account");

		if (account == null) {
			return "redirect:/login";
		}

		try {
//			createorderされるとDB削除されるため、先んじてListを取得しておく
			List<Cart> cartList =
			        cartDAO.findByAccountId(account.getAccountId());

			if (cartList == null || cartList.isEmpty()) {
				redirectAttributes.addFlashAttribute(
						"cartError",
						"カートに商品がありません。");
				
				return "redirect:/cart";
			}
			
//			MailServiceで使用するアカウントの指定
			orderInfo.setShoppingUser(account.getAccountId());
			
//			カートリストの数、小計を加算する
			int shoppingTotalPrice = 0;
			
			for (Cart cart : cartList) {
				shoppingTotalPrice += cart.getProductPrice()
						* cart.getCartQuantity();
			}
			
			orderInfo.setShoppingTotalPrice(
					shoppingTotalPrice);
			
//			注文登録、明細登録、在庫更新、カート削除
			int shoppingId = orderService.createOrder(account, orderInfo);
			
			if (shoppingId == 0) {
				redirectAttributes.addFlashAttribute(
						"cartError",
						"注文を登録できませんでした。");
				
				return "redirect:/cart";
			}
		
			
//			cartListでMail送信
			boolean mailSent = mailService.sendOrderCompleteMail(
					orderInfo,
					cartList);
			
			redirectAttributes.addFlashAttribute(
					"mailSent",
					mailSent);
			redirectAttributes.addFlashAttribute(
					"shippingEmail",
					orderInfo.getShippingEmail());
			redirectAttributes.addFlashAttribute(
					"shoppingId",
					shoppingId);
			
			return "redirect:/order/complete";
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			
			redirectAttributes.addFlashAttribute(
					"cartError",
					"注文処理中にエラーが発生しました。");
			
			return "redirect:/cart";
		}
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
	public String showOrderbuy(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		
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
			
			if (product == null) {
				redirectAttributes.addFlashAttribute(
						"cartError",
						"カート内の商品情報が見つかりません。該当商品をカートから削除してください。");
				
				return "redirect:/cart";
			}
			
			if (!product.isProductActive()) {
				redirectAttributes.addFlashAttribute(
						"cartError",
						product.getProductName() + "は現在販売を停止しています。カートから削除してください。");
				
				return "redirect:/cart";
			}
			
			if (product.getProductStock() < cart.getCartQuantity()) {
				redirectAttributes.addFlashAttribute(
						"cartError",
						product.getProductName()
								+ "の在庫が不足しています。"
								+ "現在の在庫数は"
								+ product.getProductStock()
								+ "個です。数量を変更してください。");
				
				return "redirect:/cart";
			}
			
			shoppingTotalPrice += product.getProductPrice() * cart.getCartQuantity();
		}
		
		int taxPrice = TaxUtil.inflictTax(shoppingTotalPrice);
		int taxAndShoppingPrice = TaxUtil.inflictPriceAndTax(shoppingTotalPrice);
		
		model.addAttribute("account", account);
		model.addAttribute("cartList", cartList);

		model.addAttribute("shoppingTotalPrice", PriceUtil.formatWithCommas(shoppingTotalPrice));
		model.addAttribute("taxPrice", PriceUtil.formatWithCommas(taxPrice));
		model.addAttribute("taxAndShoppingPrice", PriceUtil.formatWithCommas(taxAndShoppingPrice));

		
		return "order-info";
	}
}