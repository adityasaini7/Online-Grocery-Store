<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Login</title>
</head>
<body>
<div class="registration-container">
    <h2>Login</h2>

    <form action="Mainframe" method="post">
        <label for="customerID">User ID</label>
        <input type="text" id="customerID" name="customerID" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        
        <input type="hidden" name="type" value="login">

        <input type="submit" value="Login">
    </form>
</div>
</body>
</html>