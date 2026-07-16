package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderInfo {

    private Integer shoppingId;
    private String shoppingUser;
    private String shippingName;
    private String shippingPostalCode;
    private String shippingAddress;
    private String shippingPhone;
    private String shippingEmail;
    private String shippingPayment;
    private LocalDateTime shoppingDate;
    private Integer shoppingTotalPrice;
    private List<ここに入力> details = new ArrayList<>();

    public Integer getShoppingId() {return shoppingId;}
    public void setShoppingId(Integer shoppingId) {this.shoppingId = shoppingId;}

    public String getShoppingUser() {return shoppingUser;}
    public void setShoppingUser(String shoppingUser) {this.shoppingUser = shoppingUser;}

    public String getShippingName() {return shippingName;}
    public void setShippingName(String shippingName) {this.shippingName = shippingName;}

    public String getShippingPostalCode() {return shippingPostalCode;}
    public void setShippingPostalCode(String shippingPostalCode) {this.shippingPostalCode = shippingPostalCode;}

    public String getShippingAddress() {return shippingAddress;}
    public void setShippingAddress(String shippingAddress) {this.shippingAddress = shippingAddress;}

    public String getShippingPhone() {return shippingPhone;}
    public void setShippingPhone(String shippingPhone) {this.shippingPhone = shippingPhone;}

    public String getShippingEmail() {return shippingEmail;}
    public void setShippingEmail(String shippingEmail) {this.shippingEmail = shippingEmail;}

    public String getShippingPayment() {return shippingPayment;}
    public void setShippingPayment(String shippingPayment) {this.shippingPayment = shippingPayment;}

    public LocalDateTime getShoppingDate() {return shoppingDate;}
    public void setShoppingDate(LocalDateTime shoppingDate) {this.shoppingDate = shoppingDate;}

    public Integer getShoppingTotalPrice() {return shoppingTotalPrice;}
    public void setShoppingTotalPrice(Integer shoppingTotalPrice) {this.shoppingTotalPrice = shoppingTotalPrice;}

    public List<ここに入力> getDetails() {return details;}
    public void setDetails(List<ここに入力> details) {this.details = details;}
}