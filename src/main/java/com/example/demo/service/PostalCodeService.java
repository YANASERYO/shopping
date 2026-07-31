package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.PostalCodeDAO;
import com.example.demo.model.PostalCode;

@Service
public class PostalCodeService {
	
	private final PostalCodeDAO postalCodeDAO;
	
	public PostalCodeService(PostalCodeDAO postalCodeDAO) {
		this.postalCodeDAO = postalCodeDAO;
	}
	
	public PostalCode findByPostalCode(String postalCode) {
		
		if (postalCode == null || postalCode.isBlank()) {
			return null;
		}
		
		String normalizedPostalCode = postalCode
				.replace("-", "")
				.replace("ー", "")
				.replace("－", "")
				.trim();
		
		if (!normalizedPostalCode.matches("\\d{7}")) {
			return null;
		}
		
		return postalCodeDAO.findByPostalCode(
				normalizedPostalCode);
	}
}