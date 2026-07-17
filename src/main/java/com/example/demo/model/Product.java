package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

//Lombokのimport文を追加/柳瀬
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//キャメルケースでお願いします（修正してください）
//AllArgsを使用していますので、全項目のコンストラクタは必要ないです
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
	private int productAd; // 商品ID
	private String productName; // 商品名
	private int productPrice; // 値段
	private int productStock; // 在庫数
	private String productCategory; // カテゴリー
	private String productImgPath; // 画像
	private String productDescription; // 商品説明文
	private LocalDateTime productCreated_at; // 登録日時
	private LocalDateTime productUpdateAt; // 更新日時
	private boolean productActive; // 販売状態	

}
