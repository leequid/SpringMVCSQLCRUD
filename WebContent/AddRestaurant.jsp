<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Restaurant</title>
</head>
<body>



<form method="post" action="addRestaurant.do">


<label>Restaurant name: <input type="text" name ="restaurantName"></label><br>
<label>Restaurant Address: <input type="text" name ="restaurantAddress"></label><br>

<input type="submit" name="AddRestaurant"/>
</form>
<a href="deleteRestaurant.do">Delete Restaurant!</a><br>
<a href="viewRestaurant.do">View your List!</a><br>
<a href="editRestaurant.do">Edit your List!</a><br>
</body>
</html>