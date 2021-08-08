package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entity.WorkTime;

public class WorkTimeDAO {
		final String jdbcId = "root";
	    final String jdbcPass = "seedrose";
	    final String jdbcUrl = "jdbc:mysql://localhost:3306/kintai";
	    
	  	Connection con = null;
	   	PreparedStatement ps = null;
	   	ResultSet rs = null;
	    
	public List<WorkTime> selectWorkTimeList(int empId, String thisMonth){
	   	
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
	
	public boolean updateWorkTime(int empId, WorkTime workTime) {
		
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
    		
    		String sql = "SELECT * FROM t_work_time WHERE emp_id = ? AND work_date = ?";
	   		ps = con.prepareStatement(sql);
	   		
	   		ps.setInt(1, empId);
	   		ps.setString(2, workTime.getWorkDate().format(dateFormat));	
	   		
	   		rs = ps.executeQuery();
	   		
	   		if(rs.next()) {
	   			//既にその日に何らかの打刻がある場合はUPDATE
	   			ps = con.prepareStatement("UPDATE t_work_time SET start_time = ?, break_start_time = ?, break_finish_time = ?, finish_time = ? WHERE emp_id = ? AND work_date = ?");
	   			if(workTime.getStartTime() == null) {
	   				ps.setTime(1, null);
	   			} else {
	   				ps.setString(1, workTime.getStartTime().format(timeFormat));
	   			}
	   			if(workTime.getBreakStartTime() == null) {
	   				ps.setTime(2, null);
	   			} else {
	   				ps.setString(2, workTime.getBreakStartTime().format(timeFormat));				
	   			}
	   			if(workTime.getBreakFinishTime() == null) {
	   				ps.setTime(3, null);
	   			} else {
	   				ps.setString(3, workTime.getBreakFinishTime().format(timeFormat));				
	   			}
	   			if(workTime.getFinishTime() == null) {
	   				ps.setTime(4, null);
	   			} else {
	   				ps.setString(4, workTime.getFinishTime().format(timeFormat));				
	   			}
	   			ps.setInt(5, empId);
	   			ps.setString(6, workTime.getWorkDate().format(dateFormat));
	   			int r = ps.executeUpdate();
	   			
	   			if(r != 0) {
	   				System.out.println("勤務時刻修正が完了しました。");
	   				return true;
	   			} else {
	   				System.out.println("勤務時刻修正に失敗しました。");
	   				return false;
	   			}
		   	} else {
	   			ps = con.prepareStatement("INSERT INTO t_work_time (emp_id, work_date, start_time, break_start_time, break_finish_time, finish_time) VALUES (?, ?, ?, ?, ?, ?)");
	   			
	   			ps.setInt(1, empId);
	   			ps.setString(2, workTime.getWorkDate().format(dateFormat));
	   			if(workTime.getStartTime() == null) {
	   				ps.setTime(3, null);
	   			} else {
	   				ps.setString(3, workTime.getStartTime().format(timeFormat));
	   			}
	   			if(workTime.getBreakStartTime() == null) {
	   				ps.setTime(4, null);
	   			} else {
	   				ps.setString(4, workTime.getBreakStartTime().format(timeFormat));				
	   			}
	   			if(workTime.getBreakFinishTime() == null) {
	   				ps.setTime(5, null);
	   			} else {
	   				ps.setString(5, workTime.getBreakFinishTime().format(timeFormat));				
	   			}
	   			if(workTime.getFinishTime() == null) {
	   				ps.setTime(6, null);
	   			} else {
	   				ps.setString(6, workTime.getFinishTime().format(timeFormat));				
	   			}
	   			
	   			int r = ps.executeUpdate();
	   			
	   			if(r != 0) {
	   				System.out.println("勤務時刻修正が完了しました。");
	   				return true;
	   			} else {
	   				System.out.println("勤務時刻修正に失敗しました。");
	   				return false;
	   			}
	   		}

		} catch(SQLException | ClassNotFoundException e) {
    		e.printStackTrace();
   			System.out.println(e);
   			System.out.println("勤務時間の修正ができませんでした。");
   			return false;
   		} finally {
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
	}
}

