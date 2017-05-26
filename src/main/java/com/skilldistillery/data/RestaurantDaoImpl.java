package com.skilldistillery.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.mysql.jdbc.Statement;

public class RestaurantDaoImpl implements RestaurantDao {
	private static String url = "jdbc:mysql://localhost:3306/restaurantdb";
	private String user = "foodie";
	private String pass = "foodie";
	private int index = 0;

	// @Autowired
	// WebApplicationContext wac;

	@Autowired
	ServletContext context;

	public RestaurantDaoImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
		}
	}

	public boolean restaurantInList(int id, List<Restaurant> restaurantList) {
		for (Restaurant restaurant : restaurantList) {
			if (restaurant.getId() == id) {
				index = restaurantList.indexOf(restaurant);
				return true;
			}
		}

		return false;

	}

	public boolean foodInList(Restaurant r, int fId) {

		for (Food food : r.getFoodList()) {
			if (food.getId() == fId) {
				return true;
			}
		}

		return false;

	}

	public boolean addressInList(Restaurant r, int aId) {
		for (Address address : r.getAddressList()) {
			if (address.getId() == aId) {
				return true;
			}
		}

		return false;

	}

	public List<Restaurant> returnDataList() {
		List<Restaurant> restaurantList = new ArrayList();
		System.out.println("inload");
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT r.id,r.name, r.food_type,a.id,a.street, a.city, a.state, a.zipcode,f.id, f.name, f.price, f.description, f.url from restaurant r left join address a on a.restaurant_id = r.id left join restaurant_has_food rf on rf.restaurant_id = r.id left join food f on f.id = rf.food_id order by r.id,a.id,f.id";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
			int restaurantId = rs.getInt(1);
			String rName = rs.getString(2);
			String rFoodType = rs.getString(3);
			int aId = rs.getInt(4);
			String address = rs.getString(5);
			String city = rs.getString(6);
			String state = rs.getString(7);
			String zipcode = rs.getString(8);
			int fId = rs.getInt(9);
			String foodName = rs.getString(10);
			double foodPrice = rs.getDouble(11);
			String foodDescription = rs.getString(12);
			String url = rs.getString(13);
			List<Food> f = new ArrayList();
			f.add(new Food(foodName, foodPrice, foodDescription, url, fId));
			List<Address> a = new ArrayList();
			Address address2 = new Address(address, city, state, zipcode, aId);

				if (restaurantInList(restaurantId, restaurantList)) {
					if (!(addressInList(restaurantList.get(index), aId))) {
						restaurantList.get(index).getAddressList().add(address2);
					}

					if (!(foodInList(restaurantList.get(index), fId))) {
						restaurantList.get(index).getFoodList()
								.add(new Food(foodName, foodPrice, foodDescription, url, fId));
					}
				} else {

					a.add(address2);
					restaurantList.add(new Restaurant(restaurantId, rName, a, rFoodType, f));
				}

			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurantList;
	}

	public String concatRestaurantFood(Restaurant r) {
		// System.out.println("asdklfjaslkf");
		// if(r.getFoodList()==null){
		// return "";
		// }
		// List<Food> fl = r.getFoodList();
		// StringBuilder sb= new StringBuilder();
		//
		//
		// for (Food food : fl) {
		// sb.append(food.getName()+d2);
		// }
		//
		// sb.deleteCharAt(sb.length()-1);
		// sb.trimToSize();
		// sb.append("|");
		//
		// for (Food food : fl) {
		// sb.append(food.getDescription()+d2);
		// }
		// sb.deleteCharAt(sb.length()-1);
		// sb.trimToSize();
		// sb.append("|");
		// for (Food food : fl) {
		// sb.append(food.getPrice()+d2);
		// }
		// sb.deleteCharAt(sb.length()-1);
		// sb.trimToSize();
		// sb.append("|");
		// for (Food food : fl) {
		// sb.append(food.getVeg()+d2);
		// }
		// sb.deleteCharAt(sb.length()-1);
		// sb.trimToSize();
		// sb.append("|");
		// for (Food food : fl) {
		// sb.append(food.getSpicy()+d2);
		// }
		// sb.deleteCharAt(sb.length()-1);
		// sb.trimToSize();
		// sb.append("|");
		// for (Food food : fl) {
		// sb.append(food.getUrl()+d2);
		// }
		// sb.deleteCharAt(sb.length()-1);
		// sb.trimToSize();
		return "";
	}

	@Override
	public void addRestaurant(String rName, String rFoodType, String address, String city, String state, String zip) {
		System.out.println(rName + rFoodType + address + city+ state + zip);
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "INSERT INTO restaurant (name,food_type) VALUES (?, ?) "; 
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
																								
			stmt.setString(1, rName);
			stmt.setString(2, rFoodType);
			stmt.executeUpdate();
			int newId = 0;
			ResultSet keys = stmt.getGeneratedKeys();
			if (keys.next()) {
				newId = keys.getInt(1);
			}
			sql = "INSERT INTO address (street, city, state, zipcode, restaurant_id) VALUES (?, ?, ?, ?, ?) "; 
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, address);
			stmt.setString(2, city);
			stmt.setString(3, state);
			stmt.setString(4, zip);
			stmt.setInt(5, newId);		
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void deleteRestaurant(String name) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "select id from restaurant where name = '?'"; 
			PreparedStatement stmt = conn.prepareStatement(sql); 
																								
			stmt.setString(1, rName);
			stmt.setString(2, rFoodType);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public void addFood(String rname, String name, String description, String url) {

	}

	@Override
	public void editRestaurant(String[] RestaurantName, String[] RestaurantAddress) {

	}


	@Override
	public List<Restaurant> getAllRestaurant() {
		// TODO Auto-generated method stub
		return null;
	}

}
