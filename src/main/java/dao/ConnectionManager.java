package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//DBの接続と切断
public class ConnectionManager {
	
    Connection con = null;
    
    //DB接続
	public Connection connect() {
		
		try{
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		System.out.println(dbUri);
		final String jdbcId = dbUri.getUserInfo().split(":")[0];
		final String jdbcPass = dbUri.getUserInfo().split(":")[1];
		final String jdbcUrl = "jdbc:mysql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
		
		if(con == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(jdbcUrl, jdbcId, jdbcPass);
			}			
		}catch(SQLException | ClassNotFoundException | URISyntaxException e){
			e.printStackTrace();
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
