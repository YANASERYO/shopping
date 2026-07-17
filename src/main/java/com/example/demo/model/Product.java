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
	private int product_id; // 商品ID
	private String product_name; // 商品名
	private int product_price; // 値段
	private int product_stock; // 在庫数
	private String product_category; // カテゴリー
	private String product_img_path; // 画像
	private String product_description; // 商品説明文
	private LocalDateTime product_created_at; // 登録日時
	private LocalDateTime product_update_at; // 更新日時
	private boolean product_active; // 販売状態
	
//	public Product(int product_id, String product_name, int product_price, int product_stock, String product_category,
//					String product_img_path, String product_description, LocalDateTime product_created_at,
//					LocalDateTime product_update_at, boolean product_active) {
//		this.product_id = product_id;
//		this.product_name = product_name;
//		this.product_price = product_price;
//		this.product_stock = product_stock;
//		this.product_category = product_category;
//		this.product_img_path = product_img_path;
//		this.product_description = product_description;
//		this.product_created_at = product_created_at;
//		this.product_update_at = product_update_at;
//		this.product_active = product_active;
//	}
	
	

}
