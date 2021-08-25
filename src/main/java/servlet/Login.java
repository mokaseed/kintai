	package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountSearchDAO;
import entity.Employee;
import model.PassCheck;
import model.SysadminCheckLogic;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		//従業員ログイン画面と管理者ログイン画面への遷移振り分け
		String nextJsp;
		if("done".equals(action)) {
			nextJsp = "/WEB-INF/jsp/emp/empLogin.jsp";
		} else {
			nextJsp = "/WEB-INF/jsp/sysadmin/sysadminLogin.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextJsp);
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("empId").replaceAll("[^0-9]", "");
		int empId;
		if(id.length() == 0) {
			empId = 0;
		} else {
			empId = Integer.parseInt(id);
		}
		
		String pass = request.getParameter("pass");
		String action = request.getParameter("action");
		
		//actionがnullの場合は従業員ログイン
		if(action == null || action == "") {
			
			//name,passをEmployeeBeansにセット
			Employee emp = new Employee(empId, pass);
			
			//アカウントサーチDAOでアカウントの有無を検索
			//検索したアカウント情報を取得
			AccountSearchDAO asd = new AccountSearchDAO();
			Employee account = asd.fined(emp);
			
			//アカウントがヒットした場合
			if(account != null) {
				//パスワードのチェック
				PassCheck passCheck = new PassCheck();
				boolean passResult = passCheck.execute(emp, account);
				
				//パスワードが正しい場合
				if(passResult) {
					//セッションにアカウント情報を登録
					HttpSession session = request.getSession();
					session.setAttribute("account", account);
					//従業員メニュー画面へフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/emp/empMenu.jsp");
					dispatcher.forward(request, response);					
				
				//パスワードが誤りの場合
				} else {
					//エラー画面へフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/emp/empLoginError.jsp");
					dispatcher.forward(request, response);
				}
				
			} else {
				//アカウントがヒットしなかった場合
				//エラー画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/emp/empLoginError.jsp");
				dispatcher.forward(request, response);
			}
		//actionがdoneの場合は管理者ログイン
		} else {
			//name,passをEmployeeBeansにセット
			Employee emp = new Employee(empId, pass);
			
			//アカウントサーチDAOでアカウントの有無を検索
			//検索したアカウント情報を取得
			AccountSearchDAO asd = new AccountSearchDAO();
			Employee account = asd.fined(emp);
			
			//アカウントがヒットした場合
			if(account != null) {
				//管理者権限があるか確認
				SysadminCheckLogic scl = new SysadminCheckLogic();
				boolean sysadminResult = scl.execute(account);
				
				//パスワードが正しいか確認
				PassCheck passCheck = new PassCheck();
				boolean passResult = passCheck.execute(emp, account);
				
				//管理者権限があり、パスワードが正しい場合
				if(sysadminResult && passResult) {
					//セッションにアカウント情報を登録
					HttpSession session = request.getSession();
					session.setAttribute("account", account);
					//管理者メニュー画面へフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/sysadminMenu.jsp");
					dispatcher.forward(request, response);
				} else {
				//管理者権限がない、もしくはパスワードが誤りの場合
				//エラー画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/sysadminLoginError.jsp");
				dispatcher.forward(request, response);
				}
			} else {
				//アカウントがヒットしなかった場合
				//エラー画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/sysadminLoginError.jsp");
				dispatcher.forward(request, response);
			}
		}
		
	}

}
