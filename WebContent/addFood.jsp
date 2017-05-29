<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="bootstrap.css">
<link rel="stylesheet" href="master.css">
<title>Add food to restaurant</title>
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


	<h2>Pick a restaurant you want to add food</h2>

	<ol>
		<c:forEach items="${restaurantList}" var="i">

			<li>${i.rname}</li>
			<form method="post" action="addFood.do">
				<input type="hidden" name="rid" value="${i.rid}"><input
					type="submit" value="Select Restaurant to addFood"
					name="SelectaddFood" />
				<c:if test="${rId == i.rid}">
					<input type="text" value="TYPE FOOD NAME" name="fname" />
					<input type="text" value="TYPE FOOD DESCRIPTION" name="description" />
					<input type="text" value="TYPE FOOD PICTURE URL" name="url" />
					<input type="text" value="TYPE PRICE OF FOOD" name="price" />
					<br>
					<input type="submit" name="AddFood" value="addfood" />
				</c:if>
				<ol>
					<c:forEach items="${i.foodList}" var="f">

						<c:if test="${not empty f.fname}">

							<li><h3>${f.fname}</h3></li>
							<dd>${f.description }</dd>
							<br>
							<img src="${f.url}" alt="picture of food" height="120"
								width="160">
						</c:if>						
								<input type="text" name="fid" value="${f.fid}" />
								<input type="hidden" name="rid" value="${i.rid}" />
								<input type="submit" name="delete" value="delete" />
								
					</c:forEach>
				</ol>
			</form>

		</c:forEach>
	</ol>




</body>
</html>