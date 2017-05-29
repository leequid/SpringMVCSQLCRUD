package com.skilldistillery.data;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
	private String rname;
	private List<Address> addressList;
	private String foodType;
	private List<Food> foodList;
	private int rid;
	
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Restaurant [name=").append(rname).append(", addressList=").append(addressList)
				.append(", foodType=").append(foodType).append(", foodList=").append(foodList).append(", id=")
				.append(rid).append("]");
		return builder.toString();
	}
	public Restaurant(String name, String foodType, int id) {
		super();
		this.rname = name;
		this.foodType = foodType;
		this.rid = id;
	}
	public Restaurant(int id, String name, List<Address> addressList, String foodType, List<Food> foodList) {
		super();
		this.rname = name;
		this.addressList = addressList;
		this.foodType = foodType;
		this.foodList = foodList;
		this.rid = id;
	}
	public Restaurant(String name, Address address) {
		super();
		this.rname = name;
		addressList = new ArrayList<Address>();
		addressList.add(address);
		foodList = new ArrayList<Food>();
		
		System.out.println(address);
	}
	public Restaurant() {
		foodList = new ArrayList<Food>();
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

	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public List<Food> getFoodList() {
		return foodList;
	}
	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}
	
	
}
