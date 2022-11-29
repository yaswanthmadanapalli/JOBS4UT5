package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnect {

	private static Connection conn1;

	public static Connection getconn()
	{
		try {
			if(conn1==null) {
				//Class.forName("com.mysql.jdbc.Driver");
				Class.forName("oracle.jdbc.driver.OracleDriver");	
				conn1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","chitti");
				//conn1=DriverManager.getConnection("jdbc:mysql://node7340-env-6169095.sp.skdrive.net/user","root","EEXkfq46278");
				//conn1=DriverManager.getConnection("jdbc:mysql://localhost:3306/chitti","root","chitti");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}


		

		return conn1;
		
	}
	
}
