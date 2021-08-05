package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.WorkTimeDAO;
import entity.Employee;
import entity.WorkTime;


@WebServlet("/SelectWorkTimeList")
public class SelectWorkTimeList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//直接アクセスに対してログインしていない場合はログイン画面へリダイレクト。
		//従業員が既にログインしていたらタイムシート選択画面にフォワード。
		//actionがdoneの場合は勤務時間修正からのリダイレクトのため、修正後のタイムシートを表示
		
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		
		if(session.getAttribute("account") == null) {
			response.sendRedirect("empLogin.jsp");
		} else if(action == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/selectWorkTimeList.jsp");
			dispatcher.forward(request, response);
		} else if(action.equals("done")) {
			request.setCharacterEncoding("UTF-8");
			String thisMonth = (String)session.getAttribute("request-month");
			session.removeAttribute("request-month");
			Calendar thisMonthCalendar = Calendar.getInstance();
			thisMonthCalendar.set(Calendar.YEAR, Integer.parseInt(thisMonth.substring(0, 4)));
			thisMonthCalendar.set(Calendar.MONTH, Integer.parseInt(thisMonth.substring(5, 7)));
			
			Employee account = (Employee)session.getAttribute("account");
			WorkTimeDAO tsDAO = new WorkTimeDAO();
			List<WorkTime> workTimeList = new ArrayList<>();
			
			workTimeList = tsDAO.selectWorkTimeList(account.getEmpId(), thisMonth);
			
			String nextJsp;
			if(workTimeList == null) {
				nextJsp = "/WEB-INF/jsp/selectTimeSheetError.jsp";
			} else {
				session.setAttribute("workTimeList", workTimeList);
				session.setAttribute("thisMonthCalendar", thisMonthCalendar);
				nextJsp = "/WEB-INF/jsp/workTimeList.jsp";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextJsp);
			dispatcher.forward(request, response);
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String thisMonth = request.getParameter("request-month");
		Calendar thisMonthCalendar = Calendar.getInstance();
		thisMonthCalendar.set(Calendar.YEAR, Integer.parseInt(thisMonth.substring(0, 4)));
		thisMonthCalendar.set(Calendar.MONTH, Integer.parseInt(thisMonth.substring(5, 7)));
		
		HttpSession session = request.getSession();
		Employee account = (Employee)session.getAttribute("account");
		WorkTimeDAO tsDAO = new WorkTimeDAO();
		List<WorkTime> workTimeList = new ArrayList<>();
		
		workTimeList = tsDAO.selectWorkTimeList(account.getEmpId(), thisMonth);
		
		String nextJsp;
		if(workTimeList == null) {
			nextJsp = "/WEB-INF/jsp/selectTimeSheetError.jsp";
		} else {
			session.setAttribute("workTimeList", workTimeList);
			session.setAttribute("thisMonthCalendar", thisMonthCalendar);
			nextJsp = "/WEB-INF/jsp/workTimeList.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextJsp);
		dispatcher.forward(request, response);
		
	}

}
