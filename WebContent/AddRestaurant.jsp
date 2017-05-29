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
<title>Add Restaurant</title>
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

<h2>Add a restaurant by typing the name and the address</h2>
<form method="post" action="addRestaurant.do">

<div class="editform addtable">
<label>Restaurant name: <input type="text" name ="rname"></label><br>
<label>Type of food: <input type="text" name ="foodType"></label><br>
</div>
<div class="editform1">
<label>Restaurant street address: <input class="address" type="text" name ="street"></label><br>
<label>Restaurant city: <input class="address" type="text" name ="city"></label><br>
<label>Restaurant state: <input class="address" type="text" name ="state"></label><br>
<label>Restaurant zipcode: <input class="address" type="text" name ="zipcode"></label><br>

<div class="editsubmit">
<input type="submit" name="AddRestaurant"/>
</div>
</div>

</form>

</body>
</html>