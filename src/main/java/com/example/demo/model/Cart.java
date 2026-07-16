package com.example.demo.model;

import java.time.LocalDateTime;

public class Cart {
	private Long cartId;
	private String accountId;
	private Integer productId;
	private Integer cartQuantity;
	private LocalDateTime cartCreatedAt; 
	
	public Cart() {}
	
	public Cart(Long cartId,String accountId,Integer productId,Integer cartQuantity,LocalDateTime cartCreatedAt) {
		this.cartId = cartId;
		this.accountId = accountId;
		this.productId = productId;
		this.cartQuantity = cartQuantity;
		this.cartCreatedAt = cartCreatedAt;
	}
	
	public Long getCartId() {return cartId;}
	public void setCartId(Long cartId) {this.cartId = cartId;}
	
	public String getAccountId() {return accountId;}
	public void setAccountId(String accountId) {this.accountId = accountId;}
	
	public Integer getProductId() {return productId;}
	public void setProductId(Integer productId) {this.productId = productId;}

	public Integer getCartQuantity() {return cartQuantity;}
	public void setCartQuantity(Integer cartQuantity) {this.cartQuantity = cartQuantity;}
	
	public LocalDateTime getCartCreatedAt() {return cartCreatedAt;}
	public void setCartCreatedAt(LocalDateTime cartCreatedAt) {this.cartCreatedAt = cartCreatedAt;}
	
}
