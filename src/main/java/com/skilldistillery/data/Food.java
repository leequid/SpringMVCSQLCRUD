package com.skilldistillery.data;

public class Food {
	String fname;
	double price;
	String description;
	String Url;
	int fid;
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Food [name=").append(fname).append(", price=").append(price).append(", description=")
				.append(description).append(", Url=").append(Url).append(", id=").append(fid).append("]");
		return builder.toString();
	}
	public Food() {
		super();
	}
	public Food(String name, double price, String description, String url, int id) {
		super();
		this.fname = name;
		this.price = price;
		this.description = description;
		Url = url;
		this.fid = id;
	}

	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public Food(String name,String description,String url){
		this.fname = name;
		this.description = description;
		Url = url;
	}
	public Food(String name,String description, double price, String url) {
		super();
		this.fname = name;
		this.price = price;
		this.description = description;
		Url = url;
	}

	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	

}
