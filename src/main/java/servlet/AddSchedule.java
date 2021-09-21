package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
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

//従業員ログインチェックフィルターを通る

@WebServlet("/AddSchedule")
public class AddSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//「予定を追加」を押した時に表示していたカレンダーの年月を取得
		String action = request.getParameter("action");
		String s_index = request.getParameter("index");
		String s_date = request.getParameter("date");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		MySchedule ms = new MySchedule();
		
		if(s_index != null) {
			int index = Integer.parseInt(s_index);
			HttpSession session = request.getSession(false);
			List<MySchedule> msList = (List<MySchedule>)session.getAttribute("myScheduleList");
			
			ms = msList.get(index);
			String skdDate = String.valueOf(ms.getSkdDate());
			ms.setYear(skdDate.substring(0, 4));
			ms.setMonth(skdDate.substring(5, 7));
			ms.setDate(skdDate.substring(8, 10));
			
			String skdStartTime = String.valueOf(ms.getSkdStartTime());
			if(skdStartTime.equals("null") == false) {
				ms.setStartHour(skdStartTime.substring(0, 2));
				ms.setStartMinute(skdStartTime.substring(3, 5));
			}
			String skdFinishTime = String.valueOf(ms.getSkdFinishTime());
			if(skdFinishTime.equals("null") == false) {
				ms.setFinishHour(skdFinishTime.substring(0, 2));
				ms.setFinishMinute(skdFinishTime.substring(3, 5));
			}
		}
		
		if(s_date != null) {
			ms.setYear(s_date.substring(0, 4));
			ms.setMonth(s_date.substring(5, 7));
			ms.setDate(s_date.substring(8, 10));
		} 
		
		if(year != null) {
			ms.setYear(year);
			if(month.length() == 1) {
				ms.setMonth("0" + month);
			} else {
				ms.setMonth(month);				
			}
		}
		
		request.setAttribute("ms", ms);
		
		if(action != null) {
			//actionがdoneの場合は予定詳細画面を表示
			request.setAttribute("index", s_index);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/emp/schedule.jsp");
			dispatcher.forward(request, response);
		} else {
			//actionがnullの場合は予定編集画面を表示
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/emp/addSchedule.jsp");
			dispatcher.forward(request, response);			
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		//予定を削除
		if(action != null) {
			String s_skdId = request.getParameter("skdId");
			if(s_skdId != null) {
				int skdId = Integer.parseInt(s_skdId);
				ScheduleDAO scheduleDAO = new ScheduleDAO();
				scheduleDAO.deleteSchedule(skdId);
				response.sendRedirect("/kintai/MyCalendarMain");
			}
			
			
		} else {
			//予定登録画面で入力された予定をDBへ登録し、カレンダー画面へ遷移
			//入力内容にエラーがある場合はエラー画面へ遷移
			
			//追加する予定の各パラメータを取得
			String skdId = request.getParameter("skdId");
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
				nextJsp = "/WEB-INF/jsp/emp/addScheduleError.jsp";
				
			//エラーがない場合はスケジュールをDBに登録してカレンダー画面へ遷移
			} else {
				HttpSession session = request.getSession(false);
				Employee account = (Employee)session.getAttribute("account");
				MySchedule ms = new MySchedule();
				
				//DBに登録するデータの準備
				
				//スケジュールID
				if(skdId != null) {
					ms.setSkdId(Integer.parseInt(skdId));
				} else {
					ms.setSkdId(-1);
				}
				
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
					ms.setSkdFinishTime(null);
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
				
				ScheduleDAO scheduleDAO = new ScheduleDAO();
				List<MySchedule> myScheduleList = scheduleDAO.addSchedule(ms);
				
				if(myScheduleList == null) {
					errorMsgList.add("DB登録失敗");
					request.setAttribute("errorMsgList", errorMsgList);
					nextJsp = "/WEB-INF/jsp/emp/addScheduleError.jsp";
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

}
