package com.skilldistillery.data;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

public class RestaurantDaoImpl implements RestaurantDao {
	List<Restaurant> restaurantList;
	
	private static final String d = "|";
	private static final String d2 = "\\";

	// @Autowired
	// WebApplicationContext wac;

	@Autowired
	ServletContext context;

	public RestaurantDaoImpl() {
		restaurantList = new ArrayList<Restaurant>();
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		// TODO Auto-generated method stub
		return restaurantList;
	}

	@PostConstruct
	public void loadFile() {
		InputStream is = context.getResourceAsStream("/WEB-INF/RestaurantList.txt");

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

			String record; // Read and discard header line

			while ((record = reader.readLine()) != null) {
				Restaurant temp = new Restaurant();
				System.out.println("1");
				String[] col = record.split("\\|");
				System.out.println("2");
				String name = col[0];
				String Address = col[1];
				System.out.println(col[2]);
				String[] foodName = col[2].split("\\\\");
				System.out.println("3");
				String[] descriptionFood = col[3].split("\\\\");
				String[] price = col[4].split("\\\\");
				String[] vegiterian = col[5].split("\\\\");
				String[] spicy = col[6].split("\\\\");
				String[] FoodUrl = col[7].split("\\\\");
				temp.setName(name);
				temp.setAddress(Address);
				List<Food> foodList = new ArrayList<Food>();

				for (int i = 0; i < foodName.length; i++) {
					foodList.add(new Food(foodName[i], descriptionFood[i], Double.parseDouble(price[i]), vegiterian[i],
							spicy[i], FoodUrl[i]));
				}

				temp.setFoodList(foodList);
				restaurantList.add(temp);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public String concatRestaurantFood(Restaurant r){
		if(r.getFoodList()==null){
			return "";
		}
		List<Food> fl = r.getFoodList();
		StringBuilder sb= new StringBuilder();
		
		
		for (Food food : fl) {
			sb.append(food.getName()+d2);
		}
		
		sb.deleteCharAt(sb.length()-1);
		sb.trimToSize();
		sb.append("|");
		
		for (Food food : fl) {
			sb.append(food.getDescription()+d2);
		}
		sb.deleteCharAt(sb.length()-1);
		sb.trimToSize();
		sb.append("|");
		for (Food food : fl) {
			sb.append(food.getPrice()+d2);
		}
		sb.deleteCharAt(sb.length()-1);
		sb.trimToSize();
		sb.append("|");
		for (Food food : fl) {
			sb.append(food.getVeg()+d2);
		}
		sb.deleteCharAt(sb.length()-1);
		sb.trimToSize();
		sb.append("|");
		for (Food food : fl) {
			sb.append(food.getSpicy()+d2);
		}
		sb.deleteCharAt(sb.length()-1);
		sb.trimToSize();
		sb.append("|");
		for (Food food : fl) {
			sb.append(food.getUrl()+d2);
		}
		sb.deleteCharAt(sb.length()-1);
		sb.trimToSize();
		return sb.toString();
	}

	@Override
	public void saveFile(Restaurant restaurant) {
	
		String orderFile = "WEB-INF/orders.csv";
		String filePath = context.getRealPath(orderFile);
		System.out.println("DAO: " + filePath);
		
		try {
			PrintWriter out = new PrintWriter(new FileWriter(filePath));
			for (Restaurant r : restaurantList) {
				out.println(r.getName()+d+r.getAddress()+d+concatRestaurantFood(restaurant));

			}
			out.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public void addRestaurant(Restaurant R) {
		restaurantList.add(R);

	}
	@Override
	public void deleteRestaurant(String name) {
		for(int i=0; i < restaurantList.size(); i++){
			Restaurant r = restaurantList.get(i);
			if(r.getName().equals(name)){
				restaurantList.remove(i);
			}
		}
		
	}

	@Override
	public void editRestaurant(String[] RestaurantName, String[] RestaurantAddress) {
		
		for(int i =0;i<restaurantList.size();i++){
			restaurantList.get(i).setName(RestaurantName[i]);
			restaurantList.get(i).setAddress(RestaurantAddress[i]);
			
		}
		
	}


}
