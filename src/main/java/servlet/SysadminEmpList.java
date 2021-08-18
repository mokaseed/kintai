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

import dao.DeptDAO;
import dao.EmpListDAO;
import entity.Dept;
import entity.Employee;

@WebServlet("/SysadminEmpList")
public class SysadminEmpList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//管理者ログインチェックフィルターを通る

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//従業員一覧情報をDBから取得して管理者用従業員一覧画面を表示
		try {
			EmpListDAO empListDAO = new EmpListDAO();
			List<Employee> empList = new ArrayList<>();
			empList = empListDAO.selectEmpList();
			
			HttpSession session = request.getSession();
			session.setAttribute("empList", empList);
			
			DeptDAO deptDAO = new DeptDAO();
			List<Dept> deptList = deptDAO.selectDeptList();
			session.setAttribute("deptList", deptList);
			
		} catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadminError.jsp");
			dispatcher.forward(request, response);		
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadminEmpList.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//「従業員新規登録」「従業員情報詳細」「勤怠管理詳細」のどのボタンが押されたか判別
		String action = request.getParameter("action");
		String index = request.getParameter("index");
		HttpSession session = request.getSession(false);
		String next = null;
		
		//「従業員新規登録」が押された場合
		if(action.equals("addEmp")) {
			next = "/kintai/AddEmpMaster";
			
		//「従業員情報詳細」が押された場合
		} else if(action.equals("empMaster")) {
			session.setAttribute("index", index);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadminEmpMaster.jsp");
			dispatcher.forward(request, response);
			
		//「勤怠管理詳細」が押された場合
		} else if(action.equals("workTime")) {
			session.setAttribute("index", index);
			next = "/kintai/SysadminWorkTime";
		}
		response.sendRedirect(next);
	}

}
