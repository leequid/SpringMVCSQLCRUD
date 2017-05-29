package com.skilldistillery.data;

import java.util.ArrayList;
import java.util.List;

public class Address {
String street;
String city;
String state;
String zipcode;
int aid;
int restaurantId;




public int getAid() {
	return aid;
}


public void setAid(int aid) {
	this.aid = aid;
}


public Address() {
	street = null;
	city = null;
	state = null;
	zipcode = null;
	aid = 0;
	restaurantId = 0;
}


public Address(String street, String city, String state, String zipcode, int id, int restaurantId) {
	super();
	this.street = street;
	this.city = city;
	this.state = state;
	this.zipcode = zipcode;
	this.aid = id;
	this.restaurantId = restaurantId;
}
public int getRestaurantId() {
	return restaurantId;
}
public void setRestaurantId(int restaurantId) {
	this.restaurantId = restaurantId;
}
public Address(String street, String city, String state, String zipcode, int id) {
	super();
	this.street = street;
	this.city = city;
	this.state = state;
	this.zipcode = zipcode;
	this.aid = id;
}

public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getZipcode() {
	return zipcode;
}
public void setZipcode(String zipcode) {
	this.zipcode = zipcode;
}
public Address(String street, String city, String state, String zipcode) {
	super();
	this.street = street;
	this.city = city;
	this.state = state;
	this.zipcode = zipcode;
}


@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Address [street=").append(street).append(", city=").append(city).append(", state=").append(state)
			.append(", zipcode=").append(zipcode).append(", aid=").append(aid).append(", restaurantId=")
			.append(restaurantId).append("]");
	return builder.toString();
}
public List<Address> parceAddress(Address a, int [] aid ,int rid){
	List<Address> address = new ArrayList<>();
	String [] street = a.getStreet().split(",");
	String [] city = a.getCity().split(",");
	String [] state = a.getState().split(",");
	String [] zipcode = a.getZipcode().split(",");
	for(int i = 0; i < street.length; i++){
		address.add(new Address(street[i],city[i],state[i],zipcode[i],aid[i],rid));
	}
	
	return address;
}


}
