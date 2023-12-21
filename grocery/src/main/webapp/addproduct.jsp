<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div class="registration-container">
    <h2>Add Product</h2>

    <form action="Mainframe" method="post">
        <label for="name">Product Name:</label>
        <input type="text" id="name" name="productname" required>

        <label for="price">Price:</label>
        <input type="text" id="price" name="price" required>
        
        <input type="hidden" name="type" value="addproduct">

        <input type="submit" value="Add">
    </form>
</div>

</body>
</html>


