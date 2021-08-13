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
			//直接アクセスされた場合は、フィルターのログインチェックを通り勤怠打刻画面へ遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/clockOn.jsp");
			dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String clockOn = request.getParameter("clockOn");
		String cond = request.getParameter("cond");
		
		HttpSession session = request.getSession(false);
		Employee account = (Employee)session.getAttribute("account");
		
		ClockOnDAO clockOnDAO = new ClockOnDAO();
		boolean flag = false;
		
		//出勤か退勤かを判別してdaoに時刻とコンディションの記録を依頼
		if(clockOn.equals("work_start")) {
			flag = clockOnDAO.setWorkStartTime(account.getEmpId(), cond);
		} else if(clockOn.equals("work_finish")) {
			flag = clockOnDAO.setWorkFinishTime(account.getEmpId(), cond);
		}
		
		//DB登録が成功したら打刻完了画面へ、失敗したらエラー画面へ
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
