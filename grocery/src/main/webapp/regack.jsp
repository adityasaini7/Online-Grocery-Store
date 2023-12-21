<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>
<body style="color: white; background-color: green; text-align: center" >
<h1>Registration complete</h1><hr style="color: white;">
<p></p>
<% HttpSession ses = request.getSession(); %>
<h3>Your User ID : </h3>
<%= ses.getAttribute("userid") %>
<br>
<h3>Your password : </h3>
<%= ses.getAttribute("pass") %>
</body>
</html>