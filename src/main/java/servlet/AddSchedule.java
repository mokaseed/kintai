package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
import entity.MySchedule;
import model.AddScheduleCheck;


@WebServlet("/AddSchedule")
public class AddSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//「予定を追加」を押した時に表示していたカレンダーの年月を取得
		String s_year = request.getParameter("year");
		String s_month = request.getParameter("month");
		
		request.setAttribute("year", s_year);
		request.setAttribute("month", s_month);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addSchedule.jsp");
		dispatcher.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*メモ
		 * 日付がありえない日付の場合は日付エラーになるようにする。日付エラーJSPも作る。
		*/
		
		//予定登録画面で入力された予定をDBへ登録し、カレンダー画面へ遷移
		//年月日を未選択（「ー」を選択）の場合、時分の片方を選択しもう片方を選択していない場合、存在しない日時を選択した場合はエラー画面へ遷移
		
		//追加する予定の各パラメータを取得
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String date = request.getParameter("date");
		String startHour = request.getParameter("startHour");
		String startMinute = request.getParameter("startMinute");
		String finishHour = request.getParameter("finishHour");
		String finishMinute = request.getParameter("finishMinute");
		String color = request.getParameter("color");
		String subject = request.getParameter("subject");
		String memo = request.getParameter("memo");
		
		//入力内容にエラーがないかチェック
		AddScheduleCheck errorCheck = new AddScheduleCheck();
		List<String> errorMsgList = errorCheck.execute(year, month, date, startHour, startMinute, finishHour, finishMinute);
		
		String nextJsp = null;
		//エラーが一つでもある場合はエラー画面へ遷移
		if(errorMsgList.isEmpty() == false) {
			request.setAttribute("errorMsgList", errorMsgList);
			nextJsp = "/WEB-INF/jsp/addScheduleError.jsp";
			
		//エラーがない場合はスケジュールをDBに登録してカレンダー画面へ遷移
		} else {
			HttpSession session = request.getSession(false);
			Employee account = (Employee)session.getAttribute("account");
			MySchedule ms = new MySchedule();
			
			//DBに登録するデータの準備
			//社員ID
			ms.setEmpId(account.getEmpId());
			
			//日付
			if(month.length() == 1) {
				month = "0" + month;
			}
			if(date.length() == 1) {
				date = "0" + date;
			}
			String skdDate = year + "-" + month + "-" + date;
			ms.setSkdDate(LocalDate.parse(skdDate));
			
			//開始時刻
			if(startHour.length() == 0 || startMinute.length() == 0) {
				ms.setSkdStartTime(null);
			} else { 
				if(startHour.length() == 1) {
					startHour = "0" + startHour;
				} 
				if(startMinute.length() == 1) {
					startMinute = "0" + startMinute;
				}
				String skdStartTime = startHour + ":" + startMinute + ":00";
				ms.setSkdStartTime(LocalTime.parse(skdStartTime));
			}
			
			//終了時刻
			if(finishHour.length() == 0 || finishMinute.length() == 0) {
				ms.setSkdStartTime(null);
			} else { 
				if(finishHour.length() == 1) {
					finishHour = "0" + finishHour;
				}
				if(finishMinute.length() == 1) {
					finishMinute = "0" + finishMinute;
				}	
				String skdFinishTime = finishHour + ":" + finishMinute + ":00";
				ms.setSkdFinishTime(LocalTime.parse(skdFinishTime));
			}
			
			//色
			ms.setColor(color);
			
			//件名
			ms.setSubject(subject);
			
			//メモ
			ms.setMemo(memo);
			
			List<MySchedule> myScheduleList = new ArrayList<>();
			ScheduleDAO scheduleDAO = new ScheduleDAO();
			myScheduleList = scheduleDAO.addSchedule(ms);
			
			if(myScheduleList == null) {
				errorMsgList.add("DB登録失敗");
				request.setAttribute("errorMsgList", errorMsgList);
				nextJsp = "/WEB-INF/jsp/addScheduleError.jsp";
			} else {
				session.setAttribute("myScheduleList", myScheduleList);
			}
		}
		
		if(nextJsp != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextJsp);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("/kintai/MyCalendarMain?action=done&year=" + year + "&month=" + month);
		}
		
	}

}
