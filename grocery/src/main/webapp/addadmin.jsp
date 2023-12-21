<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Page</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div class="registration-container">
    <h2>Admin Registration</h2>

    <form action="Mainframe" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="username" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <label for="address">Address:</label>
        <textarea id="address" name="address" rows="4" required></textarea>

        <label for="contact">Contact:</label>
        <input type="tel" id="contact" name="contact" required>
        
        <input type="hidden" name="type" value="admin">

        <input type="submit" value="Register">
    </form>
</div>

</body>
</html>


