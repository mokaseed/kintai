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
    		String sql = "SELECT * FROM t_work_time WHERE emp_id = ? AND work_date = ?";
    		ps = con.prepareStatement(sql);
    		ps.setInt(1, empId);
    		ps.setString(2, now.format(dateFormat));
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			//既にその日に何らかの打刻がある場合はUPDATE
    			ps = con.prepareStatement("UPDATE t_work_time SET start_time = ?, start_cond = ? WHERE emp_id = ? AND work_date = ?");
    			ps.setString(1, now.format(timeFormat));
    			ps.setString(2, cond);
    			ps.setInt(3, empId);
    			ps.setString(4, now.format(dateFormat));
    			int r2 = ps.executeUpdate();
    			
    			if(r2 != 0) {
    				System.out.println(r2 + "件の出勤時刻をUPDATE登録しました。");
    				return true;
    			} else {
    				System.out.println("出勤時刻のUPDATE登録に失敗しました。");
    				return false;
    			}
    		} else {
    			//その日初めての打刻の場合はINSERT
    			ps = con.prepareStatement("INSERT INTO t_work_time (emp_id, work_date, start_time, start_cond) VALUES (?, ?, ?, ?)");
    			
    			ps.setInt(1, empId);
    			ps.setString(2, now.format(dateFormat));
    			ps.setString(3, now.format(timeFormat));
    			ps.setString(4, cond);
    			
    			int r1 = ps.executeUpdate();
    			
    			if(r1 != 0) {
    				System.out.println(r1 + "件の出勤時刻をINSERT登録しました。");
    				return true;
    			} else {
    				System.out.println("該当が0件です。出勤時刻のINSERT登録ができませんでした。");
    				return false;
    			}
    		}
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
    		String sql = "SELECT * FROM t_work_time WHERE emp_id = ? AND work_date = ?";
    		ps = con.prepareStatement(sql);
    		ps.setInt(1, empId);
    		ps.setString(2, now.format(dateFormat));
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			//既にその日に何らかの打刻がある場合はUPDATE
    			ps = con.prepareStatement("UPDATE t_work_time SET finish_time = ?, finish_cond = ? WHERE emp_id = ? AND work_date = ?");
    			ps.setString(1, now.format(timeFormat));
    			ps.setString(2, cond);
    			ps.setInt(3, empId);
    			ps.setString(4, now.format(dateFormat));
    			int r2 = ps.executeUpdate();
    			
    			if(r2 != 0) {
    				System.out.println(r2 + "件の退勤時刻をUPDATE登録しました。");
    				return true;
    			} else {
    				System.out.println("退勤時刻のUPDATE登録に失敗しました。");
    				return false;
    			}
    		} else {
    			//その日初めての打刻の場合はINSERT
    			ps = con.prepareStatement("INSERT INTO t_work_time (emp_id, work_date, finish_time, finish_cond) VALUES (?, ?, ?, ?)");
    			
    			ps.setInt(1, empId);
    			ps.setString(2, now.format(dateFormat));
    			ps.setString(3, now.format(timeFormat));
    			ps.setString(4, cond);
    			
    			int r1 = ps.executeUpdate();
    			
    			if(r1 != 0) {
    				System.out.println(r1 + "件の退勤時刻をINSERT登録しました。");
    				return true;
    			} else {
    				System.out.println("該当が0件です。退勤時刻のINSERT登録ができませんでした。");
    				return false;
    			}
    		}
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
    		String sql = "SELECT * FROM t_work_time WHERE emp_id = ? AND work_date = ?";
    		ps = con.prepareStatement(sql);
    		ps.setInt(1, empId);
    		ps.setString(2, now.format(dateFormat));
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			//既にその日に何らかの打刻がある場合はUPDATE
    			ps = con.prepareStatement("UPDATE t_work_time SET break_start_time = ? WHERE emp_id = ? AND work_date = ?");
    			ps.setString(1, now.format(timeFormat));
    			ps.setInt(2, empId);
    			ps.setString(3, now.format(dateFormat));
    			int r2 = ps.executeUpdate();
    			
    			if(r2 != 0) {
    				System.out.println(r2 + "件の休憩開始時刻をUPDATE登録しました。");
    				return true;
    			} else {
    				System.out.println("休憩開始時刻のUPDATE登録に失敗しました。");
    				return false;
    			}
    		} else {
    			//その日初めての打刻の場合はINSERT
    			ps = con.prepareStatement("INSERT INTO t_work_time (emp_id, work_date, break_start_time) VALUES (?, ?, ?)");
    			
    			ps.setInt(1, empId);
    			ps.setString(2, now.format(dateFormat));
    			ps.setString(3, now.format(timeFormat));
    			
    			int r1 = ps.executeUpdate();
    			
    			if(r1 != 0) {
    				System.out.println(r1 + "件の休憩開始時刻をINSERT登録しました。");
    				return true;
    			} else {
    				System.out.println("該当が0件です。休憩開始時刻のINSERT登録ができませんでした。");
    				return false;
    			}
    		}
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
    
public boolean setBreakFinishTime(int empId){
    	
		try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
    		String sql = "SELECT * FROM t_work_time WHERE emp_id = ? AND work_date = ?";
    		ps = con.prepareStatement(sql);
    		ps.setInt(1, empId);
    		ps.setString(2, now.format(dateFormat));
    		rs = ps.executeQuery();
    		
    		if(rs.next()) {
    			//既にその日に何らかの打刻がある場合はUPDATE
    			ps = con.prepareStatement("UPDATE t_work_time SET break_finish_time = ? WHERE emp_id = ? AND work_date = ?");
    			ps.setString(1, now.format(timeFormat));
    			ps.setInt(2, empId);
    			ps.setString(3, now.format(dateFormat));
    			int r2 = ps.executeUpdate();
    			
    			if(r2 != 0) {
    				System.out.println(r2 + "件の休憩終了時刻をUPDATE登録しました。");
    				return true;
    			} else {
    				System.out.println("休憩終了時刻のUPDATE登録に失敗しました。");
    				return false;
    			}
    		} else {
    			//その日初めての打刻の場合はINSERT
    			ps = con.prepareStatement("INSERT INTO t_work_time (emp_id, work_date, break_finish_time) VALUES (?, ?, ?)");
    			
    			ps.setInt(1, empId);
    			ps.setString(2, now.format(dateFormat));
    			ps.setString(3, now.format(timeFormat));
    			
    			int r1 = ps.executeUpdate();
    			
    			if(r1 != 0) {
    				System.out.println(r1 + "件の休憩終了時刻をINSERT登録しました。");
    				return true;
    			} else {
    				System.out.println("該当が0件です。休憩終了時刻のINSERT登録ができませんでした。");
    				return false;
    			}
    		}
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
