package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.DetailDAO;
import com.example.demo.dao.OrderInfoDAO;
import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Account;
import com.example.demo.model.Cart;
import com.example.demo.model.Detail;
import com.example.demo.model.OrderInfo;
import com.example.demo.model.Product;

@Service
public class OrderService {
	
	private final OrderInfoDAO orderInfoDAO;
	private final DetailDAO detailDAO;
	private final ProductDAO productDAO;
	private final CartService cartService;
	
	public OrderService(OrderInfoDAO orderInfoDAO,DetailDAO detailDAO,ProductDAO productDAO,CartService cartService)
	{
		this.orderInfoDAO = orderInfoDAO;
		this.detailDAO = detailDAO;
		this.productDAO = productDAO;
		this.cartService = cartService;
	}
	
	// 注文確定
	public int createOrder(Account account) {
		List<Cart> cartList = cartService.getCartList(account.getAccountId());
		
		// カートが空の場合
		if (cartList == null || cartList.isEmpty()) {
			return 0;
		}
		
		// 注文ヘッダを作成
		OrderInfo orderInfo = new OrderInfo();
		
		orderInfo.setShoppingUser(account.getAccountId());
		orderInfo.setShippingName(account.getAccountName());
		orderInfo.setShippingPostalCode(account.getPostalCode());
		orderInfo.setShippingAddress(account.getAccountAddress());
		orderInfo.setShippingPhone(account.getAccountPhone());
		orderInfo.setShippingEmail(account.getEmail());
		orderInfo.setShippingPayment(account.getPayment());
		
		// order_infoへ登録し、shopping_idを取得
		int shoppingId = orderInfoDAO.insert(orderInfo);
		
		if (shoppingId == 0) {
			return 0;
		}
		
		// カートの商品をdetailへ登録
		for (Cart cart : cartList) {
			Product product = productDAO.findById(Long.valueOf(cart.getProductId()));
			
			if (product == null) {throw new RuntimeException(
					"商品情報が見つかりません。商品ID：" + cart.getProductId());
			}
			int productTotal =product.getProductPrice() * cart.getCartQuantity();
			Detail detail = new Detail();
			detail.setShoppingId(shoppingId);
			detail.setProductId(cart.getProductId());
			detail.setProductName(product.getProductName());
			detail.setProductPrice(product.getProductPrice());
			detail.setProductPieces(cart.getCartQuantity());
			detail.setProductTotal(productTotal);
			boolean inserted =detailDAO.insert(detail);
			
			if (!inserted) {throw new RuntimeException(
					"注文明細の登録に失敗しました。商品ID："
			+ cart.getProductId());
			}
			}
		
		// 注文完了後にカートを空にする
		cartService.clearCart(account.getAccountId());
		return shoppingId;
	}
}