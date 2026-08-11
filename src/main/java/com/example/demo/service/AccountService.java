package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.AccountDAO;
import com.example.demo.model.Account;
import com.example.demo.util.PassEncoderUtil;

@Service
public class AccountService {
	private final AccountDAO accountDAO;
	
	public AccountService(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
//	会員の登録
	public boolean register(Account account) {
		if(account == null) {
			return false;
		}
//		入力チェック
		if(isBlank(account.getAccountId())
				|| isBlank(account.getAccountPass())
				|| isBlank(account.getAccountName())) {
			return false;
		}
		
		if (accountDAO.existsByAccountId(
				account.getAccountId())) {
			
			return false;
		}
		
//		エンコード
		String encodePassword =
				PassEncoderUtil.encode(account.getAccountPass());
		
		account.setAccountPass(encodePassword);
		
		return accountDAO.insert(account);
	}

	public boolean existsByAccountId(String accountId) {

		if (isBlank(accountId)) {
			return false;
		}

		return accountDAO.existsByAccountId(
				accountId.trim());
	}
	
	//	会員情報更新
	public boolean update(Account account) {
	
		if (account == null
				|| isBlank(account.getAccountId())
				|| isBlank(account.getAccountName())) {
			return false;
		}
	
		// 入力された場合だけパスワードをハッシュ化
		if (!isBlank(account.getAccountPass())) {
			String encodedPassword = PassEncoderUtil.encode(
					account.getAccountPass());
	
			account.setAccountPass(encodedPassword);
		} else {
			account.setAccountPass(null);
		}
	
		return accountDAO.update(account);
	}
	
//	取得
	public Account findByAccountId(String accountId) {

		if (isBlank(accountId)) {
			return null;
		}

		return accountDAO.findByAccountId(accountId);
	}

////	パスワードの変更
//	public boolean updatePassword(
//			String accountId,
//			String newPassword) {
//		if(isBlank(accountId) || isBlank(newPassword)) {
//			return false;
//		}
		
////		エンコード
//		String encodePassword =
//				PassEncoderUtil.encode(newPassword);
//		return accountDAO.updatePassword(
//				accountId,
//				encodePassword);
//	}
	
//	退会
	public boolean delete(String accountId) {
		if (isBlank(accountId)) {
			return false;
		}
		
		return accountDAO.deleteAccount(accountId);
	}
	
	private boolean isBlank(String value) {
		return value == null || value.isBlank();
	}
	
	

}
