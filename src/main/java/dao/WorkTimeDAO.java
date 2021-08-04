package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import entity.WorkTime;

public class WorkTimeDAO {
		final String jdbcId = "root";
	    final String jdbcPass = "seedrose";
	    final String jdbcUrl = "jdbc:mysql://localhost:3306/kintai";
	    
	public List<WorkTime> selectWorkTimeList(int empId, String thisMonth){
	  	
	  	Connection con = null;
	   	PreparedStatement ps = null;
	   	ResultSet rs = null;
	   	
//	   	戻り値の用意
	   	List<WorkTime> workTimeList = new ArrayList<>();
	   	
	   	
//	   	データベースへ接続
	   	try {
	   		Class.forName("com.mysql.jdbc.Driver");
	   		con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
	   		String sql = "SELECT * FROM t_work_time WHERE emp_id = ? AND work_date LIKE ?";
	   		ps = con.prepareStatement(sql);
	   		
	   		ps.setInt(1, empId);
	   		ps.setString(2, thisMonth + "%");	
	   		
	   		rs = ps.executeQuery();
	   		
	   		while(rs.next()) {
	   			WorkTime workTime = new WorkTime();
//	   			見つかったアカウント情報を戻り値にセット
	   			if(rs.getString("emp_id") != null) {
	   				int id = rs.getInt("emp_id");
	   				workTime.setEmpId(id);
	   			}
	   			if(rs.getString("work_date") != null) {
	   				LocalDate workDate = LocalDate.parse(rs.getString("work_date"));
	   				workTime.setWorkDate(workDate);
	   			}
	   			if(rs.getString("start_time") != null) {
	   				LocalTime startTime = LocalTime.parse(rs.getString("start_time"));
	   				workTime.setStartTime(startTime);
	   			}
	   			if(rs.getString("start_cond") != null) {
	   				String startCond = rs.getString("start_cond");
	   				workTime.setStartCond(startCond);
	   			}
	   			if(rs.getString("break_start_time") != null) {
	   				LocalTime breakStartTime = LocalTime.parse(rs.getString("break_start_time"));
	   				workTime.setBreakStartTime(breakStartTime);
	   			}
	   			if(rs.getString("break_finish_time") != null) {
	   				LocalTime breakFinishTime = LocalTime.parse(rs.getString("break_finish_time"));
	   				workTime.setBreakFinishTime(breakFinishTime);
	   			}
	   			if(rs.getString("finish_time") != null) {
	   				LocalTime finishTime = LocalTime.parse(rs.getString("finish_time"));
	   				workTime.setFinishTime(finishTime);
	   			}
	   			if(rs.getString("finish_cond") != null) {
	   				String finishCond = rs.getString("finish_cond");
	   				workTime.setFinishCond(finishCond);
	   			}
	    		workTimeList.add(workTime);
//	    		System.out.println("workTimeListあり");
//	   		} else {
//	   			System.out.println("workTimeListなし");
//	   			return null;
	   		}
	   		return workTimeList;
	   	} catch(SQLException| ClassNotFoundException e) {
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
    	}
	}
}

