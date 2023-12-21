<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="dao.derbyconnect" %>
    <%@ page import = "java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart</title>
    <style>
        body{
            font-family: "calibri";
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 60vh;
            background:#f4f4f4;
        }
        .container{
            display: flex;
            width: 100%;
            flex-direction: column;
            align-items: center;
            margin: 20px;
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

    </style>
</head>
<body>
<%! public Connection connection; %>

    <div class="container">
        <h1 style="color: grey">** Cart Items **</h1><hr>
    <table id="data">
        <thead>
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Price</th>
               <th>Quantity</th>
               <th></th>
                
            </tr>
        </thead>
        <tbody>

<%

		
		HttpSession ses = request.getSession();
		int cid = (int) ses.getAttribute("customerID");
		try{
			connection=derbyconnect.getConnection();
			System.out.println("Connection from controller works" + connection);
			Statement st = connection.createStatement();
			String sql = "select * from product where customerID="+cid;
			ResultSet rs = st.executeQuery(sql);
			int sum = 0;
			while(rs.next()){ sum = sum + (rs.getInt(4)*rs.getInt(3));%>
			
            <tr>
                <td><%= rs.getInt(1) %></td>
                <td><%= rs.getString(2) %></td>
                <td><%= rs.getInt(3) %></td>
                <td><%= rs.getInt(4) %></td>
            	<form method="post" action="Mainframe">
            	<td><input type="submit" value="Remove Item">
            	<input type="hidden" name="productID" value="<%= Integer.toString(rs.getInt(1)) %>">
            	<input type="hidden" name="product_name" value="<%= rs.getString(2) %>">
                <input type="hidden" name="price" value="<%= Integer.toString(rs.getInt(3)) %>">
            	<input type="hidden" name="type" value="removefromcart"></td>
            	</form>
            </tr>
            
            	<% }
			%>
			<tr>
            <td colspan="3"><b>TOTAL PRICE =></b></td>
            <td><%= sum %></td>
            <form method="post" action="Mainframe">
            <td><input type="submit" value="Order"><input type="hidden" name="type" value="transaction"></td>
            </form>
            </tr>
            
			<%
		}catch(SQLException e) {
			e.printStackTrace();
		}  %>
            <!-- Add more rows as needed -->
        </tbody>
    </table>
    
</div>





			
			
			
			



