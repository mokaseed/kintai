package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Employee;

public class AccountSearchDAO {
	final String jdbcId = "root";
    final String jdbcPass = "seedrose";
    final String jdbcUrl = "jdbc:mysql://localhost:3306/kintai";
    
    @SuppressWarnings("finally")
	public Employee fined(Employee emp){
    	
    	Connection con = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
//    	戻り値の用意
    	Employee account = null;
    	

//    	データベースへ接続
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
    		String sql = "SELECT * FROM m_emp WHERE emp_id = ? AND pass = ?";
    		ps = con.prepareStatement(sql);
    		
    		ps.setInt(1, emp.getEmpId());
    		ps.setString(2, emp.getPass());	
    		
    		rs = ps.executeQuery();
    		
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
    			System.out.println("accountあり");
    		} else {
//    			アカウントがなければnullを返す
    			System.out.println("accountなし");
    			return null;
    		}
    	} catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println(e);
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
    		if(con != null) {
    			try {
    				con.close();
    			} catch (SQLException e) {
    				// TODO 自動生成された catch ブロック
    				e.printStackTrace();
    			}
    		}
            return account;

    	}
    }
}