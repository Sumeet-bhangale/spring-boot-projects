<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="header.jsp"%><hr>

Edit Product<hr>

<div align="center">
Edit Product<hr>

	<form:form action="editProduct" method="post" modelAttribute="product">
		<form:input path="id" value="${product.id}" hidden="true"/>

		<form:label path="name">Product name:</form:label>
		<form:input path="name" value="${product.name}" />
		<br />
		
		<form:label path="price">Product Price:</form:label>
		<form:input path="price" value="${product.price}" />
		<br />
		
		<form:label path="dateAdded">Product Date Added:</form:label>
		<form:input path="dateAdded" readonly="true"/>
		<br />



		<form:button>Edit and Save Product</form:button>

	</form:form>

	<br>
	<br> <a href="/"> Back to HOME</a>