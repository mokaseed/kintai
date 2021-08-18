package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

//管理者ログインフィルターを通る

@WebServlet("/SysadminUpdateWorkTime")
public class SysadminUpdateWorkTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession(false);
		
		//社員IDを取得
		String index = (String)session.getAttribute("index");
		List<Employee> empList = (List<Employee>)session.getAttribute("empList");
		Employee emp = empList.get(Integer.parseInt(index, 10));
		
		if(action == null) {
			//勤怠管理画面にて「修正」ボタンが押された年月日と勤務時刻を取得し、勤務時刻修正画面を表示
			WorkTime workTime = new WorkTime();
			
			workTime.setEmpId(emp.getEmpId());
			
			LocalDate workDate = LocalDate.parse(request.getParameter("workDate"));
			workTime.setWorkDate(workDate);			
			
			String startTime = (String)request.getParameter("startTime");
			if(startTime.equals("null") || startTime.length() == 0) {
				workTime.setStartTime(null);
			} else {
				workTime.setStartTime(LocalTime.parse(startTime));			
			}
			
			String breakStartTime = (String)request.getParameter("breakStartTime");
			if(breakStartTime.equals("null") || breakStartTime.length() == 0) {
				workTime.setBreakStartTime(null);
			} else {
				workTime.setBreakStartTime(LocalTime.parse(breakStartTime));			
			}
			
			String breakFinishTime = (String)request.getParameter("breakFinishTime");
			if(breakFinishTime.equals("null") || breakFinishTime.length() == 0) {
				workTime.setBreakFinishTime(null);
			} else {
				workTime.setBreakFinishTime(LocalTime.parse(breakFinishTime));			
			}
			
			String finishTime = (String)request.getParameter("finishTime");
			if(finishTime.equals("null") || finishTime.length() == 0) {
				workTime.setFinishTime(null);
			} else {
				workTime.setFinishTime(LocalTime.parse(finishTime));
			}
			request.setAttribute("workTime", workTime);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadminUpdateWorkTime.jsp");
			dispatcher.forward(request, response);
		
		
		//actionがdoneの場合は勤務時間修正からのリダイレクトのため、修正後のタイムシートを表示
		} else {
			String thisMonth = (String)session.getAttribute("request-month");
			session.removeAttribute("request-month");
			Calendar thisMonthCalendar = Calendar.getInstance();
			thisMonthCalendar.set(Calendar.YEAR, Integer.parseInt(thisMonth.substring(0, 4)));
			thisMonthCalendar.set(Calendar.MONTH, Integer.parseInt(thisMonth.substring(5, 7)));
			
			WorkTimeDAO tsDAO = new WorkTimeDAO();
			List<WorkTime> workTimeList = new ArrayList<>();
			
			//勤務時刻を修正した月の勤務時刻情報を取得
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//勤務時刻修正画面にて入力された時刻を取得し、DBに上書き登録
		//予期せぬ文字（記号等）が入力された場合や、時と分の片方を入力してもう片方が未入力の場合などはエラー画面に遷移
		try {
			HttpSession session = request.getSession(false);
			String index = (String)session.getAttribute("index");
			List<Employee> empList = (List<Employee>)session.getAttribute("empList");
			Employee emp = empList.get(Integer.parseInt(index, 10));
			WorkTime workTime = new WorkTime();
			
			String workDate = request.getParameter("workDate");
			workTime.setWorkDate(LocalDate.parse(workDate));
			
			String hStartTime = request.getParameter("hStartTime");
			String mStartTime = request.getParameter("mStartTime");
			
			//時と分がどちらも未入力の場合はnullをセット
			//1桁の数字が入力された場合は前に0をつける
			if(hStartTime.length() == 0 && mStartTime.length() == 0) {
				workTime.setStartTime(null);
			} else if(hStartTime.length() == 0 || mStartTime.length() == 0) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateWorkTimeError.jsp");
				dispatcher.forward(request, response);
			} else {
				if(hStartTime.length() == 1) {
					hStartTime = "0" + hStartTime;}
				if(mStartTime.length() == 1) {
					mStartTime = "0" + mStartTime;}
				workTime.setStartTime(LocalTime.parse(hStartTime + ":" + mStartTime));
			}
			
			String hBreakStartTime = request.getParameter("hBreakStartTime");
			String mBreakStartTime = request.getParameter("mBreakStartTime");
			if(hBreakStartTime.length() == 0 && mBreakStartTime.length() == 0) {
				workTime.setBreakStartTime(null);
			} else {
				if(hBreakStartTime.length() == 1) {
					hBreakStartTime = "0" + hBreakStartTime;}
				if(mBreakStartTime.length() == 1) {
					mBreakStartTime = "0" + mBreakStartTime;}
				workTime.setBreakStartTime(LocalTime.parse(hBreakStartTime + ":" + mBreakStartTime));
			}
			
			String hBreakFinishTime = request.getParameter("hBreakFinishTime");
			String mBreakFinishTime = request.getParameter("mBreakFinishTime");
	
			
			if(hBreakFinishTime.length() == 0 && mBreakFinishTime.length() == 0) {
				workTime.setBreakFinishTime(null);
			} else {
				if(hBreakFinishTime.length() == 1) {
					hBreakFinishTime = "0" + hBreakFinishTime;}
				if(mBreakFinishTime.length() == 1) {
					mBreakFinishTime = "0" + mBreakFinishTime;}
				workTime.setBreakFinishTime(LocalTime.parse(hBreakFinishTime + ":" + mBreakFinishTime));
			}
			
			String hFinishTime = request.getParameter("hFinishTime");
			String mFinishTime = request.getParameter("mFinishTime");
			if(hFinishTime.length() == 0 && mFinishTime.length() == 0) {
				workTime.setFinishTime(null);
			} else {
				if(hFinishTime.length() == 1) {
					hFinishTime = "0" + hFinishTime;}
				if(mFinishTime.length() == 1) {
					mFinishTime = "0" + mFinishTime;}
				workTime.setFinishTime(LocalTime.parse(hFinishTime + ":" + mFinishTime));
			}
			
			WorkTimeDAO workTimeDAO = new WorkTimeDAO();
			boolean flag = workTimeDAO.updateWorkTime(emp.getEmpId(), workTime);
			if(flag) {
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM");
				session.setAttribute("request-month", workTime.getWorkDate().format(dateFormat));
				response.sendRedirect("/kintai/SysadminUpdateWorkTime?action=done");
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadminError.jsp");
				dispatcher.forward(request, response);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadminError.jsp");
			dispatcher.forward(request, response);
		}
	}
}
