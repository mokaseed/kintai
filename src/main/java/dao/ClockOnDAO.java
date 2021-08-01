package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClockOnDAO {
	final String jdbcId = "root";
    final String jdbcPass = "seedrose";
    final String jdbcUrl = "jdbc:mysql://localhost:3306/kintai";
    
    Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	LocalDateTime now = LocalDateTime.now();
	DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public boolean setWorkStartTime(int empId, String cond){
    	
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
    		ps = con.prepareStatement("INSERT INTO t_work_time (emp_id, work_date, start_time, start_cond) VALUES (?, ?, ?, ?)");
    		
    		ps.setInt(1, empId);
    		ps.setString(2, now.format(dateFormat));
    		ps.setString(3, now.format(timeFormat));
    		ps.setString(4, cond);
    		
    		int r = ps.executeUpdate();
    		
    		if(r != 0) {
    			System.out.println(r + "件の出勤時刻を登録しました。");
    		} else {
    			System.out.println("該当が0件です。出勤時刻の登録ができませんでした。");
    			return false;
    		}
    		return true;
		} catch(SQLException | ClassNotFoundException e) {
    		e.printStackTrace();
    		System.out.println(e);
    		System.out.println("出勤時刻の登録ができませんでした。");
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
    
public boolean setWorkFinishTime(int empId, String cond){
    	
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
    		ps = con.prepareStatement("UPDATE t_work_time set finish_time = ?, finish_cond = ? WHERE emp_id = ? AND work_date = ?");
    		
    		ps.setString(1, now.format(timeFormat));
    		ps.setString(2, cond);
    		ps.setInt(3, empId);
    		ps.setString(4, now.format(dateFormat));
    		int r = ps.executeUpdate();
    		
    		if(r != 0) {
    			System.out.println(r + "件の退勤時刻を登録しました。");
    		} else {
    			System.out.println("該当が0件です。退勤時刻の登録ができませんでした。");
    			return false;
    		}
    		return true;
		} catch(SQLException | ClassNotFoundException e) {
    		e.printStackTrace();
    		System.out.println(e);
    		System.out.println("退勤時刻の登録ができませんでした。");
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

public boolean setBreakStartTime(int empId){
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
		ps = con.prepareStatement("UPDATE t_work_time set break_start_time = ? WHERE emp_id = ? AND work_date = ?");
		
		ps.setString(1, now.format(timeFormat));
		ps.setInt(2, empId);
		ps.setString(3, now.format(dateFormat));
		int r = ps.executeUpdate();
		
		if(r != 0) {
			System.out.println(r + "件の休憩開始時刻を登録しました。");
		} else {
			System.out.println("該当が0件です。休憩開始時刻の登録ができませんでした。");
			return false;
		}
		return true;
	} catch(SQLException | ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println(e);
		System.out.println("休憩開始時刻の登録ができませんでした。");
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

public boolean setBreakFinishTime(int empId) {
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
		ps = con.prepareStatement("UPDATE t_work_time set break_finish_time = ? WHERE emp_id = ? AND work_date = ?");
		
		ps.setString(1, now.format(timeFormat));
		ps.setInt(2, empId);
		ps.setString(3, now.format(dateFormat));
		int r = ps.executeUpdate();
		
		if(r != 0) {
			System.out.println(r + "件の休憩終了時刻を登録しました。");
		} else {
			System.out.println("該当が0件です。休憩終了時刻の登録ができませんでした。");
			return false;
		}
		return true;
	} catch(SQLException | ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println(e);
		System.out.println("休憩終了時刻の登録ができませんでした。");
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
