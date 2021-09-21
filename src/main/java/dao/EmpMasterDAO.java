package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Employee;

//従業員情報の追加・修正・削除を行うDAO
public class EmpMasterDAO {
	final String jdbcId = "root";
    final String jdbcPass = "seedrose";
    final String jdbcUrl = "jdbc:mysql://localhost:3306/kintai";
    
    //DBを接続するメソッド
    ConnectionManager connectionManager = new ConnectionManager();
    Connection con = connectionManager.connect();
    
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//従業員情報新規登録
	public boolean empMasterRegist(Employee emp) {
		
		try {
			String sql = "INSERT INTO m_emp (name, dept_id, tel, mail, hire_date, pass, sysadmin, remarks) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, emp.getName());
			ps.setInt(2, emp.getDeptId());
			ps.setString(3, emp.getTel());
			ps.setString(4, emp.getMail());
			ps.setString(5, emp.getHireDate());
			ps.setString(6, emp.getPass());
			ps.setString(7, emp.getSysadmin());
			ps.setString(8, emp.getRemarks());
			int rowcount = ps.executeUpdate();
			
			if(rowcount != 0) {
				System.out.println("従業員新規登録が完了しました");
			} else {
				System.out.println("従業員新規登録ができませんでした");
				return false;
			}
			
		} catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("従業員新規登録ができませんでした");
    		return false;
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
    		//DB接続を切断するメソッド
    		connectionManager.close();
    	}
		return true;
	}
	
	//管理者権限にて従業員情報の修正を行う
	public List<Employee> empMasterUpdate(Employee updateEmp) {
		
		//戻り値の用意
		List<Employee> empList = new ArrayList<>();
		
		try {
			String sql = "UPDATE m_emp SET name = ?, dept_id = ?, tel = ?, mail = ?, hire_date = ?, pass = ?, sysadmin = ?, remarks = ? WHERE emp_id = ?";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, updateEmp.getName());
			ps.setInt(2, updateEmp.getDeptId());
			ps.setString(3, updateEmp.getTel());
			ps.setString(4, updateEmp.getMail());
			ps.setString(5, updateEmp.getHireDate());
			ps.setString(6, updateEmp.getPass());
			ps.setString(7, updateEmp.getSysadmin());
			ps.setString(8, updateEmp.getRemarks());
			ps.setInt(9, updateEmp.getEmpId());
			int updateRowcount = ps.executeUpdate();
			
			if(updateRowcount != 0) {
				sql = "SELECT emp_id, name, pass, dept_name, tel, mail, hire_date, sysadmin, remarks FROM m_emp inner join m_dept on m_emp.dept_id = m_dept.dept_id"; 
		   		ps = con.prepareStatement(sql);
		   		rs = ps.executeQuery();
		   		
		   		while(rs.next()) {
		   			Employee emp = new Employee();
		   			//見つかったアカウント情報を戻り値にセット
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
		   		System.out.println("従業員情報のUPDATEが完了しました");
			} else {
				System.out.println("従業員情報のUPDATEが失敗しました");
				return null;
			}			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("従業員情報のUPDATEが失敗しました");
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
    		//DB接続を切断するメソッド
    		connectionManager.close();
    	}
		return empList;
	}
	
	//個人設定の従業員情報の修正を行う
	public Employee personalSettingsEmpMasterUpdate(Employee emp) {
		
		//戻り値の用意
		Employee account = null;
		
		try {
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
	    			//修正後のアカウント情報を戻り値にセット
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
			
		} catch(SQLException e) {
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
    		//DB接続を切断するメソッド
    		connectionManager.close();
    	}
        return account;
    }
	
	public boolean deleteEmpMaster(int empId){
		try {
			String sql = "DELETE FROM m_emp WHERE emp_id = ?";
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, empId);
			int rowcount = ps.executeUpdate();
			
			if(rowcount != 0) {
				System.out.println("従業員情報の削除が完了しました");
			} else {
				System.out.println("従業員情報の削除ができませんでした");
				return false;
			}
			
		} catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("従業員情報の削除ができませんでした");
    		return false;
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
    		//DB接続を切断するメソッド
    		connectionManager.close();
    	}
		return true;
	}
	
}
