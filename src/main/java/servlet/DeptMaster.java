package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DeptDAO;
import entity.Dept;


//管理者ログインチェックフィルターを通る

@WebServlet("/DeptMaster")
public class DeptMaster extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		//事業部管理画面へ
		if(action == null) {
			DeptDAO deptDAO = new DeptDAO();
			List<Dept> deptList = deptDAO.selectDeptList();
			HttpSession session = request.getSession(false);
			session.setAttribute("deptList", deptList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/deptList.jsp");
			dispatcher.forward(request, response);			
		
		//事業部新規登録画面へ
		} else if(action.equals("regist")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/addDept.jsp");
			dispatcher.forward(request, response);	
		
		//事業部編集画面へ
		} else if(action.equals("edit")) {
			String index = request.getParameter("index");
			request.setAttribute("index", index);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/editDept.jsp");
			dispatcher.forward(request, response);	
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		DeptDAO deptDAO = new DeptDAO();
		boolean flag = false;
		
		//事業部新規登録
		if(action.equals("regist")) {
			String deptName = request.getParameter("deptName");
			
			if(deptName.length() != 0) {
				flag = deptDAO.registDept(deptName);				
			}
			
			if(flag == false) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/sysadminError.jsp");
				dispatcher.forward(request, response);
			}
			
		//事業部編集
		} else if(action.equals("edit")) {
			String deptName = request.getParameter("deptName");
			String deptId = request.getParameter("deptId");
			
			if(deptName.length() != 0) {
				flag = deptDAO.updateDept(Integer.parseInt(deptId), deptName);
			}
			
			if(flag == false) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/sysadminError.jsp");
				dispatcher.forward(request, response);
			}
			
			
		//事業部削除
		} else if(action.equals("delete")) {
			String deptId = request.getParameter("deptId");
			
			//エラー文言List
			List<String> errorMsgList = null;
			
			if(deptId != null) {
				errorMsgList = deptDAO.deleteDept(Integer.parseInt(deptId));
			}
			
			
			//エラーがある場合はエラー画面へ
			if(errorMsgList.isEmpty() == false || errorMsgList == null) {
				request.setAttribute("errorMsgList", errorMsgList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/deptDeleteError.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/DeptMaster");
	}

}
