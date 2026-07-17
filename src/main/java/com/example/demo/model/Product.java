package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

//Lombokのimport文を追加/柳瀬
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//キャメルケースでお願いします（修正してください）
//AllArgsを使用していますので、全項目のコンストラクタは必要ないです
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

	private int productId; // 商品ID
	private String productName; // 商品名
	private int productPrice; // 値段
	private int productStock; // 在庫数
	private String productCategory; // カテゴリー
	private String productImgPath; // 画像
	private String productDescription; // 商品説明文
	private LocalDateTime productCreatedAt; // 登録日
	private LocalDateTime productUpdateAt; // 更新日時
	private boolean productActive; // 販売状態	

}
