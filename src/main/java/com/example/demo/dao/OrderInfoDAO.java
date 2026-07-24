package com.example.demo.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.OrderInfo;
import com.example.demo.util.DBUtil;

@Repository
public class OrderInfoDAO {
	public List<OrderInfo> findByAccountId(String accountId){
		List<OrderInfo> orderInfoList = new ArrayList<>();
		
		String sql = """
				SELECT
				shopping_id,
				shopping_user,
				shipping_name,
				shipping_postal_code,
				shipping_address,
				shipping_phone,
				shipping_email,
				shipping_payment,
				shopping_date
				FROM order_info
				WHERE shopping_user = ?
				ORDER BY order_date DESC
				""";
		try(Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)
		){
			statement.setString(1,accountId);
			
			try(ResultSet resultSet =
					statement.executeQuery()){
				while(resultSet.next()) {
					
					OrderInfo orderInfo = new OrderInfo();
					
					orderInfo.setShoppingId(resultSet.getInt("shopping_id"));
					orderInfo.setShoppingUser(resultSet.getString("shopping_user"));
					orderInfo.setShippingName(resultSet.getString("shipping_name"));
					orderInfo.setShippingPostalCode(resultSet.getString("shipping_postal_code"));
					orderInfo.setShippingAddress(resultSet.getString("shipping_address"));
					orderInfo.setShippingPhone(resultSet.getString("shipping_phone"));
					orderInfo.setShippingEmail(resultSet.getString("shipping_email"));
					orderInfo.setShippingPayment(resultSet.getString("shipping_payment"));
					if (resultSet.getTimestamp("shopping_date") != null) {
						orderInfo.setShoppingDate(resultSet.getTimestamp("shopping_date").toLocalDateTime());
						}
					orderInfoList.add(orderInfo);
					}
				}
			
			} catch (SQLException e) {
				throw new RuntimeException(
						"注文履歴の取得に失敗しました。", e);
        }
		return orderInfoList;
	}

	
	public int insert(OrderInfo orderInfo) {
		String sql = """
		INSERT INTO order_info (
		shopping_user,
		shipping_name,
		shipping_postal_code,
		shipping_address,
		shipping_phone,
		shipping_email,
		shipping_payment,
		shopping_date

		)
		VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)
		RETURNING shopping_id
		""";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql)) 
		{
			
			pStmt.setString(1, orderInfo.getShoppingUser());
			pStmt.setString(2, orderInfo.getShippingName());
			pStmt.setString(3, orderInfo.getShippingPostalCode());
			pStmt.setString(4, orderInfo.getShippingAddress());
			pStmt.setString(5, orderInfo.getShippingPhone());
			pStmt.setString(6, orderInfo.getShippingEmail());
			pStmt.setString(7, orderInfo.getShippingPayment());
			
			try (ResultSet rs = pStmt.executeQuery()) {
				if (rs.next()) {return rs.getInt("shopping_id");
				}
				}
			
		} catch (SQLException e) {throw new RuntimeException(
				"注文情報の登録に失敗しました。",e);
		}
		return 0;
	}

}

