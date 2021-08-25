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

import dao.EmpMasterDAO;
import entity.Dept;
import entity.Employee;
import model.DateCheck;
import model.DeptCheck;

//管理者ログインチェックフィルターを通る

@WebServlet("/SysadminEmpMaster")
public class SysadminEmpMaster extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		
		//従業員情報修正画面へ
		if(action == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/sysadminEditEmpMaster.jsp");
			dispatcher.forward(request, response);
			
			
		//従業員情報詳細画面へ
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/sysadminEmpMaster.jsp");
			dispatcher.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//従業員新規登録にて入力された値を取得
			String empId = request.getParameter("empId");
			String name = request.getParameter("name").strip();
			String deptName = request.getParameter("deptName");
			String tel = request.getParameter("tel").replaceAll("[^0-9]","");
			String mail = request.getParameter("mail");
			String hireDate = request.getParameter("hireDate");
			String pass = request.getParameter("pass");
			String passCheck = request.getParameter("passCheck");
			String sysadmin = request.getParameter("sysadmin");
			String remarks = request.getParameter("remarks");
			
			//入力した二つのパスワードの一致確認
			if(pass.equals(passCheck) == false) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/passwordError.jsp");
				dispatcher.forward(request, response);
			}
			
			//入社日の表記確認
			DateCheck dateCheck = new DateCheck();
			boolean flag = dateCheck.execute(hireDate);
			if(flag == false) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/addEmpMasterError.jsp");
				dispatcher.forward(request, response);
			}
			
			//DBに登録するデータの準備
			Employee emp = new Employee();
			HttpSession session = request.getSession(false);
			
			//社員ID
			emp.setEmpId(Integer.parseInt(empId, 10));
			
			//氏名
			emp.setName(name);
			
			//事業部
			DeptCheck deptCheck = new DeptCheck();
			List<Dept> deptList = (List<Dept>)session.getAttribute("deptList");
			int deptId = deptCheck.execute(deptName, deptList);
			emp.setDeptId(deptId);
			
			//電話番号
			if(tel == null || tel.length() == 0) {
				emp.setTel(null);
			} else {
				emp.setTel(tel);
			}
			
			//メールアドレス
			if(mail == null || mail.length() == 0) {
				emp.setMail(null);
			} else {
				emp.setMail(mail);
			}
			
			//入社日
			if(hireDate == null || hireDate.length() == 0) {
				emp.setHireDate(null);
			} else {
				emp.setHireDate(hireDate);
			}
			
			//パスワード
			emp.setPass(pass);
			
			//管理者権限（管理者権限がない場合はnullをセット）
			System.out.println(sysadmin);
			if(sysadmin.equals("0")) {
				emp.setSysadmin(null);
			} else {
				emp.setSysadmin(sysadmin);
			}
			
			//備考
			if(remarks == null || remarks.length() == 0) {
				emp.setRemarks(null);
			} else {
				emp.setRemarks(remarks);
			}
			
			EmpMasterDAO empMasterDAO = new EmpMasterDAO();
			List<Employee> empList = new ArrayList<>();
			empList = empMasterDAO.empMasterUpdate(emp);
			
			session.setAttribute("empList", empList);
			
			if(empList == null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/addEmpMasterError.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/sysadminEmpMaster.jsp");
				dispatcher.forward(request, response);
			}
			
		} catch(Exception e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sysadmin/addEmpMasterError.jsp");
			dispatcher.forward(request, response);
		}
	}

}
