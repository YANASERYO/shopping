package com.example.demo.model;


public class PostalCode {
	private String postalCode;
	private String prefecture;
	private String city;
	private String town;
	
	public PostalCode() {}
	
	public PostalCode(String postalCode,String prefecture,String city,String town) {
		this.postalCode = postalCode;
		this.prefecture = prefecture;
		this.city = city;
		this.town = town;
	}
	
	public String getPostalCode() {return postalCode;}
	public void setPostalCode(String postalCode) {this.postalCode = postalCode;}
	
	public String getPrefecture() {return prefecture;}
	public void setPrefecture(String prefecture) {this.prefecture= prefecture;}
	
	public String getCity() {return city;}
	public void setCity(String city) {this.city = city;}
	
	public String getTown() {return town;}
	public void setTown(String town) {this.town = town;}
	
	public String getValueOrEmpty() {return valueOrEmpty(prefecture) + valueOrEmpty(city) + valueOrEmpty(town);}
	private String valueOrEmpty(String value) {return value == null ?"" : value;}
}
