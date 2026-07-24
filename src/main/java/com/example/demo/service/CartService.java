package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.CartDAO;
import com.example.demo.model.Cart;

@Service
public class CartService {
	
	private final CartDAO cartDAO;
	
	public CartService(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
		}
	
	public List<Cart> getCartList(String accountId) {
		return cartDAO.findByAccountId(accountId);
		}
	
	public boolean addCart(String accountId,int productId,int quantity) {
		if (quantity < 1) {
			return false;
			}
		
		Cart existingCart = cartDAO.findByAccountIdAndProductId(accountId,productId);
		
		// 同じ商品がない場合
		if (existingCart == null) {
			Cart newCart = new Cart();
			
			newCart.setAccountId(accountId);
			newCart.setProductId(productId);
			newCart.setCartQuantity(quantity);
			
			return cartDAO.insert(newCart);
			}
		
		// 同じ商品がある時は数量を追加
		int newQuantity = existingCart.getCartQuantity() + quantity;
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
	

	// カート内の数量変更
	public boolean updateQuantity(long cartId, String accountId, int quantity) {

	    if (quantity < 1) {
	        return false;
	    }

	    return cartDAO.updateQuantity(cartId, accountId, quantity);
	}

}