package com.skilldistillery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.data.Address;
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

	@RequestMapping(path = "addRestaurant.do", params = { "restaurantName","restaurantFoodType",
			"rAddress", "rCity","rZipcode","rState" }, method = RequestMethod.POST)
	public ModelAndView addRestaurant(@RequestParam("restaurantName") String name,@RequestParam("restaurantFoodType") String foodType,
	@RequestParam("rAddress") String address,@RequestParam("rCity") String city,@RequestParam("rZipcode") String zipcode,@RequestParam("rState") String state) {
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
	public ModelAndView DeleteRestaurant(@RequestParam("restaurant") String name) {
		ModelAndView mv = new ModelAndView();
		System.out.println(name);
//		dao.deleteRestaurant(name);
		dao.deleteRestaurant(name);
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
	public ModelAndView editRestaurant(@RequestParam("restaurantName") String[] name,
			@RequestParam("restaurantAddress") String[] address) {
		ModelAndView mv = new ModelAndView();
		dao.editRestaurant(name, address);
		mv.addObject("restaurantList", dao.getAllRestaurant());
		mv.setViewName("redirect:viewRestaurant.do");
		
		return mv;
	}



	@RequestMapping(path = "addFood.do")
	public ModelAndView addFood(@RequestParam(value = "restaurant", required = false) String name,
			@RequestParam(value = "foodName", required = false) String fn,
			@RequestParam(value = "foodDescription", required = false) String fd,
			@RequestParam(value = "foodUrl", required = false) String fu,
			@RequestParam(value = "AddFood", required = false) String af) {
		ModelAndView mv = new ModelAndView();
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
}
