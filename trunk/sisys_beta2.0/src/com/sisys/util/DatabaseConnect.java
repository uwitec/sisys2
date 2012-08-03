package com.sisys.util;


import java.sql.DriverManager;

import com.mysql.jdbc.Connection;


public class DatabaseConnect {
	private Connection connection = null;
	private MysqlConfig mc = new MysqlConfig();
	public Connection getConnection(){
		try {
			Class.forName(mc.getDriver());
			connection = (Connection) DriverManager.getConnection(mc.getUrl(), mc.getUser(), mc.getPassword());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return connection;
	}
}
