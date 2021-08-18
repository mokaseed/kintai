package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Employee;


public class EmpListDAO {
	final String jdbcId = "root";
    final String jdbcPass = "seedrose";
    final String jdbcUrl = "jdbc:mysql://localhost:3306/kintai";
    
    //DBを接続するメソッド
    ConnectionManager connectionManager = new ConnectionManager();
    Connection con = connectionManager.connect();
    
   	PreparedStatement ps = null;
   	ResultSet rs = null;
	
	public List<Employee> selectEmpList(){
		
		//戻り値の用意
		List<Employee> empList = new ArrayList<>();
		
		try {
	   		String sql = "SELECT emp_id, name, pass, dept_name, tel, mail, hire_date, sysadmin, remarks FROM m_emp inner join m_dept on m_emp.dept_id = m_dept.dept_id"; 
	   		ps = con.prepareStatement(sql);
	   		rs = ps.executeQuery();
	   		
	   		while(rs.next()) {
	   			Employee emp = new Employee();
//	   			見つかったアカウント情報を戻り値にセット
	   			if(rs.getString("emp_id") != null) {
	   				int id = rs.getInt("emp_id");
	   				emp.setEmpId(id);
	   			}
	   			if(rs.getString("name") != null) {
	   				String name = rs.getString("name");
	   				emp.setName(name);
	   			}
	   			if(rs.getString("pass") != null) {
	   				String pass = rs.getString("pass");
	   				emp.setPass(pass);
	   			}
	   			if(rs.getString("dept_name") != null) {
	   				String deptName = rs.getString("dept_name");
	   				emp.setDeptName(deptName);
	   			}
	   			if(rs.getString("tel") != null) {
	   				String tel = rs.getString("tel");
	   				emp.setTel(tel);
	   			}
	   			if(rs.getString("mail") != null) {
	   				String mail = rs.getString("mail");
	   				emp.setMail(mail);
	   			}
	   			if(rs.getString("hire_date") != null) {
	   				String hireDate = rs.getString("hire_date");
	   				emp.setHireDate(hireDate);
	   			}
	   			if(rs.getString("sysadmin") != null) {
	   				String sysadmin = rs.getString("sysadmin");
	   				emp.setSysadmin(sysadmin);
	   			}
	   			if(rs.getString("remarks") != null) {
	   				String remarks = rs.getString("remarks");
	   				emp.setRemarks(remarks);
	   			}
	   			empList.add(emp);
	   		}
   		} catch(SQLException e) {
   			e.printStackTrace();
   			return null;
    	} finally {
    		if(rs != null) {
    			try {
    				rs.close();
    			} catch (SQLException e) {
    				// TODO 自動生成された catch ブロック
    				e.printStackTrace();
    			}
    		}
    		if(ps != null) {
    			try {
    				ps.close();
    			} catch (SQLException e) {
    				// TODO 自動生成された catch ブロック
    				e.printStackTrace();
    			}
    		}
    		//DB接続を切断するメソッド
    		connectionManager.close();
    	}
		return empList;
	}
}
