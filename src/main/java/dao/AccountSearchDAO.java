package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Employee;

//ログイン時にアカウントが登録されているか確認するDAO
public class AccountSearchDAO {
    
	public Employee fined(Employee emp){
    	
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
    	//DB接続用のメソッド
    	ConnectionManager connectionManager = new ConnectionManager();
    	Connection con = connectionManager.connect();
    	
    	//戻り値の用意
    	Employee account = null;
    	

    	//ログイン画面にて入力された社員IDがDBに登録済みの場合、当該社員情報を返す
    	try {
    		String sql = "SELECT emp_id, name, pass, dept_name, tel, mail, hire_date, sysadmin, remarks FROM m_emp inner join m_dept on m_emp.dept_id = m_dept.dept_id WHERE emp_id = ?";
    		ps = con.prepareStatement(sql);
    		
    		ps.setInt(1, emp.getEmpId());
    		
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			//見つかったアカウント情報を戻り値にセット
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
    			//アカウントがなければnullを返す
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
    		connectionManager.close();
    	}
        return account;
    }
}