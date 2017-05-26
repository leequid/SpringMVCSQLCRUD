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

<ol>
		<c:forEach items="${restaurantList}" var="i">
		
<li><div class="editform"><label>Restaurant name: <input type="text" name ="restaurantName" value="${i.name}"></label></div><br>
<div class="editform2"><label>Restaurant Address: <input class="address" type="text" name ="restaurantAddress" value="${i.address}"></label><br></div></li>

</c:forEach>
</ol>
<input type="submit" name="editRestaurant"/>
</form>

</body>
</html>