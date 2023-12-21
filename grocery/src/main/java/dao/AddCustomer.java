package dao;

import jakarta.servlet.ServletException;
import java.sql.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;


/**
 * Servlet implementation class AddCustomer
 */

public class AddCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public Connection connection;
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession ses = request.getSession();
		try {
			connection=derbyconnect.getConnection();
			System.out.println("Connection from controller works" + connection);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		int customerID = (int) (Math.random() * (9999-1000));
		String name = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		boolean ga = address.toLowerCase().contains("us");
		String customer_name = "";
		if(ga) {
			customer_name = "US_" + name;
		}else{
			customer_name = "IN_" + name;
		}
		String contact = request.getParameter("contact");
		String position = "customer";
		
		System.out.println("ADDCUSTOMER " + customerID + customer_name);
		
		try {
			Statement st = connection.createStatement();
			String query = "select * from register where email='"+email+"'";
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				PrintWriter display = new PrintWriter(System.out);
				display.write("Account with this email already exists");
				display.flush();
				display.close();
				rs.close();
				st.close();
			}
			else {
			
				try{
					
					PreparedStatement preparedStatement=connection.prepareStatement("insert into register values(?,?,?,?,?,?,?)");
					preparedStatement.setInt(1,  customerID);
					preparedStatement.setString(2, customer_name);
					preparedStatement.setString(3, email);
					preparedStatement.setString(4, password);
					preparedStatement.setString(5, address);
					preparedStatement.setString(6, contact);
					preparedStatement.setString(7, position);
					int rows=preparedStatement.executeUpdate();
					if(rows>0) {
						response.sendRedirect("regack.jsp");
						ses.setAttribute("userid", customerID);
						ses.setAttribute("pass", password);
						System.out.println("Customer added in db");
						System.out.println();
					}else {
						System.out.println("Failed to execute Query");
						System.out.println();
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
