package com.skilldistillery.data;

import java.util.List;

public class Restaurant {
	private String name;
	private String address;
	private List<Food> foodList;
	
	
	public Restaurant(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
	public Restaurant() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Food> getFoodList() {
		return foodList;
	}
	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}
	
	
}
