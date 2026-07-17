package com.example.demo.util;

public class TaxUtil {
	private static final double SALES_TAX = 0.10;
	
	private TaxUtil() {}
	
//	消費税の計算
	public static int inflictTax(int price) {
		return(int)Math.floor(price * SALES_TAX);
	}
	
//	合計金額
	public static int inflictPriceAndTax(int price) {
		return price + inflictTax(price);
	}
}
