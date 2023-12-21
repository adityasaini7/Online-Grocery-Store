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

public class AddtoCart extends HttpServlet {
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
		
		int productID = Integer.parseInt(request.getParameter("productID"));
		String product_name = request.getParameter("product_name");
		int price = Integer.parseInt(request.getParameter("price"));
		int quantity =  Integer.parseInt(request.getParameter(Integer.toString(productID)));
		
		String reserved = "reserved";
		int customerID = (int) ses.getAttribute("customerID");
		
		
		try {
			Statement st = connection.createStatement();
			String query = "select * from product where product_name='"+product_name+"' AND customerID="+customerID;
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				PrintWriter display = new PrintWriter(System.out);
				display.write("Product with this name already exists in the cart");
				display.flush();
				display.close();
				rs.close();
				st.close();
			}
			else {
			
				try{
					
					PreparedStatement preparedStatement=connection.prepareStatement("insert into product values(?,?,?,?,?,?)");
					preparedStatement.setInt(1,  productID);
					preparedStatement.setString(2, product_name);
					preparedStatement.setInt(3, price);
					preparedStatement.setInt(4, quantity);
					preparedStatement.setString(5, reserved);
					preparedStatement.setInt(6, customerID);
					int rows=preparedStatement.executeUpdate();
					if(rows>0) {
						String kk = "delete from tempproductlist where productID="+productID;
						int test = st.executeUpdate(kk);
						System.out.println("Product added in the cart and removed from productlist");
						System.out.println();
						if(ses.getAttribute("position").equals("admin")) {
						response.sendRedirect("adminhomepage.jsp");
						}else {
							response.sendRedirect("userhomepage.jsp");
						}
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
