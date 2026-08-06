package com.example.demo.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.example.demo.model.Account;

@WebFilter(urlPatterns = {
		"/menu",
		"/products",
		"/cart",
		"/cart/*",
		"/order/*",
		"/account-edit",
		"/admin",
		"/admin/*"
})
public class NoApproved implements Filter {

	@Override
	public void doFilter(
			ServletRequest request,
			ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession(false);

		Account account = null;

		// 修正：セッションからログイン情報を取得
		if (session != null) {
			account = (Account) session.getAttribute("account");
		}

		// 修正：未ログインならログイン画面へ戻す
		if (account == null) {
			httpResponse.sendRedirect(
					httpRequest.getContextPath()
							+ "/login");
			return;
		}

		chain.doFilter(request, response);
	}
}
