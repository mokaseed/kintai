package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Employee;

public class AccountSearchDAO {
	final String jdbcId = "root";
    final String jdbcPass = "";
    final String jdbcUrl = "";
    
    public Employee fined(Employee emp) {
    	
//    	戻り値の用意
    	Employee account;
    	
//    	データベースへ接続
    	try (Connection con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass)) {
    		String sql = "SELECT * FROM m_emp WHERE name = ? AND pass = ?";
    		PreparedStatement ps = con.prepareStatement(sql);
    		
    		ps.setString(1, emp.getName());
    		ps.setString(2, emp.getPass());
    		
    		ResultSet rs = ps.executeQuery();
    		
    		if(rs.next()) {
//    			見つかったアカウント情報を戻り値にセット
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
    		} else {
//    			アカウントがなければnullを返す
    			return null;
    		}
    	} catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println(e);
    		return null;
    	}
    	return account;
    }
}
