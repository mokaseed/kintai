package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmpListDAO;
import entity.Employee;


//ログインチェックフィルターを通る

@WebServlet("/EmpList")
public class EmpList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//EmpListDAOで従業員情報一覧を取得して従業員一覧画面を表示する
		EmpListDAO empListDAO = new EmpListDAO();
		List<Employee> empList = new ArrayList<>();
		empList = empListDAO.selectEmpList();
		
		if(empList != null) {
			HttpSession session = request.getSession();
			session.setAttribute("empList", empList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/empList.jsp");
			dispatcher.forward(request, response);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/empListError.jsp");
		dispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//従業員一覧で「詳細」が押された列の従業員情報詳細画面を表示
		//「詳細」ボタンが押された列の判別はListのインデックス番号を使用
		int index = Integer.parseInt(request.getParameter("index"));
		request.setAttribute("index", index);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/empMaster.jsp");
		dispatcher.forward(request, response);
		
	}

}
