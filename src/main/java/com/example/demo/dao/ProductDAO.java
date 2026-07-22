package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
public class ProductDAO {
	private static final String URL =
			"jdbc:postgresql://localhost:5432/shopping";
	private static final String USER = "postgres";
	private static final String PASSWORD = "psql";
	
	
	// 商品一覧を取得
		public List<Product> getAllProducts() {
			List<Product> productList = new ArrayList<>();
	// データベースへ接続
	try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
		
		String sql = "SELECT product_id, product_name, product_price, product_stock, product_category, "
				+	"product_img_path, product_description, product_created_at, product_update_at, product_active "
				+	"FROM product ";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		while (rs.next()) {
			int productId = rs.getInt("product_id");
			String productName = rs.getString("product_name");
			int productPrice = rs.getInt("product_price");
			int productStock = rs.getInt("product_stock");
			String productCategory = rs.getString("product_category");
			String productImgPath = rs.getString("product_img_path");
			String productDescription = rs.getString("product_description");
			LocalDateTime productCreatedAt = rs.getObject("product_created_at", LocalDateTime.class);
			LocalDateTime productUpdateAt = rs.getObject("product_update_at", LocalDateTime.class);
			boolean productActive = rs.getBoolean("product_active");
			
			Product product = new Product();
			product.setProductId(productId);
			product.setProductName(productName);
			product.setProductPrice(productPrice);
			product.setProductStock(productStock);
			product.setProductCategory(productCategory);
			product.setProductImgPath(productImgPath);
			product.setProductDescription(productDescription);
			product.setProductCreatedAt(productCreatedAt);
			product.setProductUpdateAt(productUpdateAt);
			product.setProductActive(productActive);
			
			productList.add(product);
		}
		
	} catch (SQLException e ) {
		e.printStackTrace();
	}
	
	return productList;

	}

}
