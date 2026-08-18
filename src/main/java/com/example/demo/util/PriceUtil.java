package com.example.demo.util;

import java.text.NumberFormat;
import java.util.Locale;

public class PriceUtil {
	private PriceUtil() {
	}
	
	// 数値をカンマ区切りの文字列に変換
	public static String formatWithCommas(int number) {
		NumberFormat formatter = NumberFormat.getInstance(Locale.US);
		return formatter.format(number);
	}

}
