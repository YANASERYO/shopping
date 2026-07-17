package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.demo.model.Account;

//ログイン処理
public class AccountDAO {
    public Account login(String accountName,String accountPass) {
    	Account account = null;
    	
    
    	try(Connection conn = DriverManager.getConnection(
    		"jdbc:postgresql://localhost:5432/shopping","postgres","psql")){
    		String sql ="SELECT * FROM accounts "
    		           + "WHERE account_name = ? "
    	               + "AND account_pass = ?";
    
    
    	PreparedStatement pStmt = conn.prepareStatement(sql);
    	
    	pStmt.setString(1, accountName);
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
}
