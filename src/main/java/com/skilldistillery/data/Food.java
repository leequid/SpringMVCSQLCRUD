package com.skilldistillery.data;

public class Food {
	String name;
	double price;
	String veg;
	String description;
	String spicy;
	String Url;
	
	public Food(String name,String description,String url){
		this.name = name;
		this.description = description;
		Url = url;
	}
	public Food(String name,String description, double price, String veg, String spicy, String url) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.veg = veg;
		this.spicy = spicy;
		Url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getVeg() {
		return veg;
	}
	public void setVeg(String veg) {
		this.veg = veg;
	}
	public String getSpicy() {
		return spicy;
	}
	public void setSpicy(String spicy) {
		this.spicy = spicy;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	

}
