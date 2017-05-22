<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>Restaurant name. Address</div>
<ol>
<c:forEach items="${restaurantList}" var="i">
<li>${i.name}   ${i.address}</li>

<ol>
<c:forEach items="${i.foodList}" var="f">
<li>${f.name} ${f.veg} ${f.description } </li><br>
<img src="${f.url}" alt="picture of food" height="120" width="160">
</c:forEach>
</ol>
</c:forEach>
</ol>
<a href="addRestaurant.jsp">Add Restaurant</a><br>
<a href="deleteRestaurant.do">Delete Restaurant!</a><br>
<a href="editRestaurant.do">Edit your List!</a><br>
</body>
</html>