package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.model.Account;
import com.example.demo.util.DBUtil;

//accountNameとaccountPassでログインすることになってたので修正しました（柳瀬）

//accountIdとaccountNameで読むようになってるので修正しました（柳瀬）

//ログイン処理
public class AccountDAO {
    public Account login(String accountId,String accountPass) {
    	Account account = null;
    	
    
    	try(Connection conn = DriverManager.getConnection(
    		"jdbc:postgresql://localhost:5432/shopping","postgres","psql")){
    		String sql ="""
    		        SELECT *
    		        FROM accounts
    		        WHERE account_id = ?
    		        """;
    
    	PreparedStatement pStmt = conn.prepareStatement(sql);
    	
    	pStmt.setString(1, accountId);
    	pStmt.setString(2, accountPass);
    
    	ResultSet rs = pStmt.executeQuery();
    	
    	if(rs.next()) {
    		account = new Account();
    		
    		account.setAccountId(rs.getString("account_id"));
    	    account.setAccountName(rs.getString("account_name"));
    	    account.setAccountPass(rs.getString("account_pass"));
    	    account.setPostalCode(rs.getString("postal_code"));
    	    account.setAccountAddress(rs.getString("account_address"));
    	    account.setAccountPhone(rs.getString("account_phone"));
    	    account.setBirthday(rs.getDate("birthday").toLocalDate());
    	    account.setEmail(rs.getString("email"));
    	    account.setPayment(rs.getString("payment"));
    	    account.setAdmin(rs.getBoolean("admin"));
    	    
    	}
    	}catch (Exception e) {
    	    e.printStackTrace();
    	}
    	return account;
    }
    
    //退会処理
    
    public boolean deleteAccount(String accountId) {
    	
    	boolean result = false;

    	try(Connection conn = DriverManager.getConnection(
    			"jdbc:postgresql://localhost:5432/shopping","postgres","psql")){
    		String sql = "DELETE FROM accounts "
	                   + "WHERE account_id = ?";
    		
    		PreparedStatement pStmt = conn.prepareStatement(sql);
    		
    		pStmt.setString(1, accountId);
        
    		int count = pStmt.executeUpdate();
    		
    		if(count>0) {result = true;}
    		
    }catch (Exception e) {
        e.printStackTrace();
    }

    return result;
    }
    
// // accountsTABLEにaccount_activeを追加し論理削除する退会処理の例
//    public boolean deleteAccount(String accountId) {
//    	
//    	String sql = """
//    			UPDATE accounts
//    			SET account_active = false
//    			WHERE account_id = ?
//    			  AND account_active = true
//    			""";
//    	
//    	try (
//    			Connection conn = DBUtil.getConnection();
//    			PreparedStatement pStmt = conn.prepareStatement(sql)
//    	) {
//    		pStmt.setString(1, accountId);
//    		int count = pStmt.executeUpdate();
//    		
//    		return count == 1;
//    		
//    	} catch (SQLException e) {
//    		e.printStackTrace();
//    		return false;
//    		}
//    }
    
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
	    	PreparedStatement pStmt = conn.prepareStatement(sql)
	    	){
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
	    		pStmt.setBoolean(10,account.isAdmin());
	    		
	    		int count = pStmt.executeUpdate();
	    		
	    		return count == 1;
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    		return false;
	    		
	    	}
	    		
    }
    
//    会員情報更新
    public boolean update(Account account) {
    		String sql = """
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
    		
    		try (Connection conn = DBUtil.getConnection();
    		    	PreparedStatement pStmt = conn.prepareStatement(sql)
    		    	){
    			pStmt.setString(1, account.getAccountName());
    			pStmt.setString(2, account.getPostalCode());
    			pStmt.setString(3, account.getAccountAddress());
    			pStmt.setString(4, account.getAccountPhone());
    			if (account.getBirthday() != null) {
    				pStmt.setDate(
    						5,
    						java.sql.Date.valueOf(account.getBirthday()));
    			} else {
    				pStmt.setDate(5, null);
    			}
    			pStmt.setString(6, account.getEmail());
    			pStmt.setString(7, account.getPayment());
    			pStmt.setString(8, account.getAccountId());
    			
    			int count = pStmt.executeUpdate();
    			
    			return count == 1;
    			
    		} catch (SQLException e) {
    			e.printStackTrace();
    			return false;
    		}
    	}
}

