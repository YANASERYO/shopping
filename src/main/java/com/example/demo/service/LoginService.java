package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.AccountDAO;
import com.example.demo.model.Account;
import com.example.demo.util.PassEncoderUtil;


@Service
public class LoginService {

    private final AccountDAO accountDAO;

    public LoginService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account login(
            String accountId,
            String accountPass) {

        if (accountId == null
                || accountId.isBlank()
                || accountPass == null
                || accountPass.isBlank()) {

            return null;
        }

        Account account =
                accountDAO.findById(accountId);

        if (account == null) {
            return null;
        }

        if (!PassEncoderUtil.matches(
                accountPass,
                account.getAccountPass())) {

            return null;
        }

        return account;
    }
}