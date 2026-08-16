package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Account;
import com.example.demo.util.DBUtil;

//accountNameとaccountPassでログインすることになってたので修正しました（柳瀬）

//accountIdとaccountNameで読むようになってるので修正しました（柳瀬）

//ログイン処理


@Repository
public class AccountDAO {

	public Account findByAccountId(String accountId) {
	    String sql = """
	    		SELECT
	    		account_id,
			account_name,
			account_pass,
			postal_code,
			account_address,
			account_phone,
			birthday,
			email,
			payment,
			admin,
			account_active
			FROM accounts
			WHERE account_id = ?
	    		  	AND account_active = true
			""";
	    
	    try (Connection conn = DBUtil.getConnection();
	    		PreparedStatement pStmt = conn.prepareStatement(sql))
	    {
	    	
	    	pStmt.setString(1, accountId);
	    	
	    	try (ResultSet rs = pStmt.executeQuery()) {
	    		
	    		if (rs.next()) {
	    			Account account = new Account();
	    			
				account.setAccountId(rs.getString("account_id"));
				account.setAccountName(rs.getString("account_name"));
				account.setAccountPass(rs.getString("account_pass"));
				account.setPostalCode(rs.getString("postal_code"));
				account.setAccountAddress(rs.getString("account_address"));
				account.setAccountPhone(rs.getString("account_phone"));
				if (rs.getDate("birthday") != null) {
					account.setBirthday(
							rs.getDate("birthday").toLocalDate());
					}
				
				account.setEmail(rs.getString("email"));
				account.setPayment(rs.getString("payment"));
				account.setAdmin(rs.getBoolean("admin"));
				account.setAccountActive(rs.getBoolean("account_active"));
				return account;
				}
	    		}
	    	
	    } catch (SQLException e) {
	    	throw new RuntimeException("アカウント情報の取得に失敗しました。",e);
	    }
	    
	    return null;
	}
	
	
//    public Account login(String accountId,String accountPass) {
//    	Account account = null;
//    	
//    
//    	try(Connection conn = DriverManager.getConnection(
//    		"jdbc:postgresql://localhost:5432/shopping","postgres","psql")){
//    		String sql ="SELECT * FROM accounts "
//    		           + "WHERE account_id = ? "
//    	               + "AND account_pass = ?";
//    
//    
//    	PreparedStatement pStmt = conn.prepareStatement(sql);
//    	
//    	pStmt.setString(1, accountId);
//    	pStmt.setString(2, accountPass);
//    
//    	ResultSet rs = pStmt.executeQuery();
//    	
//    	if(rs.next()) {
//    		account = new Account();
//    		
//    		account.setAccountId(rs.getString("account_id"));
//    	    account.setAccountName(rs.getString("account_name"));
//    	    account.setAccountPass(rs.getString("account_pass"));
//    	    account.setPostalCode(rs.getString("postal_code"));
//    	    account.setAccountAddress(rs.getString("account_address"));
//    	    account.setAccountPhone(rs.getString("account_phone"));
//    	    account.setBirthday(rs.getDate("birthday").toLocalDate());
//    	    account.setEmail(rs.getString("email"));
//    	    account.setPayment(rs.getString("payment"));
//    	    account.setAdmin(rs.getBoolean("admin"));
//    	    
//    	}
//    	}catch (Exception e) {
//    	    e.printStackTrace();
//    	}
//    	return account;
//    }

    
//    物理削除する退会処理
//    
//    public boolean deleteAccount(String accountId) {
//    	
//    	boolean result = false;
//
//    	try(Connection conn = DriverManager.getConnection(
//    			"jdbc:postgresql://localhost:5432/shopping","postgres","psql")){
//    		String sql = "DELETE FROM accounts "
//	                   + "WHERE account_id = ?";
//    		
//    		PreparedStatement pStmt = conn.prepareStatement(sql);
//    		
//    		pStmt.setString(1, accountId);
//        
//    		int count = pStmt.executeUpdate();
//    		
//    		if(count>0) {result = true;}
//    		
//    }catch (Exception e) {
//        e.printStackTrace();
//    }
//
//    return result;
//    }

	// accountsTABLEにaccount_activeを追加し論理削除する退会処理
	public boolean deleteAccount(String accountId) {
	
		String sql = """
				UPDATE accounts
				SET account_active = false
				WHERE account_id = ?
				  AND account_active = true
				""";
	
		try (
				Connection conn = DBUtil.getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql)) {
			pStmt.setString(1, accountId);
			int count = pStmt.executeUpdate();
	
			return count == 1;
	
		} catch (SQLException e) {
			throw new RuntimeException(
					"退会処理に失敗しました。",
					e);
		}
	}

	//    会員登録
	public boolean insert(Account account) {
		String sql = """
				INSERT INTO accounts(
				account_id,
				account_name,
				account_pass,
				postal_code,
				account_address,
				account_phone,
				birthday,
				email,
				payment,
				admin
				)
				VALUES(?,?,?,?,?,?,?,?,?,?)
				""";
		
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql)) {
			pStmt.setString(1, account.getAccountId());
			pStmt.setString(2, account.getAccountName());
			pStmt.setString(3, account.getAccountPass());
			pStmt.setString(4, account.getPostalCode());
			pStmt.setString(5, account.getAccountAddress());
			pStmt.setString(6, account.getAccountPhone());
			if (account.getBirthday() != null) {
				pStmt.setDate(
						7,
						java.sql.Date.valueOf(account.getBirthday()));
			} else {
				pStmt.setDate(7, null);
			}
			pStmt.setString(8, account.getEmail());
			pStmt.setString(9, account.getPayment());
			pStmt.setBoolean(10, account.isAdmin());
			
			int count = pStmt.executeUpdate();
			
			return count == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
		
	}

//    	IDの重複に関して確認するメソッド
	public boolean existsByAccountId(String accountId) {

		String sql = """
				SELECT 1
				FROM accounts
				WHERE account_id = ?
				""";

		try (
				Connection conn = DBUtil.getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setString(1, accountId);

			try (ResultSet rs = pStmt.executeQuery()) {
				return rs.next();
			}

		} catch (SQLException e) {
			throw new RuntimeException(
					"アカウントIDの確認に失敗しました。",
					e);
		}
	}

	// 会員情報更新
	public boolean update(Account account) {

		boolean updatePassword = account.getAccountPass() != null;

		String sql = updatePassword ? """
				UPDATE accounts
				SET
					account_name = ?,
					account_pass = ?,
					postal_code = ?,
					account_address = ?,
					account_phone = ?,
					birthday = ?,
					email = ?,
					payment = ?
				WHERE account_id = ?
				""" : """
				UPDATE accounts
				SET
					account_name = ?,
					postal_code = ?,
					account_address = ?,
					account_phone = ?,
					birthday = ?,
					email = ?,
					payment = ?
				WHERE account_id = ?
				""";

		try (
				Connection conn = DBUtil.getConnection();
				PreparedStatement pStmt = conn.prepareStatement(sql)) {
			int parameterIndex = 1;

			pStmt.setString(
					parameterIndex++,
					account.getAccountName());

			// 新しいパスワードが入力された場合だけ更新
			if (updatePassword) {
				pStmt.setString(
						parameterIndex++,
						account.getAccountPass());
			}

			pStmt.setString(
					parameterIndex++,
					account.getPostalCode());

			pStmt.setString(
					parameterIndex++,
					account.getAccountAddress());

			pStmt.setString(
					parameterIndex++,
					account.getAccountPhone());

			if (account.getBirthday() != null) {
				pStmt.setDate(
						parameterIndex++,
						java.sql.Date.valueOf(
								account.getBirthday()));
			} else {
				pStmt.setDate(
						parameterIndex++,
						null);
			}

			pStmt.setString(
					parameterIndex++,
					account.getEmail());

			pStmt.setString(
					parameterIndex++,
					account.getPayment());

			pStmt.setString(
					parameterIndex,
					account.getAccountId());

			int count = pStmt.executeUpdate();

			return count == 1;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}

