package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Detail;
import com.example.demo.util.DBUtil;
import com.example.demo.util.TaxUtil;

@Repository
public class DetailDAO {
	// 注文明細を登録
	public boolean insert(Detail detail) {
		String sql = """
				INSERT INTO detail (
				shopping_id,
				product_id,
				product_name,
				product_price,
				product_pieces,
				product_total
				)
				VALUES (?, ?, ?, ?, ?, ?)
				""";
        try (Connection conn = DBUtil.getConnection();
        		PreparedStatement pStmt = conn.prepareStatement(sql)) 
        {
        	
				pStmt.setInt(1, detail.getShoppingId());
				pStmt.setInt(2, detail.getProductId());
				pStmt.setString(3, detail.getProductName());
				pStmt.setInt(4, detail.getProductPrice());
				pStmt.setInt(5, detail.getProductPieces());
				pStmt.setInt(6, detail.getProductTotal());
				
				int count = pStmt.executeUpdate();
				return count == 1;
        } catch (SQLException e) {
        		throw new RuntimeException(
        				"注文明細の登録に失敗しました。",e);
        }
    }
	
	public boolean insert(Connection conn, Detail detail)
			throws SQLException {
			String sql = """
			INSERT INTO detail (
				shopping_id,
				product_id,
				product_name,
				product_price,
				product_pieces,
				product_total
				)
				VALUES (?, ?, ?, ?, ?, ?)
				""";
			
		try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
			
				pStmt.setInt(1, detail.getShoppingId());
				pStmt.setInt(2, detail.getProductId());
				pStmt.setString(3, detail.getProductName());
				pStmt.setInt(4, detail.getProductPrice());
				pStmt.setInt(5, detail.getProductPieces());
				pStmt.setInt(6, detail.getProductTotal());
				
				return pStmt.executeUpdate() == 1;
		}
	}
	
//	購入履歴の取得
	public List<Detail> findByAccountId(String accountId){
		List<Detail> detailList = new ArrayList<>();
		
		String sql = """
				SELECT
				d.detail_id,
				d.shopping_id,
				d.product_id,
				d.product_name,
				d.product_price,
				d.product_pieces,
				d.product_total
				FROM detail d
				INNER JOIN order_info o
					ON d.shopping_id = o.shopping_id

				WHERE o.shopping_user = ?
				ORDER BY o.shopping_date DESC, d.detail_id
				""";
		
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)
		){
			statement.setString(1,accountId);
			
			try(ResultSet resultSet =
					statement.executeQuery()){
				while(resultSet.next()) {
					
					Detail detail = new Detail();
					
					detail.setDetailId(resultSet.getLong("detail_id"));
					detail.setShoppingId(resultSet.getInt("shopping_id"));
					detail.setProductId(resultSet.getInt("product_id"));
					detail.setProductName(resultSet.getString("product_name"));
					detail.setProductPrice(resultSet.getInt("product_price"));
					detail.setProductPieces(resultSet.getInt("product_pieces"));
					detail.setProductTotal(resultSet.getInt("product_total"));	

					
					int productTaxAndPrice = TaxUtil.inflictPriceAndTax(detail.getProductTotal());
					
					detail.setProductTaxAndPrice(productTaxAndPrice);

					detailList.add(detail);
				}
			}
			
			} catch (SQLException e) {
				throw new RuntimeException(
						"注文履歴の取得に失敗しました。" + e.getMessage(), e);
        }
		return detailList;
	}
}