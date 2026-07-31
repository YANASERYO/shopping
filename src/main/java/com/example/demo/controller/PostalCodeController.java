package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PostalCode;
import com.example.demo.service.PostalCodeService;

@RestController
public class PostalCodeController {

	private final PostalCodeService postalCodeService;

	public PostalCodeController(
			PostalCodeService postalCodeService) {

		this.postalCodeService = postalCodeService;
	}

	@GetMapping("/api/postal-code")
	public Map<String, Object> searchAddress(
			@RequestParam("postalCode") String postalCode) {

		Map<String, Object> response = new HashMap<>();

		PostalCode result =
				postalCodeService.findByPostalCode(postalCode);

		if (result == null) {
			response.put("found", false);
			response.put(
					"message",
					"該当する住所が見つかりませんでした。");

			return response;
		}

		String address =
				valueOrEmpty(result.getPrefecture())
				+ valueOrEmpty(result.getCity())
				+ valueOrEmpty(result.getTown());

		response.put("found", true);
		response.put("address", address);

		return response;
	}

	private String valueOrEmpty(String value) {
		return value == null ?  "" : value;
	}
}