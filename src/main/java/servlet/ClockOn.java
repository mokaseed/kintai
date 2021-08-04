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

/**
 * Servlet implementation class ClockOn
 */
@WebServlet("/ClockOn")
public class ClockOn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//従業員が既にログインしていたら勤怠入力画面にフォワード。
		//ログインしていない場合はログイン画面へリダイレクト。
		
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
		
		HttpSession session = request.getSession();
		Employee account = (Employee)session.getAttribute("account");
		
		ClockOnDAO clockOnDAO = new ClockOnDAO();
		
		//押されたボタンが「出勤」もしくは「退勤」の場合、コンディション入力へフォワード
		//押されたバタンが「休憩開始」もしくは「休憩終了」の場合は時間をDBに記録して打刻完了画面へフォアード
		boolean flag = false;
		String nextJsp = null;
		if(clockOn.equals("work_start")) {
			request.setAttribute("clockOn", "work_start");
			nextJsp = "WEB-INF/jsp/cond.jsp";
			flag = true;
		} else if(clockOn.equals("work_finish")) {
			request.setAttribute("clockOn", "work_finish");	
			nextJsp = "WEB-INF/jsp/cond.jsp";
			flag = true;
		} else if(clockOn.equals("break_start")) {
			flag = clockOnDAO.setBreakStartTime(account.getEmpId());
			nextJsp = "WEB-INF/jsp/clockOnOK.jsp";
		} else if(clockOn.equals("break_finish")) {
			flag = clockOnDAO.setBreakFinishTime(account.getEmpId());
			nextJsp = "WEB-INF/jsp/clockOnOK.jsp";
		}
		
		if(flag == false) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("clockOnError");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("clockOn", clockOn);
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextJsp);
			dispatcher.forward(request, response);
		}
		
	}

}
