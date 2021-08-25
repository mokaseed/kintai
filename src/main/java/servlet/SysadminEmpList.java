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
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession(false);
		
		//従業員一覧情報をDBから取得して管理者用従業員一覧画面を表示
		if(action == null) {
			try {
				EmpListDAO empListDAO = new EmpListDAO();
				List<Employee> empList = new ArrayList<>();
				empList = empListDAO.selectEmpList();
				
				session.setAttribute("empList", empList);
				
				DeptDAO deptDAO = new DeptDAO();
				List<Dept> deptList = deptDAO.selectDeptList();
				session.setAttribute("deptList", deptList);
				
			} catch(Exception e) {
				e.printStackTrace();
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/sysadminError.jsp");
				dispatcher.forward(request, response);		
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/sysadminEmpList.jsp");
			dispatcher.forward(request, response);
		
		
		//選択された事業部の従業員情報を表示
		} else if(action.equals("selectDept")) {
			String dept = request.getParameter("dept");
			if(dept == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/sysadminEmpList.jsp");
				dispatcher.forward(request, response);
			
				
			//「管理者」が選択された場合
			} if(dept.equals("0")) {
				EmpListDAO empListDAO = new EmpListDAO();
				List<Employee> empList = empListDAO.selectSysadminEmpList();
				session.setAttribute("empList", empList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/sysadminEmpList.jsp");
				dispatcher.forward(request, response);
				
				
			//事業部が選択された場合
			} else {
				EmpListDAO empListDAO = new EmpListDAO();
				List<Employee> empList = empListDAO.selectDeptEmpList(Integer.parseInt(dept, 10));
				session.setAttribute("empList", empList);
				request.setAttribute("nowDept", Integer.parseInt(dept, 10));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/sysadminEmpList.jsp");
				dispatcher.forward(request, response);
			}
			
			
		//検索にヒットした従業員情報を表示
		} else if(action.equals("search")) {
			String searchWord = request.getParameter("search");
			EmpListDAO empListDAO = new EmpListDAO();
			List<Employee> empList = empListDAO.searchEmpList(searchWord);
			session.setAttribute("empList", empList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/sysadminEmpList.jsp");
			dispatcher.forward(request, response);
		}
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
			next = "/kintai/SysadminEmpMaster?action=done";
			
		//「勤怠管理詳細」が押された場合
		} else if(action.equals("workTime")) {
			session.setAttribute("index", index);
			next = "/kintai/SysadminWorkTime?action=done";
		}
		response.sendRedirect(next);
	}

}
