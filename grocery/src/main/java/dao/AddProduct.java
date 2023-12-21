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

public class AddProduct extends HttpServlet {
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
		
		int productID = (int) (Math.random() * (9999-1000));
		String product_name = request.getParameter("productname");
		int price = Integer.parseInt(request.getParameter("price"));
		
		
		try {
			Statement st = connection.createStatement();
			String query = "select * from productlist where product_name='"+product_name+"'";
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				PrintWriter display = new PrintWriter(System.out);
				display.write("Product with this name already exists");
				display.flush();
				display.close();
				rs.close();
				st.close();
			}
			else {
			
				try{
					
					PreparedStatement preparedStatement=connection.prepareStatement("insert into productlist values(?,?,?)");
					preparedStatement.setInt(1,  productID);
					preparedStatement.setString(2, product_name);
					preparedStatement.setInt(3, price);
					int rows=preparedStatement.executeUpdate();
					if(rows>0) {
						String abc = "insert into tempproductlist values("+productID+",'"+product_name+"',"+price+")";
						int f = st.executeUpdate(abc);						
						ses.setAttribute("productID", productID);
						ses.setAttribute("product_name", product_name);
						ses.setAttribute("product_price", price);
						System.out.println("product added in db");
						System.out.println();
						response.sendRedirect("productack.jsp");
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
