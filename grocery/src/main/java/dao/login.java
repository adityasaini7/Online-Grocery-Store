package dao;

import jakarta.servlet.ServletException;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Connection connection;
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession hs = request.getSession();
		
		try {
			connection=derbyconnect.getConnection();
			System.out.println("Connection from controller works");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		int customerID = Integer.parseInt(request.getParameter("customerID"));
		String updated_password = request.getParameter("password");
		
		try {
			Statement st = connection.createStatement();
			String query = "select * from register where customerID="+customerID+"AND password='"+updated_password+"'";
			ResultSet rs = st.executeQuery(query);
			if(!rs.next()) {
				PrintWriter display = new PrintWriter(System.out);
				display.write("Account does not exist");
				display.flush();
				display.close();
				rs.close();
				st.close();
			}else {
				String position = rs.getString(7);
				hs.setAttribute("position", position);
				String old_password = "";
				Date date = Calendar.getInstance().getTime();  
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
                String strDate = dateFormat.format(date);  
                String last_login = strDate;
				String last_logout = "";
				String status = "Y";
				
				System.out.println("ADDCUSTOMER " + customerID + updated_password);
				
				String check =  "select customerID from customer where customerID="+customerID;
				ResultSet se = st.executeQuery(check);
				if(se.next()) {
					String qqq = "update customer set last_login='"+last_login+"', is_now_logged_in='"+status+"' where customerID="+customerID;
					st.executeUpdate(qqq);
					System.out.println("updated");
					hs.setAttribute("customerID", customerID);
					hs.setAttribute("status", status );
				}else {
					try{
						
						PreparedStatement preparedStatement=connection.prepareStatement("insert into customer values(?,?,?,?,?,?)");
						preparedStatement.setInt(1,  customerID);
						preparedStatement.setString(2, last_login);
						preparedStatement.setString(3, last_logout);
						preparedStatement.setString(4, updated_password);
						preparedStatement.setString(5, old_password);
						preparedStatement.setString(6, status);
						int rows=preparedStatement.executeUpdate();
						if(rows>0) {
							
							hs.setAttribute("customerID", customerID);
							hs.setAttribute("status", status );
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
				//refresh addtocart
				String qe = "delete from product";
				int ff = st.executeUpdate(qe);
				//refresh tempproductlist
				String yo = "delete from tempproductlist";
				int dd = st.executeUpdate(yo);
				//refresh tempproductlist
				Statement stt = connection.createStatement();
				String dde = "select * from productlist";
				ResultSet ree = stt.executeQuery(dde);
				
					while(ree.next()){
					String shs = "insert into tempproductlist values("+ree.getInt(1)+",'"+ree.getString(2)+"',"+ree.getInt(3)+")";
					int ew = st.executeUpdate(shs);
					}
				
				String q = "select position from register where customerID="+customerID;
				ResultSet user_role = st.executeQuery(q);
				user_role.next();
				if(user_role.getString(1).equals("customer")) {
					response.sendRedirect("userhomepage.jsp");
				}else if(user_role.getString(1).equals("admin")) {
					response.sendRedirect("adminhomepage.jsp");
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
