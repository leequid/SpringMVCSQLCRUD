package com.skilldistillery.data;

public class Food {
	String name;
	double price;
	String description;
	String Url;
	int id;
	
	
	public Food(String name, double price, String description, String url, int id) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		Url = url;
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Food(String name,String description,String url){
		this.name = name;
		this.description = description;
		Url = url;
	}
	public Food(String name,String description, double price, String url) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
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
