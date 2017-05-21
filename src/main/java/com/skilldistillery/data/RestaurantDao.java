package com.skilldistillery.data;

import java.util.List;

import javax.servlet.ServletContext;

public interface RestaurantDao {
	List<Restaurant> getAllRestaurant();
	void saveFile(Restaurant restaurant);
	void addRestaurant(Restaurant R);
	void deleteRestaurant(String name);
	void editRestaurant(String[] RestaurantName,String[] RestaurantAddress);
	
}
