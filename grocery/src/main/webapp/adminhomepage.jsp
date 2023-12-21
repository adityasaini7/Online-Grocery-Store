<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="dao.derbyconnect" %>
    <%@ page import = "java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Portal</title>
<style>
body {
    font-family: 'Arial', sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4;
}

.navbar {
    background-color: #333;
}

ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
}

li {
    float: right;
}

a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

a:hover {
    background-color: #ddd;
    color: black;
}

input{
    display: block;
    color: white;
    text-align: center;
     background-color: #333;
     font-size: 18px;
    padding: 14px 16px;
    text-decoration: none;
    border: none;
}

input:hover {
	cursor: pointer;
    background-color: #ddd;
    color: black;
}

 button {
            padding: 10px 20px;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

button:hover {
            background-color: #0056b3;
        }
        
        table {
            width: 80%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #007BFF;
            color: #fff;
        }
         .container{
            display: flex;
            width: 100%;
            flex-direction: column;
            align-items: center;
            margin: 20px;
        }

</style>
</head>
<body>
<nav class="navbar">
    <ul>
    <form method="post" action="Mainframe">
    <li><input type="submit" value="Logout"><input type="hidden" name="type" value="logout"></li>
    </form>
    <form method="post" action="viewcustomers.jsp">
    <li><input type="submit" value="View Registered Customers"></li>
     </form>
     <form method="post" action="viewadmins.jsp">
    <li><input type="submit" value="View Registered Admins"></li>
     </form>
     <form method="post" action="viewalltransaction.jsp">
    <li><input type="submit" value="View all transaction"></li>
     </form>
     <form method="post" action="addproduct.jsp">
     <li><input type="submit" value="Add Product"></li>
     </form>
     <form method="post" action="addadmin.jsp">
     <li><input type="submit" value="Add Admin"></li>
     </form>
     </form>
     <form method="post" action="orderplaced.jsp">
    <li><input type="submit" value="Order list"></li>
    </form>
     <form method="post" action="viewcart.jsp">
     <li><input type="submit" value="View Cart"></li>
     </form>
       
        
    </ul>
</nav>
<%! public Connection connection; %>
<div class="container">
<h1 style="color: grey">** Products **</h1><hr>

    <table id="data">
        <thead>
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Product Price</th>
                <th>Quantity</th>
              	<th></th>
            </tr>
        </thead>
        <tbody>
        <%

		
		HttpSession ses = request.getSession();

		try{
			connection=derbyconnect.getConnection();
			System.out.println("Connection from controller works" + connection);
			Statement st = connection.createStatement();
			String sql = "select * from tempproductlist ";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){ 
            	%>
			
            <tr>
                <td><%= rs.getInt(1) %></td>
                <td><%= rs.getString(2) %></td>
                <td><%= rs.getInt(3) %></td>
                <form method="post" action="Mainframe">
                <td><input type="text" name="<%= Integer.toString(rs.getInt(1)) %>" style="color: black; background:white; cursor: auto;"></td>
            	<td><input type="submit" value="Add to Cart"></td>
            	<input type="hidden" name="type" value="addtocart"><input type="hidden" name="productID" value="<%= Integer.toString(rs.getInt(1)) %>">
            	<input type="hidden" name="product_name" value="<%= rs.getString(2) %>"><input type="hidden" name="price" value="<%= Integer.toString(rs.getInt(3)) %>">
            	
            	 </form>
            </tr>
            
            	<% 
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}  %>
        </tbody>
        </table>
       

</div>

</body>
</html>