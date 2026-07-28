//package com.example.demo.filter;
//
//import java.io.IOException;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import com.example.demo.model.Account;
//
//@WebFilter(urlPatterns = {
//		"/Menu",
//		"/admin",
//		"/Product-list",
//		"/Cart",
////		ここにURLを記載
//})
//public class NoApproved implements Filter {
//	
//	@Override
//	public void doFilter(
//			ServletRequest request,
//			ServletResponse response,
//			FilterChain chain)
//	throws IOException,ServletException{
//		HttpServletRequest httpRequest =
//				(HttpServletRequest) request;
//		
//		HttpServletResponse httpResponse =
//				(HttpServletResponse) response;
//		
//		HttpSession session =
//				httpRequest.getSession(false);
//		
//		Account loginAccount = null;
//		
//		if(loginAccount == null) {
//			httpResponse.sendRedirect(
//					httpRequest.getContextPath() + "/login");
//			
//			return;
//		}
//		
//		chain.doFilter(request,response);
//	}
//	
//	
//
//}
