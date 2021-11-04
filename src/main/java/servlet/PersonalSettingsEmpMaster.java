package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmpMasterDAO;
import entity.Employee;

//従業員ログインチェックフィルターを通る

@WebServlet("/PersonalSettingsEmpMaster")
public class PersonalSettingsEmpMaster extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String nextJsp;
		if(action == null) {
			//個人設定＿従業員情報画面へ
			nextJsp = "/WEB-INF/jsp/emp/personalSettingsEmpMaster.jsp";
		} else {
			//個人設定＿従業員情報修正画面へ
			nextJsp = "/WEB-INF/jsp/emp/updatePersonalSettingsEmpMaster.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextJsp);
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tel = request.getParameter("tel");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		String passCheck = request.getParameter("passCheck");
		String remarks = request.getParameter("remarks");
		
		HttpSession session = request.getSession();
		Employee account = (Employee)session.getAttribute("account");
		
		String nextJsp = null;
		
		//入力された二つのパスワードが一致しているか確認
		//一致している場合は修正内容をDBにUPDATE。不一致の場合はエラー画面に遷移。
		if(pass.equals(passCheck)) {
			Employee emp = new Employee(account.getEmpId(), tel, mail, pass, remarks);
			EmpMasterDAO empMasterDAO = new EmpMasterDAO();
			Employee revisedAccount = empMasterDAO.personalSettingsEmpMasterUpdate(emp);
			
			//DBのUPDATEが成功したら修正後の情報を表示
			//DBのUPDATEが失敗したらエラー画面へ
			if(revisedAccount != null) {
				session.setAttribute("account", revisedAccount);
				nextJsp = "/WEB-INF/jsp/emp/personalSettingsEmpMaster.jsp";
			} else {
				nextJsp = "/WEB-INF/jsp/emp/updatePersonalSettingsEmpMasterError.jsp";
			}
			
		} else {
			nextJsp = "/WEB-INF/jsp/emp/passwordError.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextJsp);
		dispatcher.forward(request, response);
		
	}
}
