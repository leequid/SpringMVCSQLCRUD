<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Restaurant</title>
<link rel="stylesheet" href="bootstrap.css">
<link rel="stylesheet" href="master.css">
</head>
<body>
	<div class="container-fluid">
		<div class="row">

			<div class="col-md-2">
				<a href="addRestaurant.do">Add Restaurant</a>
			</div>
			<div class="col-md-2">
				<a href="deleteRestaurant.do">Delete Restaurant!</a>
			</div>
			<div class="col-md-2">
				<a href="viewRestaurant.do">View your List!</a>
			</div>
			<div class="col-md-2">
				<a href="editRestaurant.do">Edit your List!</a>
			</div>
			<div class="col-md-2">
				<a href="addFood.do">Add food to restaurant</a>
			</div>
		</div>
	</div>
	<h2>Delete Restaurant</h2>

	<ol>
		<c:forEach items="${restaurantList}" var="i">
			<li><h2>${i.rname}</h2></li>
			<div>Restaurant Locations</div>
			<ol>
				<c:forEach items="${i.addressList}" var="a">
				<c:if test="${not empty a && a.street != null}">
					<li>${a}</li>
					<form method="post" action="deleteRestaurantLocation.do">
						<input type="hidden" name="addressId" value="${a.aid}"> <input
							type="submit" value="Delete Location" name="delete" />
					</form>
</c:if>
				</c:forEach>
			</ol>
			<form method="post" action="deleteRestaurant.do">
				<input type="hidden" name="rid" value="${i.rid}"> <input
					type="hidden" name="rname" value="${i.rname}"> <input
					type="submit" value="Delete Restaurant" name="delete" />
			</form>
			<form method="post" action="addLocation.do">
				<input type="hidden" name="rid" value="${i.rid}"> <input
					type="hidden" name="rname" value="${i.rname}"> <input
					type="submit" value="Add Location" name="add" />
				<c:if test="${not empty add && addId == i.rid}">
					<br>
					<label>Street address: <input type="text" name="street"></label>
					<br>
					<label>City: <input type="text" name="city">
					</label>
					<br>
					<label>State: <input type="text" name="state"></label>
					<br>
					<label>Zip-code:<input type="text" name="zipcode">
					</label>
					<input type="hidden" name="restaurantId" value="${i.rid}">
					<br>
					<input type="submit" name="addLocation"
						value="Submit to add location" />
				</c:if>
			</form>
			<form method="post" action="editRestaurant.do">
				<input type="hidden" name="rid" value="${i.rid}"> <input
					type="hidden" name="rname" value="${i.rname}"> <input
					type="submit" value="Edit Restaurant" name="edit" />

			</form>



		</c:forEach>
	</ol>
</body>
</html>