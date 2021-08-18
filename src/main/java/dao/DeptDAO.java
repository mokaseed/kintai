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
}
