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

	
	<form method="post" action="editRestaurant.do">

<ol>
		<c:forEach items="${restaurantList}" var="i">
<li><label>Restaurant name: <input type="text" name ="restaurantName" value="${i.name}"></label><br>
<label>Restaurant Address: <input type="text" name ="restaurantAddress" value="${i.address}"></label><br></li>
</c:forEach>
</ol>
<input type="submit" name="editRestaurant"/>
</form>


<a href="addRestaurant.jsp">Add Restaurant</a><br>
<a href="viewRestaurant.do">View your List!</a><br>
</body>
</html>