package com.skilldistillery.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			if (restaurant.getRid() == id) {
				index = restaurantList.indexOf(restaurant);
				return true;
			}
		}

		return false;

	}

	public boolean foodInList(Restaurant r, int fId) {

		for (Food food : r.getFoodList()) {
			if (food.getFid() == fId) {
				return true;
			}
		}

		return false;

	}

	public boolean addressInList(Restaurant r, int aId) {
		for (Address address : r.getAddressList()) {
			if (address.getAid() == aId) {
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

		return "";
	}

	@Override
	public void addRestaurant(String rName, String rFoodType, String address, String city, String state, String zip) {
		System.out.println(rName + rFoodType + address + city + state + zip);
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
			System.out.println(address);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, address);
			stmt.setString(2, city);
			stmt.setString(3, state);
			stmt.setString(4, zip);
			stmt.setInt(5, newId);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	// public void deleteRestaurant(String name) {
	public void deleteRestaurant(Restaurant r) {
		// need a method to delete restaurant_has_food records for restaurant
		// id.

		deleteAddress(r);
		deleteRestaurantHasFood(r);
		;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "delete from restaurant where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, r.getRid());

			stmt.executeUpdate();
			// ResultSet rs = stmt.executeQuery();
			// int restaurantId = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// public void deleteFood(Restaurant r) {
	// try {
	// Connection conn = DriverManager.getConnection(url, user, pass);
	// String sql = "select food_id from restaurant_has_food where restaurant_id
	// = ?";
	// PreparedStatement stmt = conn.prepareStatement(sql);
	// stmt.setInt(1, r.getId());
	// ResultSet rs = stmt.executeQuery();
	//
	// while (rs.next()) {
	// int restaurantId = rs.getInt(1);
	// sql = "delete from restaurant where id = ?";
	// stmt = conn.prepareStatement(sql);
	// stmt.setInt(1, restaurantId);
	// stmt.executeUpdate();
	//
	// }
	// //
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }

	public void deleteAddress(Restaurant r) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "delete from address where restaurant_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, r.getRid());

			stmt.executeUpdate();
			// ResultSet rs = stmt.executeQuery();
			// int restaurantId = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteRestaurantHasFood(Restaurant r) {
		// need a method to delete restaurant_has_food records for restaurant
		// id.
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "delete from restaurant_has_food where restaurant_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, r.getRid());
			stmt.executeUpdate();
			// ResultSet rs = stmt.executeQuery();
			// int restaurantId = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addFood(String rname, String name, String description, String url) {

	}



	@Override
	public List<Restaurant> getAllRestaurant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLocation(Restaurant r, Address a) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "INSERT INTO address (street, city, state, zipcode, restaurant_id) VALUES (?, ?, ?, ?, ?) ";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, a.getStreet());
			stmt.setString(2, a.getCity());
			stmt.setString(3, a.getState());
			stmt.setString(4, a.getZipcode());
			stmt.setInt(5, r.getRid());
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteRestaurantLocation(int a) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "delete from address where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, a);
			stmt.executeUpdate();
			// ResultSet rs = stmt.executeQuery();
			// int restaurantId = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Food> getFoodByRestaurantId(int id) {
		List<Food> a = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "select f.id, f.name, f.price, f.description, f.url from restaurant r join restaurant_has_food rhf on r.id = rhf.restaurant_id join food f on rhf.food_id = f.id where r.id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int foodId = rs.getInt(1);
				String name = rs.getString(2);
				Double price = rs.getDouble(3);
				String description = rs.getString(4);
				String url = rs.getString(5);
				a.add(new Food(name, price, description, url, foodId));

			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	@Override
	public List<Address> getAddressByRestaurantId(int id) {
		List<Address> a = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "select a.id, a.street, a.city, a.state, a.zipcode from restaurant r join address a on r.id=a.restaurant_id where r.id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int addressId = rs.getInt(1);
				String street = rs.getString(2);
				String city = rs.getString(3);
				String state = rs.getString(4);
				String zipcode = rs.getString(5);
				int restaurantId = id;
				a.add(new Address(street, city, state, zipcode, addressId, restaurantId));

			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return a;
	}

	@Override
	public Restaurant getRestaurantById(int id) {
		Restaurant a;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT id, name, food_type from restaurant where id =?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int restaurantId = rs.getInt(1);
				String name = rs.getString(2);
				String food_type = rs.getString(3);
				a = new Restaurant(name, food_type, restaurantId);
				return a;
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void addFood(int id, Food f) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "INSERT INTO food (name,description,url,price) VALUES (?, ?,?,?) ";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, f.getFname());
			stmt.setString(2, f.getDescription());
			stmt.setString(3, f.getUrl());
			stmt.setDouble(4, f.getPrice());
			stmt.executeUpdate();
			int newId = 0;
			ResultSet keys = stmt.getGeneratedKeys();
			if (keys.next()) {
				newId = keys.getInt(1);
			}
			sql = "INSERT INTO restaurant_has_food (restaurant_id, food_id) VALUES (?,?) ";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setInt(2, newId);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void editRestaurant(Restaurant r) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "UPDATE restaurant SET name = ?, food_type = ? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, r.getRname());
			stmt.setString(2, r.getFoodType());
			stmt.setInt(3, r.getRid());
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public void editAddress(List<Address> a) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			for (Address address : a) {
				String sql = "UPDATE address SET street = ?, city = ?,state = ?,zipcode = ?,restaurant_id = ? WHERE id = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);

				stmt.setString(1, address.getStreet());
				stmt.setString(2, address.getCity());
				stmt.setString(3, address.getState());
				stmt.setString(4, address.getZipcode());
				stmt.setInt(5, address.getRestaurantId());
				stmt.setInt(6, address.getAid());
				stmt.executeUpdate();
				stmt.close();
			
			}
			
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteFood(int foodId, int restaurantId) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "delete from restaurant_has_food where restaurant_id = ? and food_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, restaurantId);
			stmt.setInt(2, foodId);
			stmt.executeUpdate();
			// ResultSet rs = stmt.executeQuery();
			// int restaurantId = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
