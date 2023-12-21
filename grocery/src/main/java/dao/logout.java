package dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * Servlet implementation class logout
 */
public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Connection connection;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		try {
			connection=derbyconnect.getConnection();
			System.out.println("Connection from controller works" + connection);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			Statement st = connection.createStatement();
			String query = "delete from product";
			int ff = st.executeUpdate(query);
			Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
            String strDate = dateFormat.format(date);  
            String last_logout= strDate;
			String status = "N";
			int customerID = (int) session.getAttribute("customerID");
			String qw = "update customer set last_logout='"+last_logout+"', is_now_logged_in='"+status+"' where customerID="+customerID;
			int dewq = st.executeUpdate(qw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.invalidate();
	    response.sendRedirect("login.jsp");
	    return; // <--- Here.
	}

}
