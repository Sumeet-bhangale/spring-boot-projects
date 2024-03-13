<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%><hr>

<table border=1 >
	<tr>
		<th>ID
		<th>NAME
		<th>PRICE
		<th>DATE ADDED
		<th>ACTION
		
	</tr>

	<c:forEach var="product" items="${prodlist}">
		<tr>
			<td>${product.id}
			<td>${product.name}
			<td>${product.price}
			<td>${product.dateAdded}
			<td><a href="editProduct?id=${product.id}"> EDIT </a> 
			<a href="deleteProduct?id=${product.id}">  &nbsp; | &nbsp; DELETE </a>
		<tr>
	</c:forEach>

</table>