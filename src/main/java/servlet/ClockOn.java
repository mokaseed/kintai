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

//従業員ログインチェックフィルターを通る

@WebServlet("/ClockOn")
public class ClockOn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//直接アクセスされた場合は、フィルターのログインチェックを通り勤怠打刻画面へ遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/emp/clockOn.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String clockOn = request.getParameter("clockOn");
		
		HttpSession session = request.getSession(false);
		Employee account = (Employee)session.getAttribute("account");
		
		ClockOnDAO clockOnDAO = new ClockOnDAO();
		
		//押されたボタンが「出勤」もしくは「退勤」の場合、コンディション入力へフォワード
		//押されたボタンが「休憩開始」もしくは「休憩終了」の場合は時間をDBに記録して打刻完了画面へフォワード
		boolean flag = false;
		String nextJsp = null;
		if(clockOn.equals("work_start")) {
			request.setAttribute("clockOn", "work_start");
			nextJsp = "WEB-INF/jsp/emp/cond.jsp";
			flag = true;
		} else if(clockOn.equals("work_finish")) {
			request.setAttribute("clockOn", "work_finish");	
			nextJsp = "WEB-INF/jsp/emp/cond.jsp";
			flag = true;
		} else if(clockOn.equals("break_start")) {
			flag = clockOnDAO.setBreakStartTime(account.getEmpId());
			nextJsp = "WEB-INF/jsp/emp/clockOnOK.jsp";
		} else if(clockOn.equals("break_finish")) {
			flag = clockOnDAO.setBreakFinishTime(account.getEmpId());
			nextJsp = "WEB-INF/jsp/emp/clockOnOK.jsp";
		}
		
		if(flag == false) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/emp/clockOnError.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("clockOn", clockOn);
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextJsp);
			dispatcher.forward(request, response);
		}
		
	}

}
