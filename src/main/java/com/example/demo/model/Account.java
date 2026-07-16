package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Account {

    private int account_id;
    private String account_name;
    private String account_pass;
    private int postal_code;
    private String account_address;
    private String account_phone;
    private String birthday;
    private String email;
    private String payment;
    private boolean admin;

}
