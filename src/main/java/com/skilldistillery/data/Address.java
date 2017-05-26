package com.skilldistillery.data;

public class Address {
String street;
String city;
String state;
String zipcode;
int id;


public Address(String street, String city, String state, String zipcode, int id) {
	super();
	this.street = street;
	this.city = city;
	this.state = state;
	this.zipcode = zipcode;
	this.id = id;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
	builder.append("Address: street=").append(street).append(", city=").append(city).append(", state=").append(state)
			.append(", zipcode=").append(zipcode);
	return builder.toString();
}


}
