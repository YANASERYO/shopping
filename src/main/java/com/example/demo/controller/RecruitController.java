package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecruitController {

	// 採用情報画面を表示
	@GetMapping("/recruit")
	public String showRecruit() {
		return "recruit";
	}

	/*
		index.jspからrecruit.jspへ遷移するリンク
		<a href="${pageContext.request.contextPath}/recruit">
		採用情報
		</a>
	 */
}