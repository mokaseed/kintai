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


//従業員ログインチェックフィルターを通る

@WebServlet("/EmpList")
public class EmpList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String action = request.getParameter("action");
		
		
		//EmpListDAOで従業員情報一覧を取得して従業員一覧画面を表示する
		if(action == null) {
			EmpListDAO empListDAO = new EmpListDAO();
			List<Employee> empList = new ArrayList<>();
			empList = empListDAO.selectEmpList();
			
			DeptDAO deptDAO = new DeptDAO();
			List<Dept> deptList = deptDAO.selectDeptList();
			session.setAttribute("deptList", deptList);
			
			if(empList == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/emp/empListError.jsp");
				dispatcher.forward(request, response);
			}
			session.setAttribute("empList", empList);
		
		
		//選択された事業部の従業員情報を表示
		} else if(action.equals("selectDept")) {
			String dept = request.getParameter("dept");
			if(dept != null) {
				EmpListDAO empListDAO = new EmpListDAO();
				List<Employee> empList = empListDAO.selectDeptEmpList(Integer.parseInt(dept, 10));
				session.setAttribute("empList", empList);
				request.setAttribute("nowDept", Integer.parseInt(dept, 10));
			} 
			
		
		//検索にヒットした従業員情報を表示
		} else if(action.equals("search")) {
			String searchWord = request.getParameter("search");
			EmpListDAO empListDAO = new EmpListDAO();
			List<Employee> empList = empListDAO.searchEmpList(searchWord);
			session.setAttribute("empList", empList);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/emp/empList.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//従業員一覧で「詳細」が押された列の従業員情報詳細画面を表示
		//「詳細」ボタンが押された列の判別はListのインデックス番号を使用
		int index = Integer.parseInt(request.getParameter("index"));
		request.setAttribute("index", index);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/emp/empMaster.jsp");
		dispatcher.forward(request, response);
		
	}

}
