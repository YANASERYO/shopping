package com.example.demo.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Lombokを使用するのはOKですが
//adminがis_admin等で勝手に読むようになるので覚えてください

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	private String accountId;
	private String accountName;
	private String accountPass;
	private String postalCode;
	private String accountAddress;
	private String accountPhone;
	private LocalDate birthday;
	private String email;
	private String payment;
	private boolean admin;
}
