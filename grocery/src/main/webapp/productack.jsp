<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product added</title>
<link rel="stylesheet" href="style.css">
</head>
<body style="color: white; background-color: green; text-align: center" >
<h1>Product added</h1><hr style="color: white;">
<p></p>
<% HttpSession ses = request.getSession(); %>
<h3>Product ID : </h3>
<%= ses.getAttribute("productID") %>
<br>
<h3>Product Name : </h3>
<%= ses.getAttribute("product_name") %>
<br>
<h3>Product Price : </h3>
<%= ses.getAttribute("product_price") %>
</body>
</html>