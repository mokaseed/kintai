package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import model.WorkTimeCheck;

//従業員ログインチェックフィルターを通る

@WebServlet("/UpdateWorkTime")
public class UpdateWorkTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//勤怠管理画面にて「修正」ボタンが押された年月日と勤務時刻を取得し、勤務時刻修正画面を表示
		WorkTime workTime = new WorkTime();
		
		HttpSession session = request.getSession(false);
		Employee account = (Employee)session.getAttribute("account");
		workTime.setEmpId(account.getEmpId());
		
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/emp/updateWorkTime.jsp");
		dispatcher.forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//勤務時刻修正画面にて入力された時刻を取得し、DBに上書き登録する
		
		HttpSession session = request.getSession(false);
		Employee account = (Employee)session.getAttribute("account");
		
		String hStartTime = request.getParameter("hStartTime");
		String mStartTime = request.getParameter("mStartTime");
		String hBreakStartTime = request.getParameter("hBreakStartTime");
		String mBreakStartTime = request.getParameter("mBreakStartTime");
		String hBreakFinishTime = request.getParameter("hBreakFinishTime");
		String mBreakFinishTime = request.getParameter("mBreakFinishTime");
		String hFinishTime = request.getParameter("hFinishTime");
		String mFinishTime = request.getParameter("mFinishTime");
		
		//入力された勤務時刻のチェック
		WorkTimeCheck workTimeCheck = new WorkTimeCheck();
		List<String> errorMsgList = workTimeCheck.execute(hStartTime, mStartTime, hBreakStartTime, mBreakStartTime, hBreakFinishTime, mBreakFinishTime, hFinishTime, mFinishTime);
		
		//チェックにてエラーがある場合はエラー画面へ遷移
		if(errorMsgList.isEmpty() == false) {
			request.setAttribute("errorMsgList", errorMsgList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/emp/updateWorkTimeError.jsp");
			dispatcher.forward(request, response);
		} else {
			//DBに登録する値の準備
			WorkTime workTime = new WorkTime();
			
			//日付
			String workDate = request.getParameter("workDate");
			workTime.setWorkDate(LocalDate.parse(workDate));
			
			//出勤時刻
			if(hStartTime.length() == 0 && mStartTime.length() == 0) {
				//時と分がどちらも未入力の場合はnullをセット
				workTime.setStartTime(null);
			} else {
				//1桁の数字が入力された場合は前に0をつける
				if(hStartTime.length() == 1) {
					hStartTime = "0" + hStartTime;}
				if(mStartTime.length() == 1) {
					mStartTime = "0" + mStartTime;}
				workTime.setStartTime(LocalTime.parse(hStartTime + ":" + mStartTime));
			}
			//休憩開始時刻
			if(hBreakStartTime.length() == 0 && mBreakStartTime.length() == 0) {
				//時と分がどちらも未入力の場合はnullをセット
				workTime.setBreakStartTime(null);
			} else {
				//1桁の数字が入力された場合は前に0をつける
				if(hBreakStartTime.length() == 1) {
					hBreakStartTime = "0" + hBreakStartTime;}
				if(mBreakStartTime.length() == 1) {
					mBreakStartTime = "0" + mBreakStartTime;}
				workTime.setBreakStartTime(LocalTime.parse(hBreakStartTime + ":" + mBreakStartTime));
			}
			//休憩終了時刻
			if(hBreakFinishTime.length() == 0 && mBreakFinishTime.length() == 0) {
				//時と分がどちらも未入力の場合はnullをセット
				workTime.setBreakFinishTime(null);
			} else {
				//1桁の数字が入力された場合は前に0をつける
				if(hBreakFinishTime.length() == 1) {
					hBreakFinishTime = "0" + hBreakFinishTime;}
				if(mBreakFinishTime.length() == 1) {
					mBreakFinishTime = "0" + mBreakFinishTime;}
				workTime.setBreakFinishTime(LocalTime.parse(hBreakFinishTime + ":" + mBreakFinishTime));
			}
			//退勤時刻
			if(hFinishTime.length() == 0 && mFinishTime.length() == 0) {
				//時と分がどちらも未入力の場合はnullをセット
				workTime.setFinishTime(null);
			} else {
				//1桁の数字が入力された場合は前に0をつける
				if(hFinishTime.length() == 1) {
					hFinishTime = "0" + hFinishTime;}
				if(mFinishTime.length() == 1) {
					mFinishTime = "0" + mFinishTime;}
				workTime.setFinishTime(LocalTime.parse(hFinishTime + ":" + mFinishTime));
			}
			
			//DBへ登録
			WorkTimeDAO workTimeDAO = new WorkTimeDAO();
			boolean flag = workTimeDAO.updateWorkTime(account.getEmpId(), workTime);
			if(flag) {
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM");
				session.setAttribute("request-month", workTime.getWorkDate().format(dateFormat));
				response.sendRedirect("/kintai/SelectWorkTimeList?action=done");
			} else {
				errorMsgList.add("・勤務時刻修正に失敗しました");
				request.setAttribute("errorMsgList", errorMsgList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/emp/updateWorkTimeError.jsp");
				dispatcher.forward(request, response);
				
			}
		}
	}

}
