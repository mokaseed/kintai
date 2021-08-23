package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.MyCalendar;
import model.MyCalendarLogic;


@WebServlet("/MyCalendarMain")
public class MyCalendarMain extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/myCalendar.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
