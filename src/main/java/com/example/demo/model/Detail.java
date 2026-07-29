package com.example.demo.model;

import java.time.LocalDateTime;

import com.example.demo.util.TaxUtil;

public class Detail {
	private Long detailId;
	private Integer shoppingId;
	private Integer productId;
	private String productName;
	private Integer productPrice;
	private Integer productPieces;
	private Integer productTotal;
	
//	表示用
	private LocalDateTime shoppingDate;
	private Integer shoppingTotalPrice;
	private Integer productTaxAndPrice;
	
//	発送内容の表示
	private String shippingName;
	private String shippingPostalCode;
	private String shippingAddress;
	private String shippingPhone;
	private String shippingEmail;
	private String shippingPayment;
	
	public Detail() {}
	
	public Detail(Long detailId, Integer shoppingId,Integer productId,String productName,
					Integer productPrice,Integer productPieces,Integer productTotal ) {
		this.detailId = detailId;
		this.shoppingId = shoppingId;
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productPieces = productPieces;
		this.productTotal = productTotal;
	}

	public Long getDetailId() {return detailId;}
	public void setDetailId(Long detailId) {this.detailId = detailId;}
	
	public Integer getShoppingId() {return shoppingId;}
	public void setShoppingId(Integer shoppingId) {this.shoppingId = shoppingId;}

	public Integer getProductId() {return productId;}
	public void setProductId(Integer productId) {this.productId = productId;}
	
	public String getProductName() {return productName;}
	public void setProductName(String productName) {this.productName = productName;}
	
	public Integer getProductPrice() {return productPrice;}
	public void setProductPrice(Integer productPrice) {this.productPrice = productPrice;}
	
	public Integer getProductPieces() {return productPieces;}
	public void setProductPieces(Integer productPieces) {this.productPieces = productPieces;}
	
	public Integer getProductTotal() {return productTotal;}
	public void setProductTotal(Integer productTotal) {this.productTotal = productTotal;}
	
//	表示用
	public LocalDateTime getShoppingDate() {return shoppingDate;}
	public void setShoppingDate(LocalDateTime shoppingDate) {this.shoppingDate = shoppingDate;}

	public Integer getShoppingTotalPrice() {return shoppingTotalPrice;}
	public void setShoppingTotalPrice(Integer shoppingTotalPrice) {this.shoppingTotalPrice = shoppingTotalPrice;}
	
	public Integer getProductTaxAndPrice() {return productTaxAndPrice;}
	public void setProductTaxAndPrice(Integer productTaxAndPrice) {this.productTaxAndPrice = productTaxAndPrice;}
	
//	shoppingIdごとの税込み金額
	public Integer getShoppingTaxAndPrice() {

		if (shoppingTotalPrice == null) {
			return 0;
		}

		return TaxUtil.inflictPriceAndTax(shoppingTotalPrice);
	}
	
//	order-historyにorder-infoの表示
	public String getShippingName() {return shippingName;}
	public void setShippingName(String shippingName) {this.shippingName = shippingName;}
	
	public String getShippingPostalCode() {return shippingPostalCode;}
	public void setShippingPostalCode(String shippingPostalCode) {this.shippingPostalCode = shippingPostalCode;}

	public String getShippingAddress() {return shippingAddress;}
	public void setShippingAddress(String shippingAddress) {this.shippingAddress = shippingAddress;}

	public String getShippingPhone() {return shippingPhone;}
	public void setShippingPhone(String shippingPhone) {this.shippingPhone = shippingPhone;}

	public String getShippingEmail() {return shippingEmail;}
	public void setShippingEmail(String shippingEmail) {this.shippingEmail = shippingEmail;}

	public String getShippingPayment() {return shippingPayment;}
	public void setShippingPayment(String shippingPayment) {this.shippingPayment = shippingPayment;}
}
