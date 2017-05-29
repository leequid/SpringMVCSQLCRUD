<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="bootstrap.css">
<link rel="stylesheet" href="master.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Restaurant</title>
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
	<h1>View all the restaurant</h1>

	<ol>
		<c:forEach items="${restaurantList}" var="i">
			<li><h2>${i.rname}</h2></li>
			<div>Restaurant Locations</div>
			<ol>
				<c:forEach items="${i.addressList}" var="a">
					<li>${a}</li>
				</c:forEach>
			</ol>

			<ol>
				<c:forEach items="${i.foodList}" var="f">

					<c:if test="${not empty f.fname}">

						<li><h3>${f.fname}</h3></li>
						<dd>${f.description }</dd>
						<br>
						<img src="${f.url}" alt="picture of food" height="120" width="160">
					</c:if>
				</c:forEach>
			</ol>

		</c:forEach>
	</ol>

</body>
</html>