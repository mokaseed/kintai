package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entity.MySchedule;

public class ScheduleDAO {
	
	final String jdbcId = "root";
    final String jdbcPass = "seedrose";
    final String jdbcUrl = "jdbc:mysql://localhost:3306/kintai";
    
  //DBを接続するメソッド
    ConnectionManager connectionManager = new ConnectionManager();
    Connection con = connectionManager.connect();
    
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public List<MySchedule> addSchedule(MySchedule ms){
		
		List<MySchedule> myScheduleList = new ArrayList<>();
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		try {
			String sql = "SELECT * FROM t_skd WHERE skd_id = ?";
			ps = con.prepareStatement(sql);
			if(String.valueOf(ms.getSkdId()) != null) {
				ps.setInt(1, ms.getSkdId());	
			} else {
				ps.setInt(1, -1);
			}
			rs = ps.executeQuery();
			
			if(rs.next()) {
				//スケジュールIDに該当があればUPDATE
				sql = "UPDATE t_skd SET skd_color = ?, skd_date = ?, skd_start_time = ?, skd_finish_time = ?, subject = ?, memo = ? WHERE skd_id = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, ms.getColor());
				ps.setString(2, ms.getSkdDate().format(dateFormat));
				if(ms.getSkdStartTime() != null) {
					ps.setString(3, ms.getSkdStartTime().format(timeFormat));
				} else {
					ps.setTime(3, null);
				}
				if(ms.getSkdFinishTime() != null) {
					ps.setString(4, ms.getSkdFinishTime().format(timeFormat));				
				} else {
					ps.setTime(4, null);
				}
				ps.setString(5, ms.getSubject());
				ps.setString(6, ms.getMemo());
				ps.setInt(7, ms.getSkdId());
				
				int rowcount = ps.executeUpdate();
				if(rowcount != 0) {
					System.out.println("スケジュール上書き登録OK");
				} else {
					System.out.println("スケジュール上書き登録NG");
					return null;
				}
			} else {
				//スケジュールIDが該当なしの場合はINSERT
				sql = "INSERT INTO t_skd (emp_id, skd_color, skd_date, skd_start_time, skd_finish_time, subject, memo) VALUES (?, ?, ?, ?, ?, ?, ?)";
				ps = con.prepareStatement(sql);
				
				ps.setInt(1, ms.getEmpId());
				ps.setString(2, ms.getColor());
				ps.setString(3, ms.getSkdDate().format(dateFormat));
				if(ms.getSkdStartTime() != null) {
					ps.setString(4, ms.getSkdStartTime().format(timeFormat));
				} else {
					ps.setTime(4, null);
				}
				if(ms.getSkdFinishTime() != null) {
					ps.setString(5, ms.getSkdFinishTime().format(timeFormat));				
				} else {
					ps.setTime(5, null);
				}
				ps.setString(6, ms.getSubject());
				ps.setString(7, ms.getMemo());
				int rowcount = ps.executeUpdate();
				
				if(rowcount != 0) {
					System.out.println("スケジュール新規登録OK");
				} else {
					System.out.println("スケジュール新規登録NG");
					return null;
				}
			}
			
			
			sql = "SELECT skd_id, emp_id, skd_color, skd_date, skd_start_time, skd_finish_time, subject, memo FROM t_skd WHERE emp_id = ? ORDER BY skd_start_time"; 
	   		ps = con.prepareStatement(sql);
	   		ps.setInt(1, ms.getEmpId());
	   		rs = ps.executeQuery();
	   		
	   		while(rs.next()) {
	   			MySchedule s_ms = new MySchedule();
//	   			見つかったスケジュール情報を戻り値にセット
	   			if(rs.getString("skd_id") != null) {
	   				int skdId = rs.getInt("skd_id");
	   				s_ms.setSkdId(skdId);
	   			}
	   			if(rs.getString("emp_id") != null) {
	   				int empId = rs.getInt("emp_id");
	   				s_ms.setEmpId(empId);
	   			}
	   			if(rs.getString("skd_color") != null) {
	   				String color = rs.getString("skd_color");
	   				s_ms.setColor(color);
	   			}
	   			if(rs.getString("skd_date") != null) {
	   				LocalDate skdDate  = LocalDate.parse(rs.getString("skd_date"));
	   				s_ms.setSkdDate(skdDate);
	   			}
	   			if(rs.getString("skd_start_time") != null) {
	   				LocalTime skdStartTime = LocalTime.parse(rs.getString("skd_start_time"));
	   				s_ms.setSkdStartTime(skdStartTime);
	   			}
	   			if(rs.getString("skd_finish_time") != null) {
	   				LocalTime skdFinishTime = LocalTime.parse(rs.getString("skd_finish_time"));
	   				s_ms.setSkdFinishTime(skdFinishTime);
	   			}
	   			if(rs.getString("subject") != null) {
	   				String subject = rs.getString("subject");
	   				s_ms.setSubject(subject);
	   			}
	   			if(rs.getString("memo") != null) {
	   				String memo = rs.getString("memo");
	   				s_ms.setMemo(memo);
	   			}
	   			myScheduleList.add(s_ms);
	   		}
		} catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("スケジュール登録ができませんでした");
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
		return myScheduleList;
	}
	
	//スケジュールリストを取得する
	public List<MySchedule> selectScheduleList(int employeeId){
		
		//戻り値の用意
		List<MySchedule> myScheduleList = new ArrayList<>();
		
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		try {
			String sql = "SELECT * FROM t_skd WHERE emp_id = ? ORDER BY skd_start_time"; 
	   		ps = con.prepareStatement(sql);
	   		ps.setInt(1, employeeId);
	   		rs = ps.executeQuery();
	   		
	   		while(rs.next()) {
	   			MySchedule ms = new MySchedule();
//		   			見つかったスケジュール情報を戻り値にセット
	   			if(rs.getString("skd_id") != null) {
	   				int skdId = rs.getInt("skd_id");
	   				ms.setSkdId(skdId);
	   			}
	   			if(rs.getString("emp_id") != null) {
	   				int empId = rs.getInt("emp_id");
	   				ms.setEmpId(empId);
	   			}
	   			if(rs.getString("skd_color") != null) {
	   				String color = rs.getString("skd_color");
	   				ms.setColor(color);
	   			}
	   			if(rs.getString("skd_date") != null) {
	   				LocalDate skdDate  = LocalDate.parse(rs.getString("skd_date"));
	   				ms.setSkdDate(skdDate);
	   			}
	   			if(rs.getString("skd_start_time") != null) {
	   				LocalTime skdStartTime = LocalTime.parse(rs.getString("skd_start_time"));
	   				ms.setSkdStartTime(skdStartTime);
	   			}
	   			if(rs.getString("skd_finish_time") != null) {
	   				LocalTime skdFinishTime = LocalTime.parse(rs.getString("skd_finish_time"));
	   				ms.setSkdFinishTime(skdFinishTime);
	   			}
	   			if(rs.getString("subject") != null) {
	   				String subject = rs.getString("subject");
	   				ms.setSubject(subject);
	   			}
	   			if(rs.getString("memo") != null) {
	   				String memo = rs.getString("memo");
	   				ms.setMemo(memo);
	   			}
	   			myScheduleList.add(ms);
	   		}
		   		System.out.println("スケジュールの登録が完了しました");
			
		} catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("スケジュール登録ができませんでした");
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
		return myScheduleList;
	}

}
