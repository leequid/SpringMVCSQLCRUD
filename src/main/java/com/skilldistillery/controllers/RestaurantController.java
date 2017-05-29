package com.skilldistillery.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.data.Address;
import com.skilldistillery.data.Food;
import com.skilldistillery.data.Restaurant;
import com.skilldistillery.data.RestaurantDao;

@Controller
@SessionAttributes({ "Restaurant" })
public class RestaurantController {

	@Autowired
	RestaurantDao dao;

	@ModelAttribute("Restaurant")
	Restaurant newRestaurant() {
		return new Restaurant();
	}

	@RequestMapping(path = "addRestaurant.do", method = RequestMethod.POST)
	public ModelAndView addRestaurant(@RequestParam("rname") String name,@RequestParam("foodType") String foodType,
	@RequestParam("street") String address,@RequestParam("city") String city,@RequestParam("zipcode") String zipcode,@RequestParam("state") String state) {
		dao.addRestaurant(name, foodType, address, city, state, zipcode);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("restaurantList", dao.getAllRestaurant());
		mv.setViewName("redirect:viewRestaurant.do");
		return mv;
	}
	@RequestMapping(path = "addRestaurant.do", method = RequestMethod.GET)
	public ModelAndView viewaddRestaurant() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addRestaurant.jsp");
		return mv;
	}

	@RequestMapping(path = "deleteRestaurant.do")
	public ModelAndView loadDeleteRestaurant() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("restaurantList", dao.returnDataList());
		mv.setViewName("deleteRestaurant.jsp");
		return mv;

	}
	
	@RequestMapping(path = "deleteRestaurant.do", method = RequestMethod.POST)
//	public ModelAndView DeleteRestaurant(@RequestParam("restaurant") String name) {
	public ModelAndView DeleteRestaurant(Restaurant restaurant) {
		System.out.println(restaurant);
		System.out.println("delete restaurant");
		ModelAndView mv = new ModelAndView();
//		dao.deleteRestaurant(name);
		dao.deleteRestaurant(restaurant);
		mv.addObject("restaurantList", dao.returnDataList());
		mv.setViewName("deleteRestaurant.jsp");
		return mv;

	}

	@RequestMapping(path = "editRestaurant.do")
	public ModelAndView editRestaurantLoad() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("restaurantList", dao.getAllRestaurant());
		mv.setViewName("editRestaurant.jsp");
		return mv;
	}

	@RequestMapping(path = "editRestaurant.do", method = RequestMethod.POST)
	public ModelAndView editRestaurant(Restaurant r) {
		System.out.println(r);
		ModelAndView mv = new ModelAndView();
		mv.addObject("restaurantList", dao.getAllRestaurant());
		mv.setViewName("redirect:viewRestaurant.do");
		
		return mv;
	}



	@RequestMapping(path = "addFood.do")
	public ModelAndView addFood(Restaurant r, Food f) {
		System.out.println(f);
		ModelAndView mv = new ModelAndView();
		mv.addObject("restaurantList", dao.returnDataList());
		mv.addObject("rId", r.getRid());
		mv.setViewName("addFood.jsp");
		return mv;

	}
	@RequestMapping(path = "addFood.do", method = RequestMethod.POST)
	public ModelAndView addFoodToRestaurant(@RequestParam(value = "rid", required=false) int id ,
			@RequestParam(value = "AddFood", required=false) String add ,Food f) {
		System.out.println(f);
		ModelAndView mv = new ModelAndView();
		mv.addObject("restaurantList", dao.returnDataList());
		mv.addObject("rId", id);
		dao.addFood(id, f);
		
		if (add != null){
			mv.setViewName("redirect:viewRestaurant.do");
			return mv;
		}
		mv.setViewName("addFood.jsp");
		return mv;
		
	}
	@RequestMapping(path = "deleteFood.do", params="delete", method = RequestMethod.POST)
	public ModelAndView deleteFood(@RequestParam ("fid") int foodId, @RequestParam ("rid") int restaurantId ) {
		ModelAndView mv = new ModelAndView();
		dao.deleteFood(foodId, restaurantId);
		mv.addObject("restaurantList", dao.returnDataList());
			mv.setViewName("addFood.jsp");
			return mv;

		
	}

	@RequestMapping(path = "viewRestaurant.do", method = RequestMethod.GET)
	public ModelAndView view() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("restaurantList", dao.returnDataList());
		mv.setViewName("viewRestaurant.jsp");
		return mv;
	}
	
	@RequestMapping(path = "start.do", method = RequestMethod.GET)
	public ModelAndView start() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("restaurantList", dao.getAllRestaurant());
		mv.setViewName("index.html");
		return mv;
	}
	
	@RequestMapping(path = "editRestaurant.do", params="edit", method = RequestMethod.POST)
	public ModelAndView addLocation(@RequestParam("rid") int id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("restaurant", dao.getRestaurantById(id));
		mv.addObject("address", dao.getAddressByRestaurantId(id));
		mv.addObject("food", dao.getFoodByRestaurantId(id));
		System.out.println(dao.getFoodByRestaurantId(id).size());
		
		mv.setViewName("editRestaurant.jsp");
		return mv;
	}
	@RequestMapping(path = "editRestaurant.do", params="editRestaurant", method = RequestMethod.POST)
	public ModelAndView addLocation(Restaurant r, Address a, @RequestParam("aid") int [] aid) {
		ModelAndView mv = new ModelAndView();
		dao.editRestaurant(r);
		dao.editAddress(a.parceAddress(a,aid,r.getRid()));

		mv.addObject("restaurantList", dao.returnDataList());
		mv.setViewName("viewRestaurant.jsp");
		return mv;
	}
	
	@RequestMapping(path = "addLocation.do", params="add", method = RequestMethod.POST)
	public ModelAndView actuallyAddLocation(@RequestParam("add") String s, Restaurant r,Address a,
			@RequestParam(value = "street", required=false) String str,@RequestParam(value = "city", required=false) String c
			,@RequestParam(value = "state", required=false) String st,@RequestParam(value = "zipCode", required=false) String z) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("restaurantList", dao.returnDataList());
		mv.addObject("addId", r.getRid());
		mv.addObject("add", s);
		mv.setViewName("deleteRestaurant.jsp");
		return mv;
	}
	
	@RequestMapping(path = "addLocation.do", params="addLocation", method = RequestMethod.POST)
	public ModelAndView actuallyAddLocation(Restaurant r,Address a) {	
		ModelAndView mv = new ModelAndView();
		dao.addLocation(r,a);
		mv.setViewName("redirect:viewRestaurant.do");
		return mv;
	}
	@RequestMapping(path = "deleteRestaurantLocation.do", params="delete", method = RequestMethod.POST)
	public ModelAndView actuallyAddLocation(@RequestParam("addressId") int a) {	
		ModelAndView mv = new ModelAndView();
		dao.deleteRestaurantLocation(a);
		mv.addObject("restaurantList", dao.returnDataList());
		mv.setViewName("deleteRestaurant.jsp");
		return mv;
	}
	
	
}
