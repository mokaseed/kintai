package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//DBの接続と切断
public class ConnectionManager {
	final String jdbcId = "root";
    final String jdbcPass = "seedrose";
    final String jdbcUrl = "jdbc:mysql://localhost:3306/kintai";
    
    Connection con = null;
    
    //DB接続
	public Connection connect() {
		
		if(con == null) {
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
			}catch(SQLException | ClassNotFoundException e){
				e.printStackTrace();
			}			
		}
		return con;
	}
	
	//DB切断
	public void close() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
			e.printStackTrace();
			}
		}
	}

}
