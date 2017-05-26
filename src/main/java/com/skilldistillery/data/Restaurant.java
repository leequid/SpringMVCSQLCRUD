package com.skilldistillery.data;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
	private String name;
	private List<Address> addressList;
	private String foodType;
	private List<Food> foodList;
	private int id;
	
	
	
	public Restaurant(int id, String name, List<Address> addressList, String foodType, List<Food> foodList) {
		super();
		this.name = name;
		this.addressList = addressList;
		this.foodType = foodType;
		this.foodList = foodList;
		this.id = id;
	}
	public Restaurant(String name, Address address) {
		super();
		this.name = name;
		addressList = new ArrayList<Address>();
		addressList.add(address);
		foodList = new ArrayList<Food>();
		
		System.out.println(address);
	}
	public Restaurant() {
		foodList = new ArrayList<Food>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Address> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Food> getFoodList() {
		return foodList;
	}
	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}
	
	
}
