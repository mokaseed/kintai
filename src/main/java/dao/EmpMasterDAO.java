package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Employee;

public class EmpMasterDAO {
	final String jdbcId = "root";
    final String jdbcPass = "seedrose";
    final String jdbcUrl = "jdbc:mysql://localhost:3306/kintai";
    
    Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public void empMasterRegist() {
		
	}
	
	public void empMasterUpdate() {
		
	}
	
	//個人設定の従業員情報の修正を行う
	public Employee personalSettingsEmpMasterUpdate(Employee emp) {
		
		//戻り値の用意
		Employee account = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
    		String sql = "UPDATE m_emp SET tel = ?, mail = ?, pass = ?, remarks = ? WHERE emp_id = ?";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, emp.getTel());
			ps.setString(2, emp.getMail());
			ps.setString(3, emp.getPass());
			ps.setString(4, emp.getRemarks());
			ps.setInt(5, emp.getEmpId());
			int updateRowcount = ps.executeUpdate();
			
			if(updateRowcount != 0) {
				ps = con.prepareStatement("SELECT emp_id, name, pass, dept_name, tel, mail, hire_date, sysadmin, remarks FROM m_emp inner join m_dept on m_emp.dept_id = m_dept.dept_id WHERE emp_id = ?");
	    		ps.setInt(1, emp.getEmpId());
	    		rs = ps.executeQuery();
	    		
	    		if(rs.next()) {
//	    			修正後のアカウント情報を戻り値にセット
	    			int empId = rs.getInt("emp_id");
	    			String name = rs.getString("name");
	    			String pass = rs.getString("pass");
	    			String deptName = rs.getString("dept_name");
	    			String tel = rs.getString("tel");
	    			String mail = rs.getString("mail");
	    			String hireDate = rs.getString("hire_date");
	    			String sysadmin = rs.getString("sysadmin");
	    			String remarks = rs.getString("remarks");
	    			account = new Employee(empId, name, pass, deptName, tel, mail, hireDate, sysadmin, remarks);
					System.out.println("personalSettingsEmpMasterUpdateのUPDATEが成功しました");
	    		}
			} else {
				return null;
			}
			
		} catch(SQLException | ClassNotFoundException e) {
    		e.printStackTrace();
    		return null;
    	} finally {
    		if(rs != null) {
    			try {
    				rs.close();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    		if(ps != null) {
    			try {
    				ps.close();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    		if(con != null) {
    			try {
    				con.close();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    	}
        return account;
    }
}
