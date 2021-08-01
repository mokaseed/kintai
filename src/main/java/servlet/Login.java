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
			nextJsp = "empLogin.jsp";
		} else {
			nextJsp = "sysadminLogin.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextJsp);
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int empId = Integer.parseInt(request.getParameter("empId"));
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
				
				//セッションにアカウント情報を登録
				HttpSession session = request.getSession();
				session.setAttribute("account", account);
				//従業員メニュー画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/empMenu.jsp");
				dispatcher.forward(request, response);
			} else {
				//アカウントがヒットしなかった場合
				//エラー画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/empLoginError.jsp");
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
				boolean result = scl.execute(account);
				
				//管理者権限がある場合
				if(result) {
					//セッションにアカウント情報を登録
					HttpSession session = request.getSession();
					session.setAttribute("account", account);
					//管理者メニュー画面へフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadminpMenu.jsp");
					dispatcher.forward(request, response);
				}
				//管理者権限がない場合
				//エラー画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/sysadminLoginError.jsp");
				dispatcher.forward(request, response);
			} else {
				//アカウントがヒットしなかった場合
				//エラー画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/sysadminLoginError.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		
	}

}
