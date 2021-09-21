package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//勤怠打刻に関するDAO
public class ClockOnDAO {
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//DB接続用のメソッド
	ConnectionManager connectionManager = new ConnectionManager();
	Connection con = connectionManager.connect();
	
	LocalDateTime now = LocalDateTime.now();
	DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
	//勤怠打刻で出勤が押された場合の処理
    public boolean setWorkStartTime(int empId, String cond){
		try {
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
    			int updateRowcount = ps.executeUpdate();
    			
    			if(updateRowcount != 0) {
    				System.out.println(updateRowcount + "件の出勤時刻をUPDATE登録しました。");
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
    			
    			int insertRowcount = ps.executeUpdate();
    			
    			if(insertRowcount != 0) {
    				System.out.println(insertRowcount + "件の出勤時刻をINSERT登録しました。");
    				return true;
    			} else {
    				System.out.println("該当が0件です。出勤時刻のINSERT登録ができませんでした。");
    				return false;
    			}
    		}
    	} catch(SQLException e) {
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
   			//DB接続を切断するメソッド
   			connectionManager.close();

   		}
    }
    
    //勤怠打刻で退勤が押された場合の処理
    public boolean setWorkFinishTime(int empId, String cond){
    	
		try {
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
    			int updateRowCount = ps.executeUpdate();
    			
    			if(updateRowCount != 0) {
    				System.out.println(updateRowCount + "件の退勤時刻をUPDATE登録しました。");
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
    			
    			int insertRowCount = ps.executeUpdate();
    			
    			if(insertRowCount != 0) {
    				System.out.println(insertRowCount + "件の退勤時刻をINSERT登録しました。");
    				return true;
    			} else {
    				System.out.println("該当が0件です。退勤時刻のINSERT登録ができませんでした。");
    				return false;
    			}
    		}
    	} catch(SQLException e) {
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
   			//DB接続を切断するメソッド
   			connectionManager.close();

   		}
    }
    //勤怠打刻で休憩開始が押された場合の処理
    public boolean setBreakStartTime(int empId){
    	
		try {
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
    			int updateRowcount = ps.executeUpdate();
    			
    			if(updateRowcount != 0) {
    				System.out.println(updateRowcount + "件の休憩開始時刻をUPDATE登録しました。");
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
    			
    			int insertRowcount = ps.executeUpdate();
    			
    			if(insertRowcount != 0) {
    				System.out.println(insertRowcount + "件の休憩開始時刻をINSERT登録しました。");
    				return true;
    			} else {
    				System.out.println("該当が0件です。休憩開始時刻のINSERT登録ができませんでした。");
    				return false;
    			}
    		}
    	} catch(SQLException e) {
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
   			//DB接続を切断するメソッド
   			connectionManager.close();
   		}
    }
    //勤怠打刻で休憩終了が押された場合の処理
    public boolean setBreakFinishTime(int empId){
    	
		try {
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
    			int updateRowcount = ps.executeUpdate();
    			
    			if(updateRowcount != 0) {
    				System.out.println(updateRowcount + "件の休憩終了時刻をUPDATE登録しました。");
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
    			
    			int insertRowcount = ps.executeUpdate();
    			
    			if(insertRowcount != 0) {
    				System.out.println(insertRowcount + "件の休憩終了時刻をINSERT登録しました。");
    				return true;
    			} else {
    				System.out.println("該当が0件です。休憩終了時刻のINSERT登録ができませんでした。");
    				return false;
    			}
    		}
    	} catch(SQLException e) {
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
   			//DB接続を切断するメソッド
   			connectionManager.close();
   		}
    }
}
