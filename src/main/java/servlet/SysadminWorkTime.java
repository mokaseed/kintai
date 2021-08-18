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

//管理者ログインチェックを通る

@WebServlet("/SysadminWorkTime")
public class SysadminWorkTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		//
		if(action == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/");
			dispatcher.forward(request, response);
		
		//選択された従業員の当月のタイムシートを表示
		} else {
			HttpSession session = request.getSession(false);
			String index = (String)session.getAttribute("index");
			List<Employee> empList = (List<Employee>)session.getAttribute("empList");
			
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
			
			//選択された従業員の社員IDを取得
			Employee emp = empList.get(Integer.parseInt(index, 10));
			WorkTimeDAO workTimeDAO = new WorkTimeDAO();
			List<WorkTime> workTimeList = workTimeDAO.selectWorkTimeList(emp.getEmpId(), thisMonth);
			
			String nextJsp = null;
			if(workTimeList == null) {
				nextJsp = "/WEB-INF/jsp/sysadminError.jsp";
			} else {
				session.setAttribute("workTimeList", workTimeList);
				session.setAttribute("thisMonthCalendar", thisMonthCalendar);
				nextJsp = "/WEB-INF/jsp/sysadminWorkTimeList.jsp";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextJsp);
			dispatcher.forward(request, response);
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//勤怠管理画面の年月選択で選択された月のタイムシートを表示
		
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
				
				//社員IDを取得
				HttpSession session = request.getSession(false);
				String index = (String)session.getAttribute("index");
				List<Employee> empList = (List<Employee>)session.getAttribute("empList");
				Employee emp = empList.get(Integer.parseInt(index, 10));
				
				WorkTimeDAO tsDAO = new WorkTimeDAO();
				List<WorkTime> workTimeList = new ArrayList<>();
				
				//選択された年月の勤務時刻情報を取得
				workTimeList = tsDAO.selectWorkTimeList(emp.getEmpId(), thisMonth);
				
				String nextJsp;
				if(workTimeList == null) {
					nextJsp = "/WEB-INF/jsp/sysadminError.jsp";
				} else {
					session.setAttribute("workTimeList", workTimeList);
					session.setAttribute("thisMonthCalendar", thisMonthCalendar);
					nextJsp = "/WEB-INF/jsp/sysadminWorkTimeList.jsp";
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher(nextJsp);
				dispatcher.forward(request, response);
				
	}

}
