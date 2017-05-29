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
<title>Edit Restaurant</title>
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
	<h2>Edit Restaurant information</h2>
	
	<form method="post" action="editRestaurant.do">
<label>Restaurant name: <input type="text" name ="rname" value="${restaurant.rname}"></label><br>	
<label>Food Type: <input type="text" name ="foodType" value="${restaurant.foodType}"></label><br>	
	<input type="hidden" name ="rid" value="${restaurant.rid}">

<ol>
<c:if test="${not empty address}">
<h3>Address</h3>
		<c:forEach items="${address}" var="j">
		
<li><label>Street: <input type="text" name ="street" value="${j.street}"></label><br>
<label>City: <input type="text" name ="city" value="${j.city}"></label><br>
<label>State: <input type="text" name ="state" value="${j.state}"></label><br>
<label>Zip-code: <input type="text" name ="zipcode" value="${j.zipcode}"></label><br></li>

<input type="hidden" name ="aid" value="${j.aid}">

<input type="hidden" name ="restaurantId" value="${j.restaurantId}">
</c:forEach>
</c:if>
</ol>

<ol>
<c:if test="${not empty food}">
<h3>Food</h3>
		<c:forEach items="${food}" var="k">
		
<li><label>Food Name: <input type="text" name ="fname" value="${k.fname}"></label><br>
<label>Price: <input type="text" name ="price" value="${k.price}"></label><br>
<label>Description: <input type="text" name ="description" value="${k.description}"></label><br>
<label>url: <input type="text" name ="url" value="${k.url}"></label><br></li>
<input type="hidden" name ="fid" value="${k.fid}">
</c:forEach>
</c:if>
</ol>
<input type="submit" name="editRestaurant"/>
</form>

</body>
</html>