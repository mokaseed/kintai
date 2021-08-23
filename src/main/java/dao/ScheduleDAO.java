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
			String sql = "INSERT INTO t_skd (emp_id, skd_color, skd_date, skd_start_time, skd_finish_time, subject, memo) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
				sql = "SELECT * FROM t_skd WHERE emp_id = ?"; 
		   		ps = con.prepareStatement(sql);
		   		ps.setInt(1, ms.getEmpId());
		   		rs = ps.executeQuery();
		   		
		   		while(rs.next()) {
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
			} else {
				System.out.println("スケジュール登録ができませんでした");
				return null;
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

}
