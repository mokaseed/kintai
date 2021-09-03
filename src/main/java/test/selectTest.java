package test;

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
import entity.MyCalendar;
import entity.MySchedule;
import model.MyCalendarLogic;

/**
 * Servlet implementation class selectTest
 */
@WebServlet("/selectTest")
public class selectTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MyCalendarLogic logic = new MyCalendarLogic();
		MyCalendar mc = logic.createMyCalendar(2021, 8);
		
		request.setAttribute("mc", mc);
		
		HttpSession session = request.getSession(false);
//		Employee account = (Employee)session.getAttribute("account");
		ScheduleDAO scheduleDAO = new ScheduleDAO();
		List<MySchedule> myScheduleList = scheduleDAO.selectScheduleList(1);
		
		session.setAttribute("myScheduleList", myScheduleList);

	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/test.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
