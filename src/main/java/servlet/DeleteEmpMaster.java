package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpMasterDAO;

//管理者ログインチェックフィルターを通る

@WebServlet("/DeleteEmpMaster")
public class DeleteEmpMaster extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//従業員情報を削除する
		String empId = request.getParameter("empId");
		boolean flag = false;
		
		EmpMasterDAO empMasterDAO = new EmpMasterDAO();
		if(empId != null) {
		 	flag = empMasterDAO.deleteEmpMaster(Integer.parseInt(empId));
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/sysadminError.jsp");
			dispatcher.forward(request, response);
		}
		
		if(flag == false) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/sysadminError.jsp");
			dispatcher.forward(request, response);
		}
		
		response.sendRedirect("/kintai/SysadminEmpList");
	}

}
