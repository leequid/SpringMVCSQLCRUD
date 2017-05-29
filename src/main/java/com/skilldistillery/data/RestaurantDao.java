package com.skilldistillery.data;

import java.util.List;

import javax.servlet.ServletContext;

public interface RestaurantDao {
	List<Restaurant> getAllRestaurant();
	void addRestaurant(String rName, String rFoodType, String address, String city, String state, String zip);
	void deleteRestaurant(Restaurant r);
	void editRestaurant(Restaurant r);
	void addFood(String rname, String name, String description, String url);
	List<Restaurant> returnDataList();
	void addLocation(Restaurant r,Address a);
	void deleteRestaurantLocation(int a);
	List<Food>  getFoodByRestaurantId(int id);
	List<Address> getAddressByRestaurantId(int id);
	Restaurant getRestaurantById(int id);
	void addFood(int id, Food f);
	void editAddress(List<Address> a);
	void deleteFood(int foodId, int restaurantId);
}  
