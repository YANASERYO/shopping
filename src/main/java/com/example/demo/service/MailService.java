package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.model.OrderInfo;
import com.example.demo.util.PriceUtil;
import com.example.demo.util.TaxUtil;

import shopMail.ShopMail;

@Service
public class MailService {

	private static final int GROUP_NUMBER = 1;
	private static final int TEXT_MAIL = 0;

	public boolean sendOrderCompleteMail(OrderInfo orderInfo,List<Cart> cartList) {
		String orderDetails = "";
		for (Cart cart : cartList) {
			orderDetails += cart.getProductName() + " x " + cart.getCartQuantity() + "\n";}
		
		int shoppingTotalPrice = orderInfo.getShoppingTotalPrice();
		int taxPrice = TaxUtil.inflictTax(shoppingTotalPrice);
		int taxAndShoppingPrice = TaxUtil.inflictPriceAndTax(shoppingTotalPrice);
		
		String subject = "【KINARI】ご注文ありがとうございました";
		
		String body =
				"""
				%s 様
				ご注文ありがとうございました。
				注文内容は以下の通りです。
				
				【発送先】
				発送先氏名: %s
				発送先郵便番号: %s
				発送先住所: %s
				発送先電話番号: %s
				発送先お支払い方法: %s
				
				【ご注文商品】
				%s
				
				【金額】
				小計: %s 円
				消費税: %s 円
				合計: %s 円
				
				発送につきまして、ご不明な点がございましたら
				メールまたはお電話にてご連絡くださいませ。
				
				【求人募集】
				弊社では、営業職としてご活躍頂けるスタッフを募集しております。
				ご興味のある方は、下記URLよりご確認の上、
				採用担当　平島　へご連絡ください。
				http://localhost:8080/recruit

				今後ともKINARIオンラインショップをよろしくお願いいたします。
				
				【株式会社　KINARI】
				℡　092-482-8058
				📠　092-482-8059
				✉　kinarikinakina@kinakinari.xyz
				
				会社概要
				http://localhost:8080/company
				""".formatted(
				orderInfo.getShoppingUser(),
				orderInfo.getShippingName(),
				orderInfo.getShippingPostalCode(),
				orderInfo.getShippingAddress(),
				orderInfo.getShippingPhone(),
				orderInfo.getShippingPayment(),
				orderDetails,
				PriceUtil.formatWithCommas(shoppingTotalPrice),
				PriceUtil.formatWithCommas(taxPrice),
				PriceUtil.formatWithCommas(taxAndShoppingPrice));
				
		
		try {
			ShopMail.send(
					GROUP_NUMBER,
					orderInfo.getShippingEmail(),
					"KINARIオンラインショップ",
					subject,
					body,
					TEXT_MAIL);
			
			return true;
			
		} catch (Exception e) {
			System.err.println("注文完了メールの送信に失敗しました。");
			e.printStackTrace();
			return false;
		}
	}
}
