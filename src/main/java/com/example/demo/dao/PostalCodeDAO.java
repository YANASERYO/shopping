package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.example.demo.model.PostalCode;
import com.example.demo.util.DBUtil;

@Repository
public class PostalCodeDAO {
	
	public PostalCode findByPostalCode(String postalCode) {
		
		String sql = """
				SELECT
				    postal_code,
				    prefecture,
				    city,
				    town
				FROM postal_code_master
				WHERE postal_code = ?
				ORDER BY postal_code_id
				LIMIT 1
				""";
		
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql)
		) {
			pStmt.setString(1, postalCode);
			
			try (ResultSet rs = pStmt.executeQuery()) {
				
				if (rs.next()) {
					return new PostalCode(
							rs.getString("postal_code"),
							rs.getString("prefecture"),
							rs.getString("city"),
							rs.getString("town")
					);
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(
					"郵便番号から住所を取得できませんでした。", e);
		}
		
		return null;
	}
}