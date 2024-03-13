<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="header.jsp"%><hr>

<div align="center">

	<form:form action="addProduct" method="post" modelAttribute="product">

		<form:label path="name">Product name:</form:label>
		<form:input path="name" />
		<br />
		
		<form:label path="price">Product Price:</form:label>
		<form:input path="price" />
		<br />
		
		<!--<form:label path="dateAdded">Product Date Added:</form:label>
		<form:input path="dateAdded" />-->
		
		<form:label path="dateAdded">Product Date Added:</form:label>
		<input name="dateAdded" type="date" />
		<br />



		<form:button>Add Product</form:button>

	</form:form>

	<br>
	<br> <a href="/"> Back to HOME</a>