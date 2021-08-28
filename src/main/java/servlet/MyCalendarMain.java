package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDAO;
import entity.Employee;
import entity.MyCalendar;
import entity.MySchedule;
import model.MyCalendarLogic;


@WebServlet("/MyCalendarMain")
public class MyCalendarMain extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//カレンダーの生成
		String s_year = request.getParameter("year");
		String s_month = request.getParameter("month");
		
		MyCalendarLogic logic = new MyCalendarLogic();
		MyCalendar mc = null;
		if(s_year != null && s_month != null) {
			int year = Integer.parseInt(s_year);
			int month = Integer.parseInt(s_month);
			if(month == 0) {
				month = 12;
				year--;
			}
			if(month == 13){
				month = 1;
				year++;
			}
			//年月指定がある場合は指定の年月のカレンダーを生成
			mc = logic.createMyCalendar(year, month);
		} else {
			//年月指定がない場合は今月のカレンダーを生成
			mc = logic.createMyCalendar();
		}
		
		request.setAttribute("mc", mc);
		
		
		//actionがNULLの場合はDBからスケジュールリストを取得
		String action = request.getParameter("action");
		if(action == null) {
			HttpSession session = request.getSession(false);
			Employee account = (Employee)session.getAttribute("account");
			ScheduleDAO scheduleDAO = new ScheduleDAO();
			List<MySchedule> myScheduleList = scheduleDAO.selectScheduleList(account.getEmpId());
			
			session.setAttribute("myScheduleList", myScheduleList);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/emp/myCalendar.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
