package com.scanit.server.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MySQLHelper {
	
	
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		ResourceBundle rb=ResourceBundle.getBundle("com/scanit/shared/db");
		String url=rb.getString("mysqlUrl");
		String userName=rb.getString("mysqlUserName");
		String password=rb.getString("mysqlPassword");
		String driver=rb.getString("mysqlDriver");
		
		Class.forName(driver);
		return DriverManager.getConnection(url, userName, password);
		
        		
		
	}
	

}
