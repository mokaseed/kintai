package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DeptDAO;
import dao.EmpMasterDAO;
import entity.Dept;
import entity.Employee;
import model.DateCheck;
import model.DeptCheck;

//管理者ログインチェックフィルターを通る

@WebServlet("/AddEmpMaster")
public class AddEmpMaster extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//DBから事業部一覧を取得して従業員新規登録画面へ
		DeptDAO deptDAO = new DeptDAO();
		List<Dept> deptList = deptDAO.selectDeptList();
		
		String nextJsp = null;
		if(deptList != null) {
			HttpSession session = request.getSession(false);
			session.setAttribute("deptList", deptList);
			nextJsp ="/WEB-INF/jsp/addEmpMaster.jsp";
		} else {
			nextJsp ="/WEB-INF/jsp/sysadminError.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextJsp);
		dispatcher.forward(request, response);	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		//従業員新規登録の入力画面で入力された内容を確認画面に表示させる
		if(action == null) {
			try {
			//従業員新規登録にて入力された値を取得
			String lastName = request.getParameter("lastName").strip();
			String firstName = request.getParameter("firstName").strip();
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
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/passwordError.jsp");
				dispatcher.forward(request, response);
			}
			
			//入社日の表記確認
			DateCheck dateCheck = new DateCheck();
			boolean flag = dateCheck.execute(hireDate);
			if(flag == false) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addEmpMasterError.jsp");
				dispatcher.forward(request, response);
			}
			
			Map<String, String> addEmp = new HashMap<>();
			addEmp.put("lastName", lastName);
			addEmp.put("firstName", firstName);
			addEmp.put("deptName", deptName);
			addEmp.put("tel", tel);
			addEmp.put("mail", mail);
			addEmp.put("hireDate", hireDate);
			addEmp.put("pass", pass);
			addEmp.put("sysadmin", sysadmin);
			addEmp.put("remarks", remarks);
			
			HttpSession session = request.getSession(false);
			session.setAttribute("addEmp", addEmp);
			
			} catch(Exception e) {
				e.printStackTrace();
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addEmpMasterError.jsp");
				dispatcher.forward(request, response);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addEmpMasterConfirm.jsp");
			dispatcher.forward(request, response);
		
				
		//従業員新規登録入力内容確認画面で「登録」が押された場合の処理
		//入力内容をDBに新規登録する
		} else {
			//DBに登録するデータの準備
			Employee emp = new Employee();
			HttpSession session = request.getSession(false);
			Map<String, String> addEmp = (Map<String, String>)session.getAttribute("addEmp");
			
			//名前（姓と名を繋げる）
			String name = addEmp.get("lastName") + addEmp.get("firstName");
			emp.setName(name);
			
			//事業部（事業部IDに変換する）
			DeptCheck deptCheck = new DeptCheck();
			List<Dept> deptList = (List<Dept>)session.getAttribute("deptList");
			int deptId = deptCheck.execute(addEmp.get("deptName"), deptList);
			emp.setDeptId(deptId);
			
			//電話番号
			String tel = addEmp.get("tel");
			if(tel == null || tel.length() == 0) {
				emp.setTel(null);
			} else {
				emp.setTel(tel);
			}
			
			//メールアドレス
			String mail = addEmp.get("mail");
			if(mail == null || mail.length() == 0) {
				emp.setMail(null);
			} else {
				emp.setMail(mail);
			}
			
			//入社日
			String hireDate = addEmp.get("hireDate");
			if(hireDate == null || hireDate.length() == 0) {
				emp.setHireDate(null);
			} else {
				emp.setHireDate(hireDate);
			}
			
			//パスワード
			emp.setPass(addEmp.get("pass"));
			
			//管理者権限（管理者権限がない場合はnullをセット）
			String sysadmin = addEmp.get("sysadmin");
			System.out.println(sysadmin);
			if(sysadmin.equals("0")) {
				emp.setSysadmin(null);
			} else {
				emp.setSysadmin(sysadmin);
			}
			
			//備考
			String remarks = addEmp.get("remarks");
			if(remarks == null || remarks.length() == 0) {
				emp.setRemarks(null);
			} else {
				emp.setRemarks(remarks);
			}
			
			EmpMasterDAO empMasterDAO = new EmpMasterDAO();
			boolean flag = empMasterDAO.empMasterRegist(emp);
			
			if(flag == false) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addEmpMasterError.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addEmpMasterOK.jsp");
				dispatcher.forward(request, response);
			}
			
		}
	}

}
