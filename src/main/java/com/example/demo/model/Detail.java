package com.example.demo.model;

public class Detail {
	private Long detailId;
	private Integer shoppingId;
	private Integer productId;
	private String productName;
	private Integer productPrice;
	private Integer productPieces;
	private Integer productTotal;
	
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
	
}
