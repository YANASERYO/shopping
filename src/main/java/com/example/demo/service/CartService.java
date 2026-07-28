package com.example.demo.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.CartDAO;
import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;

@Service
public class CartService {
	
	private final CartDAO cartDAO;
	private final ProductDAO productDAO;
	
	public CartService(CartDAO cartDAO,ProductDAO productDAO) {
		this.cartDAO = cartDAO;
		this.productDAO = productDAO;
		}
	
	public List<Cart> getCartList(String accountId) {
		return cartDAO.findByAccountId(accountId);
		}
	
	public boolean addCart(String accountId,int productId,int quantity) {
		if (quantity < 1) {
			return false;
			}
		Product product = productDAO.findById((long) productId);
		
		if (product == null) {
	        return false;
	    }
		
		Cart existingCart = cartDAO.findByAccountIdAndProductId(accountId,productId);
		
		
		// 同じ商品がない場合
		if (existingCart == null) {
			if (quantity > product.getProductStock()) {
				return false;
	        }
			Cart newCart = new Cart();
			
			newCart.setAccountId(accountId);
			newCart.setProductId(productId);
			newCart.setCartQuantity(quantity);
			
			return cartDAO.insert(newCart);
			}
		
		// 同じ商品がある時は数量を追加
		int newQuantity = existingCart.getCartQuantity() + quantity;
		if (newQuantity > product.getProductStock()) {
			return false;
		}
		return cartDAO.updateQuantity(existingCart.getCartId(),accountId,newQuantity);
	}
	
	// カートから商品を削除
	public boolean removeCart(long cartId,String accountId) {
		return cartDAO.delete(cartId, accountId);
	}

	

	// 注文確定後にカートを空に
	public boolean clearCart(String accountId) {
	    return cartDAO.deleteByAccountId(accountId);
	}
	
	public boolean clearCart(Connection conn,String accountId)
			throws SQLException {
		return cartDAO.deleteByAccountId(conn, accountId);
  	}
	

	// カート内の数量変更
	public boolean updateQuantity(long cartId, String accountId, int productId,int quantity) {
		
		if (quantity <= 0) {
			return false;
		}
		
		Product product = productDAO.findById((long) productId);
		
		if (product == null) {
			return false;
		}
		
		if (quantity > product.getProductStock()) {
			return false;
		}
		
		return cartDAO.updateQuantity(cartId,accountId,quantity);
	}
	

}