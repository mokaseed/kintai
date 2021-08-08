package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Forward")
public class Forward extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String nextJsp = null;
		
		HttpSession session = request.getSession();
		if(session.getAttribute("account") == null) {
			response.sendRedirect("top.jsp");
			
		} else {
			if(action.equals("empMenu")){
				nextJsp = "/WEB-INF/jsp/empMenu.jsp";
			} else if(action.equals("sysadminMenu")){
				nextJsp = "/WEB-INF/jsp/sysadminMenu.jsp";
			} else {
				nextJsp = "/top.jsp";
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextJsp);
			dispatcher.forward(request, response);			
		}
		
	}

}
