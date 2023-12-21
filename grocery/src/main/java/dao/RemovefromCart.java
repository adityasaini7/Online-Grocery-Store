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

public class RemovefromCart extends HttpServlet {
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
		int customerID = (int) ses.getAttribute("customerID");
		String product_name = request.getParameter("product_name");
		int price = Integer.parseInt(request.getParameter("price"));
		
		try {
			Statement st = connection.createStatement();
			String query = "select * from product where productID="+productID+" AND customerID="+customerID;
			ResultSet rs = st.executeQuery(query);
			if(!rs.next()) {
				PrintWriter display = new PrintWriter(System.out);
				display.write("Product is not removable cause it doesn't exist");
				display.flush();
				display.close();
				rs.close();
				st.close();
			}
			else {
			
				try{
					String qq = "delete from product where productID="+productID+" AND customerID="+customerID;
					int re = st.executeUpdate(qq);
					if(re>0) {
						String oo = "insert into tempproductlist values("+productID+",'"+product_name+"',"+price+")";
						int sd = st.executeUpdate(oo);
						response.sendRedirect("viewcart.jsp");
						System.out.println("Product deleted from cart");
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
