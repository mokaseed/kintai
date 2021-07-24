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


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		String nextJsp;
		if(action == "done") {
			nextJsp = "/WEB-INF/empLogin.jsp";
		} else {
			nextJsp = "/WEB-INF/sysadminLogin.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("next");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String action = request.getParameter("action");
		
		//actionがnullの場合は従業員ログイン
		if(action == null || action == "") {
			
			//name,passをEmployeeBeansにセット
			Employee emp = new Employee(name, pass);
			
			//アカウントサーチDAOでアカウントの有無を検索
			//検索したアカウント情報を取得
			AccountSearchDAO asd = new AccountSearchDAO();
			Employee account = asd.fined(emp);
			
			//アカウントがヒットした場合
			if(account != null) {
				//セッションにアカウント情報を登録
				HttpSession session = request.getSession();
				session.setAttribute("account", account);
				//従業員メニュー画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("empMenu");
				dispatcher.forward(request, response);
			} else {
				//アカウントがヒットしなかった場合
				//エラー画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("liginError");
				dispatcher.forward(request, response);
			}
			
			
		}
	}

}
