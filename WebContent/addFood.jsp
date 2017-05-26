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

 <div class="col-md-2"><a href="addRestaurant.do">Add Restaurant</a></div>
<div class="col-md-2"><a href="deleteRestaurant.do">Delete Restaurant!</a></div>
<div class="col-md-2"><a href="viewRestaurant.do">View your List!</a></div>
<div class="col-md-2"><a href="editRestaurant.do">Edit your List!</a></div>
<div class="col-md-2"><a href="addFood.do">Add food to restaurant</a></div>
</div>
</div>

	
	<h2>Pick a restaurant you want to add food</h2>

<ol>
		<c:forEach items="${restaurantList}" var="i">
			
			<li>${i.name}${i.address}</li> 
			<form method="post" action="addFood.do">
			<input type="hidden" name="restaurant" value="${i.name}"><input type="submit" value="Select Restaurant to addFood" name="SelectaddFood"/>
			<c:if test="${restaurantName!=null && restaurantName==i.name}"><input type="text" value="TYPE FOOD NAME" name="foodName"/><input type="text" value="TYPE FOOD DESCRIPTION" name="foodDescription"/><input type="text" value="TYPE FOOD PICTURE URL" name="foodUrl"/><br><input type="submit" name="AddFood" value="addfood"/>	</c:if>
			
			</form>
		
		</c:forEach>
</ol>




</body>
</html>