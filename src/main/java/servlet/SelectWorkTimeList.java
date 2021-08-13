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
			
		String action = request.getParameter("action");
		HttpSession session = request.getSession(false);
		
        //当月のタイムシート画面に遷移
		if(action == null) {
			
			//当月の勤務時刻情報をdaoから取得するために、今の年月を取得しthisMonthにセット
			Calendar thisMonthCalendar = Calendar.getInstance();
			thisMonthCalendar.set(Calendar.YEAR, thisMonthCalendar.get(Calendar.YEAR));
			thisMonthCalendar.set(Calendar.MONTH, thisMonthCalendar.get(Calendar.MONTH) +1);
			
			String y = String.valueOf(thisMonthCalendar.get(Calendar.YEAR));
			String m;
			if(String.valueOf(thisMonthCalendar.get(Calendar.MONTH)).length() == 1) {
				m = "0" + thisMonthCalendar.get(Calendar.MONTH);
			} else {
				m = String.valueOf(thisMonthCalendar.get(Calendar.MONTH));
			}
			String thisMonth = y + "-" + m ;
			
			
			Employee account = (Employee)session.getAttribute("account");
			WorkTimeDAO tsDAO = new WorkTimeDAO();
			List<WorkTime> workTimeList = new ArrayList<>();
			
			//当月の勤務時刻情報を取得
			workTimeList = tsDAO.selectWorkTimeList(account.getEmpId(), thisMonth);
			
			String nextJsp;
			if(workTimeList == null) {
				nextJsp = "/WEB-INF/jsp/selectWorkTimeListError.jsp";
			} else {
				session.setAttribute("workTimeList", workTimeList);
				session.setAttribute("thisMonthCalendar", thisMonthCalendar);
				nextJsp = "/WEB-INF/jsp/workTimeList.jsp";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextJsp);
			dispatcher.forward(request, response);
			
			
		//actionがdoneの場合は勤務時間修正からのリダイレクトのため、修正後のタイムシートを表示
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
			
			//勤務時刻を修正した月の勤務時刻情報を取得
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
		
		//勤怠管理画面の年月選択で選択された月のタイムシートを表示
		request.setCharacterEncoding("UTF-8");
		
		//選択された年月を取得
		String y = request.getParameter("selectY");
		String m = request.getParameter("selectM");
	
		if(m.length() == 1) {
			m = "0" + m;
		}
		String thisMonth = y + "-" + m ;
		
		Calendar thisMonthCalendar = Calendar.getInstance();
		thisMonthCalendar.set(Calendar.YEAR, Integer.parseInt(y));
		thisMonthCalendar.set(Calendar.MONTH, Integer.parseInt(m));
		
		HttpSession session = request.getSession(false);
		Employee account = (Employee)session.getAttribute("account");
		WorkTimeDAO tsDAO = new WorkTimeDAO();
		List<WorkTime> workTimeList = new ArrayList<>();
		
		//選択された年月の勤務時刻情報を取得
		workTimeList = tsDAO.selectWorkTimeList(account.getEmpId(), thisMonth);
		
		String nextJsp;
		if(workTimeList == null) {
			nextJsp = "/WEB-INF/jsp/selectWorkTimeListError.jsp";
		} else {
			session.setAttribute("workTimeList", workTimeList);
			session.setAttribute("thisMonthCalendar", thisMonthCalendar);
			nextJsp = "/WEB-INF/jsp/workTimeList.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextJsp);
		dispatcher.forward(request, response);
		
	}

}
