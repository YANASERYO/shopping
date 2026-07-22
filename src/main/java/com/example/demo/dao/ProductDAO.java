package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			Long productId = rs.getLong("product_id");
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
		
//		IDから取得
		 public Product findById(Long productId) {
			 String sql = """
			 		SELECT
			 		product_id,
			 		product_name,
			 		product_price,
			 		product_stock,
			 		product_category,
			 		product_img_path,
			 		product_description,
			 		product_created_at,
			 		product_update_at,
			 		product_active
			 		FROM product
			 		WHERE product_id = ?
			 		""";
			 
			 try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
					 PreparedStatement pStmt = conn.prepareStatement(sql))
			 {
				 pStmt.setLong(1, productId);
				 try (ResultSet rs = pStmt.executeQuery()) {
					 if (rs.next()) {
						 return createProduct(rs);
					 }
				 }
			 } catch (SQLException e) {throw new RuntimeException(
					 "商品情報の取得に失敗しました。", e);
			 }
			 
			 return null;
		 }
		// 商品登録
		 public boolean insert(Product product) {
			 String sql = """
			 		INSERT INTO product (
			 		product_name,
			 		product_price,
			 		product_stock,
			 		product_category,
			 		product_img_path,
			 		product_description,
			 		product_created_at,
			 		product_update_at,
			 		product_active)
			 		VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, ?)
			 		""";
			 try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
					 PreparedStatement pStmt =conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS))
			 {
				 pStmt.setString(1, product.getProductName());
				 pStmt.setInt(2, product.getProductPrice());
				 pStmt.setInt(3, product.getProductStock());
				 pStmt.setString(4, product.getProductCategory());
				 pStmt.setString(5, product.getProductImgPath());
				 pStmt.setString(6, product.getProductDescription());
				 pStmt.setBoolean(7, product.isProductActive());
				 int count = pStmt.executeUpdate();
				 if (count > 0) {
					 try (ResultSet generatedKeys = pStmt.getGeneratedKeys()) {
						 if (generatedKeys.next()) {
							 product.setProductId(
									 generatedKeys.getLong(1));
							 }
						 }
					 return true;
					 }
				 } catch (SQLException e) {
					 throw new RuntimeException(
							 "商品の登録に失敗しました。", e);
					 }
			 return false;
			 }
		 
		 	// 商品更新
		 public boolean update(Product product) {
			 String sql = """
			 		UPDATE product
			 		SET
			 		product_name = ?,
			 		product_price = ?,
			 		product_stock = ?,
			 		product_category = ?,
			 		product_img_path = ?,
			 		product_description = ?,
			 		product_update_at = CURRENT_TIMESTAMP,
			 		product_active = ?
			 		WHERE product_id = ?
			 		""";
			 try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
					 PreparedStatement pStmt = conn.prepareStatement(sql)) 
			 {
				 pStmt.setString(1, product.getProductName());
				 pStmt.setInt(2, product.getProductPrice());
				 pStmt.setInt(3, product.getProductStock());
				 pStmt.setString(4, product.getProductCategory());
				 pStmt.setString(5, product.getProductImgPath());
				 pStmt.setString(6, product.getProductDescription());
				 pStmt.setBoolean(7, product.isProductActive());
				 pStmt.setLong(8, product.getProductId());
				 int count = pStmt.executeUpdate();
				 return count > 0;
				 } catch (SQLException e) {
					 throw new RuntimeException(
							 "商品の更新に失敗しました。", e);
					 }
			 }
		 
		 	// ResultSetからProductを作成する共通処理
		 private Product createProduct(ResultSet rs)throws SQLException {
			 Product product = new Product();
			 
			 product.setProductId(rs.getLong("product_id"));
			 product.setProductName(rs.getString("product_name"));
			 product.setProductPrice(rs.getInt("product_price"));
			 product.setProductStock(rs.getInt("product_stock"));
			 product.setProductCategory(rs.getString("product_category"));
			 product.setProductImgPath(rs.getString("product_img_path"));
			 product.setProductDescription(rs.getString("product_description"));
			 product.setProductCreatedAt(rs.getObject("product_created_at",LocalDateTime.class));
			 product.setProductUpdateAt(rs.getObject("product_update_at",LocalDateTime.class));
			 product.setProductActive(rs.getBoolean("product_active"));
			 return product;
		    }
		}

