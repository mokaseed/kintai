package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//DBの接続と切断
public class ConnectionManager {
	final String jdbcId = "b92fbb8338a735";
    final String jdbcPass = "a9972877";
    final String jdbcUrl = "jdbc:mysql://us-cdbr-east-04.cleardb.com/heroku_4781b9d084eb35a";
    
    Connection con = null;
    
    //DB接続
	public Connection connect() {
		
		if(con == null) {
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
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
