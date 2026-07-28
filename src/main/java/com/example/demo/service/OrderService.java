package com.example.demo.service;

import java.sql.Connection;
import java.sql.SQLException;
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
import com.example.demo.util.DBUtil;

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
	public int createOrder(Account account, OrderInfo orderInfo) {
		List<Cart> cartList = cartService.getCartList(account.getAccountId());
		
		// カートが空の場合
		if (cartList == null || cartList.isEmpty()) {
			return 0;
		}
		
		int shoppingTotalPrice = 0;
		
		for (Cart cart : cartList) {
			Product product = productDAO.findById(Long.valueOf(cart.getProductId()));
			
			if (product == null) {
				throw new RuntimeException(
						"商品情報が見つかりません。商品ID：" + cart.getProductId());
				}
			
			shoppingTotalPrice += product.getProductPrice() * cart.getCartQuantity();
		}
		
		orderInfo.setShoppingTotalPrice(shoppingTotalPrice);
		
		try (Connection conn = DBUtil.getConnection()) {
			// 自動コミットを無効
			conn.setAutoCommit(false);
			try {
				// 注文ヘッダを登録
				int shoppingId = orderInfoDAO.insert(conn, orderInfo);
				
				if (shoppingId == 0) {
					throw new RuntimeException(
							"注文ヘッダの登録に失敗しました。");
				}
				
		
		// カートの商品をdetailへ登録
		for (Cart cart : cartList) {
			Product product = productDAO.findById(Long.valueOf(cart.getProductId()));
			
			if (product == null) {throw new RuntimeException(
					"商品情報が見つかりません。商品ID：" + cart.getProductId());
			}
			int productTotal =product.getProductPrice() * cart.getCartQuantity();
			
			boolean stockUpdated = productDAO.updateStockAfterOrder(conn, cart.getProductId(), cart.getCartQuantity());
			
			if (!stockUpdated) {
				throw new RuntimeException(
						"在庫が不足しています。商品ID：" + cart.getProductId());
		    }
			
			Detail detail = new Detail();
			detail.setShoppingId(shoppingId);
			detail.setProductId(cart.getProductId());
			detail.setProductName(product.getProductName());
			detail.setProductPrice(product.getProductPrice());
			detail.setProductPieces(cart.getCartQuantity());
			detail.setProductTotal(productTotal);
			boolean inserted =detailDAO.insert(conn,detail);
			
			if (!inserted) {throw new RuntimeException(
					"注文明細の登録に失敗しました。商品ID："
			+ cart.getProductId());
			}
			}
		
		// 注文完了後にカートを空にする
		boolean cleared = cartService.clearCart(conn, account.getAccountId());
		
		if (!cleared) {
			throw new RuntimeException(
			"カート情報の削除に失敗しました。");
		}
		conn.commit();
		return shoppingId;
		
			} catch (Exception e) {
				
				// 途中までの登録をすべて取り消す
				try {
					conn.rollback();
					
				} catch (SQLException rollbackException) {
					e.addSuppressed(rollbackException);
				}
				
				throw new RuntimeException(
						"注文確定処理に失敗しました。",
						e);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(
					"データベース接続に失敗しました。",
					e);
		}
	}
}
	