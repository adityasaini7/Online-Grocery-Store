package mainframe;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class Mainframe
 */
public class Mainframe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession hs = request.getSession();
		String type = request.getParameter("type");
		System.out.println(type);
		if(type.equals("register")) {
			//redirect to AddCustomer
			RequestDispatcher rd=request.getRequestDispatcher("AddCustomer");  
			rd.forward(request, response);
			
		}else if(type.equals("login")) {
			//redirect to login
			RequestDispatcher rd=request.getRequestDispatcher("login");  
			rd.forward(request, response);
			
			
		}else if(type.equals("admin")) {
			//redirect to addadmin
			RequestDispatcher rd=request.getRequestDispatcher("AddAdmin");  
			rd.forward(request, response);
		}else if(type.equals("addproduct")) {
			//redirect to AddProduct
			RequestDispatcher rd=request.getRequestDispatcher("AddProduct");  
			rd.forward(request, response);
		}else if(type.equals("addtocart")) {
			//redirect to AddtoCart
			RequestDispatcher rd=request.getRequestDispatcher("AddtoCart");  
			rd.forward(request, response);
		}else if(type.equals("removefromcart")) {
			//redirect to RemovefromCart
			RequestDispatcher rd=request.getRequestDispatcher("RemovefromCart");  
			rd.forward(request, response);
		}else if(type.equals("logout")) {
			//redirect to logout
			RequestDispatcher rd=request.getRequestDispatcher("logout");  
			rd.forward(request, response);
			
		}else if(type.equals("transaction")) {
			//redirect to transaction
			RequestDispatcher rd=request.getRequestDispatcher("transaction");  
			rd.forward(request, response);
		}
	}

}
