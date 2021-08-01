package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Employee;

/**
 * Servlet implementation class SessionTest
 */
@WebServlet("/SessionTest")
public class SessionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Employee account = new Employee(000001, "1234");
		
		HttpSession session = request.getSession();
		session.setAttribute("account", account);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/clockOnError.jsp");
		dispatcher.forward(request, response);	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
