package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Detail;
import com.example.demo.util.DBUtil;

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
}