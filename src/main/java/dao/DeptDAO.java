package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Dept;

public class DeptDAO {
	
	final String jdbcId = "root";
    final String jdbcPass = "seedrose";
    final String jdbcUrl = "jdbc:mysql://localhost:3306/kintai";
    
  //DBを接続するメソッド
    ConnectionManager connectionManager = new ConnectionManager();
    Connection con = connectionManager.connect();
    
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//事業部一覧をListで取得
	public List<Dept> selectDeptList() {
		
		//戻り値の用意
		List<Dept> deptList = new ArrayList<>();
		
		try {
    		ps = con.prepareStatement("SELECT * FROM m_dept");
    		rs = ps.executeQuery();
    		
    		while(rs.next()) {
    			int deptId = rs.getInt("dept_id");
    			String deptName = rs.getString("dept_name");
    			Dept dept = new Dept(deptId, deptName);
    			deptList.add(dept);
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
        return deptList;
	}
	
	//事業部を新規登録する
	public boolean registDept(String deptName) {
		try {
			String sql = "INSERT INTO m_dept (dept_name) VALUES (?)";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, deptName);
			int rowcount = ps.executeUpdate();
			
			if(rowcount != 0) {
				System.out.println("事業部新規登録が完了しました");
			} else {
				System.out.println("事業部新規登録ができませんでした");
				return false;
			}
			
		} catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("事業部新規登録ができませんでした");
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
	
	//事業部情報を編集する
	public boolean updateDept(int deptId, String deptName) {
		try {
			String sql = "UPDATE m_dept SET dept_name = ? WHERE dept_id = ?";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, deptName);
			ps.setInt(2, deptId);
			int rowcount = ps.executeUpdate();
			
			if(rowcount != 0) {
				System.out.println("事業部情報の編集が完了しました");
			} else {
				System.out.println("事業部情報の編集ができませんでした");
				return false;
			}
			
		} catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("事業部情報の編集ができませんでした");
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
	
	//事業部情報を削除する
	public boolean deleteDept(int deptId) {
		try {
			String sql = "DELETE FROM m_dept WHERE dept_id = ?";
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, deptId);
			int rowcount = ps.executeUpdate();
			
			if(rowcount != 0) {
				System.out.println("事業部情報の削除が完了しました");
			} else {
				System.out.println("事業部情報の削除ができませんでした");
				return false;
			}
			
		} catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("事業部情報の削除ができませんでした");
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
