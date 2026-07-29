package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Cart;
import com.example.demo.util.DBUtil;

@Repository
public class CartDAO {
	
//	アカウントのカート一覧を取得
	public List<Cart>findByAccountId(String accountId){
		List<Cart> cartList = new ArrayList<>();
		
		String sql = """
				SELECT
				c.cart_id,
				c.account_id,
				c.product_id,
				c.cart_quantity,
				c.cart_created_at,
				p.product_name,
				p.product_img_path,
				p.product_price
				FROM cart c
				INNER JOIN product p
				ON c.product_id = p.product_id
				WHERE c.account_id = ?
				ORDER BY c.cart_created_at
				""";
		
		try (Connection conn = DBUtil.getConnection();
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
					cart.setProductName(rs.getString("product_name"));
					cart.setProductImgPath(rs.getString("product_img_path"));
					cart.setProductPrice(rs.getInt("product_price"));
					Timestamp createdAt =
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
	
//	カートに商品が存在するかの確認
	public Cart findByAccountIdAndProductId(String accountId, int productId) {
			String sql = """
				SELECT
				cart_id,
				account_id,
				product_id,
				cart_quantity,
				cart_created_at
				FROM cart
				WHERE account_id = ?
				AND product_id = ?
				""";
			
			try (Connection connection = DBUtil.getConnection();
					PreparedStatement statement = connection.prepareStatement(sql)
							) {
				statement.setString(1, accountId);
				statement.setInt(2, productId);
				
				try (ResultSet resultSet = statement.executeQuery()) {
					
					if (resultSet.next()) {
						Cart cart = new Cart();
						
						cart.setCartId(resultSet.getLong("cart_id"));
						cart.setAccountId(resultSet.getString("account_id"));
						cart.setProductId(resultSet.getInt("product_id"));
						cart.setCartQuantity(resultSet.getInt("cart_quantity"));
						cart.setCartCreatedAt(
								resultSet.getTimestamp("cart_created_at").toLocalDateTime()
								);
						
						return cart;
						}
					}
				
			} catch (SQLException e) {
				throw new RuntimeException("カート情報の取得に失敗しました。", e);}
			
			return null;
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

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql)) {
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
	
//カート内の数量の変更
	public boolean updateQuantity(long cartId,String accountId,int quantity) {
		String sql = """
				UPDATE cart
				SET cart_quantity = ?
				WHERE cart_id = ?
				AND account_id = ?
				""";
		
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql)) {
			pStmt.setInt(1, quantity);
			pStmt.setLong(2, cartId);
			pStmt.setString(3, accountId);
			
			int count = pStmt.executeUpdate();
			
			return count > 0;
			
	    } catch (SQLException e) {
	    	throw new RuntimeException(
	    			"カート内商品の数量変更に失敗しました。",
	    			e
	    			);
	    	}
	}

//カートから商品を1件削除
	public boolean delete(long cartId,String accountId) {
		String sql = """
				DELETE FROM cart
				WHERE cart_id = ?
				AND account_id = ?
				""";
		
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql)) {
			pStmt.setLong(1, cartId);
			pStmt.setString(2, accountId);
			
			int count = pStmt.executeUpdate();
			
			return count > 0;
			
		} catch (SQLException e) {
			throw new RuntimeException(
					"カート内商品の削除に失敗しました。",
					e
					);
			}

	}
	
	// 注文確定後にカートを空にする
	public boolean deleteByAccountId(String accountId) {
	    String sql = """
	    			DELETE FROM cart
	    			WHERE account_id = ?
	    			""";
	    try (Connection conn = DBUtil.getConnection();
	    		PreparedStatement pStmt = conn.prepareStatement(sql)) 
	    {
	    	
	    		pStmt.setString(1, accountId);
	    		pStmt.executeUpdate();
	    		return true;
	    		
	    } catch (SQLException e) {
	    		throw new RuntimeException(
	    		"カート情報の削除に失敗しました。",
	    		e
	        );
	    }
	}
	
	public boolean deleteByAccountId(Connection conn,String accountId) 
		throws SQLException {
		String sql = """
				DELETE FROM cart
				WHERE account_id = ?
				""";
		
		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
			
			pStmt.setString(1, accountId);
			pStmt.executeUpdate();
			
			return true;
			}
		}

}