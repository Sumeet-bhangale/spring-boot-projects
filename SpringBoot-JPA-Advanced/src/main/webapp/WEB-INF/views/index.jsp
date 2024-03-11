<html>
<body>
	<h2></h2>
	<%@ include file="header.jsp"%>

	<br>
	<br> Search Products by name

	<form action="listProductsByName" method="GET">
		<br> Name : <input name="name"> <br> <input
			type="submit">
	</form>

	<br>
	<br> Search Products by price

	<form action="listProductsByPrice" method="GET">
		<br> Name : <input name="price"> <br> <input
			type="submit">
	</form>


	<br>
	<br> Search Products by name starting with

	<form action="listProductsByNameStartingWith" method="GET">
		<br> Name : <input name="name"> <br> <input
			type="submit">
	</form>


</body>
</html>