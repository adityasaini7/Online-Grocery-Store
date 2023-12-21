<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="dao.derbyconnect" %>
    <%@ page import = "java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registered Customers</title>
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
        <h1 style="color: grey">** Registered Customers **</h1><hr>
    <table id="data">
        <thead>
            <tr>
                <th>Customer ID</th>
                <th>Customer Name</th>
                <th>Email</th>
                <th>Password</th>
                <th>Address</th>
                <th>Contact</th>
            </tr>
        </thead>
        <tbody>

<%

		
		HttpSession ses = request.getSession();

		try{
			connection=derbyconnect.getConnection();
			System.out.println("Connection from controller works" + connection);
			Statement st = connection.createStatement();
			String sql = "select * from register where position='admin'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){ %>
			
            <tr>
                <td><%= rs.getInt(1) %></td>
                <td><%= rs.getString(2) %></td>
                <td><%= rs.getString(3) %></td>
                <td><%= rs.getString(4) %></td>
                <td><%= rs.getString(5) %></td>
                <td><%= rs.getInt(6) %></td>
            
            </tr>
            	<% }
		}catch(SQLException e) {
			e.printStackTrace();
		}  %>
            <!-- Add more rows as needed -->
        </tbody>
    </table>
    
</div>





			
			
			
			



