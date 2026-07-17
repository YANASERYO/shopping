package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Account {

	private int accountId;
	private String accountName;
	private String accountPass;
	private int postalCode;
	private String accountAddress;
	private String accountPhone;
	private String birthday;
	private String email;
	private String payment;
	private boolean admin;
	

}
