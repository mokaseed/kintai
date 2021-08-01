package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClockOnDAO;
import entity.Employee;


@WebServlet("/Cond")
public class Cond extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//直接アクセスに対して従業員が既にログインしていたら勤怠入力画面にフォワード。
		//ログインしていない場合はTOP画面へリダイレクト。
				
		HttpSession session = request.getSession();
		if(session.getAttribute("account") == null) {
			response.sendRedirect("empLogin.jsp");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/clockOn.jsp");
			dispatcher.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String clockOn = request.getParameter("clockOn");
		String cond = request.getParameter("cond");
		
		HttpSession session = request.getSession();
		Employee account = (Employee)session.getAttribute("account");
		
		ClockOnDAO clockOnDAO = new ClockOnDAO();
		boolean flag = false;
		
		if(clockOn.equals("work_start")) {
			flag = clockOnDAO.setWorkStartTime(account.getEmpId(), cond);
		} else if(clockOn.equals("work_finish")) {
			flag = clockOnDAO.setWorkFinishTime(account.getEmpId(), cond);
		}
		
		if(flag) {
			request.setAttribute("clockOn", clockOn);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/clockOnOK.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/clockOnError.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
