package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.OrderInfoDAO;
import com.example.demo.model.Account;
import com.example.demo.model.OrderInfo;

@Controller
public class OrderInfoController {
	private final OrderInfoDAO orderInfoDAO;
    
	public OrderInfoController(OrderInfoDAO orderInfoDAO) {
		this.orderInfoDAO = orderInfoDAO;
		}
	
	@GetMapping("/orders")
	public String showOrderHistory(HttpSession session, Model model) {
		Account account = (Account) session.getAttribute("account");
		
		// 未ログインの場合
		if (account == null) {
			return "redirect:/login";
			}
		
		List<OrderInfo> orderInfoList = orderInfoDAO.findByAccountId(account.getAccountId());
		
		model.addAttribute("orderInfoList",orderInfoList);
		
		return "order-info";
	}
}