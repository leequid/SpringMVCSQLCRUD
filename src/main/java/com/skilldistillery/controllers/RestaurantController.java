package com.skilldistillery.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


import com.skilldistillery.data.Restaurant;
import com.skilldistillery.data.RestaurantDao;

@Controller
@SessionAttributes({"Restaurant"})
public class RestaurantController {

	@Autowired
	RestaurantDao dao;
	
	@ModelAttribute("Restaurant")
	Restaurant newRestaurant(){
		return new Restaurant();
	}
	
	@RequestMapping(path="addRestaurant.do",params={"restaurantName","restaurantAddress"}, method=RequestMethod.POST)
	public ModelAndView addRestaurant(@RequestParam("restaurantName") String name,@RequestParam("restaurantAddress") String address){

		dao.addRestaurant(new Restaurant(name,address));
		ModelAndView mv = new ModelAndView();
		mv.addObject("restaurantList", dao.getAllRestaurant());
		mv.setViewName("redirect:viewRestaurant.do");
		dao.saveFile(dao.getAllRestaurant().get((dao.getAllRestaurant().size())-1));
		return mv;
	}
	
	@RequestMapping(path="deleteRestaurant.do")
	public ModelAndView loadDeleteRestaurant(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("restaurantList", dao.getAllRestaurant());
		mv.setViewName("DeleteRestaurant.jsp");
		return mv;
		
		
	}
	
	@RequestMapping(path="editRestaurant.do")
	public ModelAndView editRestaurantLoad(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("restaurantList", dao.getAllRestaurant());
		mv.setViewName("editRestaurant.jsp");
		return mv;
	}
	@RequestMapping(path="editRestaurant.do", method=RequestMethod.POST)
	public ModelAndView editRestaurant(@RequestParam("restaurantName") String[] name,@RequestParam("restaurantAddress") String[] address){
		ModelAndView mv = new ModelAndView();
		dao.editRestaurant(name, address);
		mv.addObject("restaurantList", dao.getAllRestaurant());
		mv.setViewName("redirect:viewRestaurant.do");
		return mv;
	}
	
	
	
	@RequestMapping(path="deleteRestaurant.do", method=RequestMethod.POST)
	public ModelAndView DeleteRestaurant(@RequestParam("restaurant") String name){
		ModelAndView mv = new ModelAndView();
		dao.deleteRestaurant(name);	
		mv.addObject("restaurantList", dao.getAllRestaurant());
		mv.setViewName("DeleteRestaurant.jsp");
		return mv;
		
		
	}
	
	@RequestMapping(path="viewRestaurant.do")
	public ModelAndView view(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("restaurantList", dao.getAllRestaurant());
		mv.setViewName("ViewRestaurant.jsp");
		return mv;
	}
}
