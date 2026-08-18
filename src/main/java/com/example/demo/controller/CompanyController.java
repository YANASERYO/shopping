package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyController {
	
	@GetMapping("/company")
	public String showCompany() {
		return "company";
	}

}


/*
	index.jspからcompany.jspへ遷移するリンク
	<a href="${pageContext.request.contextPath}/company">
	採用情報
	</a>
*/