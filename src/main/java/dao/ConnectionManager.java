package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	final String jdbcId = "root";
    final String jdbcPass = "seedrose";
    final String jdbcUrl = "jdbc:mysql://localhost:3306/kintai";
    
    Connection con = null;
    
	public Connection connect() {
		
		if(con == null) {
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
				System.out.println("DB接続を行いました");
			}catch(SQLException | ClassNotFoundException e){
				e.printStackTrace();
			}			
		}
		return con;
	}
	
	public void close() {
		if(con != null) {
			try {
				con.close();
				System.out.println("DB切断を行いました");
			} catch (SQLException e) {
			e.printStackTrace();
			}
		}
	}

}
