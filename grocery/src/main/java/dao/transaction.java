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

public class transaction extends HttpServlet {
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
		
		int transactionID = (int) (Math.random() * (9999-1000));
		int customerID = (int) ses.getAttribute("customerID");
		int totalamount=0;
		int items=0;
		String productID = "";
		
		try {
			Statement stt = connection.createStatement();
			String qt = "select * from product where customerID="+customerID;
			ResultSet r = stt.executeQuery(qt);
			
			
			while(r.next()) {
			productID = productID + Integer.toString(r.getInt(1)) + "  ";
			totalamount = totalamount + (r.getInt(3)*r.getInt(4));
			items++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			Statement st = connection.createStatement();
			String query = "select * from tran where transactionID="+transactionID;
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				PrintWriter display = new PrintWriter(System.out);
				display.write("Transaction ID already exists");
				display.flush();
				display.close();
				rs.close();
				st.close();
			}
			else {
			
				try{
					
					PreparedStatement preparedStatement=connection.prepareStatement("insert into tran values(?,?,?,?,?)");
					preparedStatement.setInt(1,  transactionID);
					preparedStatement.setInt(2, customerID);
					preparedStatement.setString(3, productID);
					preparedStatement.setInt(4, totalamount);
					preparedStatement.setInt(5, items);
					int rows=preparedStatement.executeUpdate();
					if(rows>0) {
						
						System.out.println("Order placed");
						System.out.println();
						ses.setAttribute("transactionID", transactionID);
						response.sendRedirect("orderplaced.jsp");
						
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
