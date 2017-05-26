package com.skilldistillery.data;

import java.util.List;

import javax.servlet.ServletContext;

public interface RestaurantDao {
	List<Restaurant> getAllRestaurant();
	void addRestaurant(String rName, String rFoodType, String address, String city, String state, String zip);
	void deleteRestaurant(String name);
	void editRestaurant(String[] RestaurantName,String[] RestaurantAddress);
	void addFood(String rname, String name, String description, String url);
	List<Restaurant> returnDataList();
	
}
