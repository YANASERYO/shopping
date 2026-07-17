package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Cart;

public class CartDAO {
	private static final String URL =
			"jbdc:postgresql://localhost:5432/shopping";
	private static final String USER = "postgres";
	private static final String PASSWORD = "psql";
	
//	アカウントのカート一覧を取得
	public List<Cart>findByAccountID(String accountId){
		List<Cart> cartList = new ArrayList<>();
		
		String sql = """
				SELECT
				cart_id,
				account_id,
				product_id,
				cart_quantity,
				cart_created_at
				FROM cart
				WHERE account_id = ?
				ODER BY cart_created_at
				""";
		
		try(Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement pStmt = conn.prepareStatement(sql)
		){
			pStmt.setString(1,accountId);
			try(ResultSet rs = pStmt.executeQuery()){
				while(rs.next()) {
					Cart cart = new Cart();
					
					cart.setCartId(rs.getLong("cart_id"));
					cart.setAccountId(rs.getString("account_id"));
					cart.setProductId(rs.getInt("product_id"));
					cart.setCartQuantity(rs.getInt("cart_quantity"));
					
					Timestamp createAt =
							rs.getTimestamp("cart_created_at");
					if(createdAt != null) {
						cart.setCartCreatedAt(createdAt.toLocalDateTime());
					}
					
					cartList.add(cart);
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cartList;
		
	}
	
//	カートに商品を追加
	public boolean insert(Cart cart) {
		String sql = """
				INSERT INTO cart (
				account_id,
				product_id,
				cart_quantity,
				cart_created_at
				)
				VALUES (?, ?, ?, CURRENT_TIMESTAMP)
				""";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pStmt = conn.prepareStatement(sql)
        ) {
			pStmt.setString(1, cart.getAccountId());
			pStmt.setInt(2, cart.getProductId());
			pStmt.setInt(3, cart.getCartQuantity());
			
			int count = pStmt.executeUpdate();
			return count > 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return false;
	}

}

//カート内の数量の変更
//カートから商品を1件削除