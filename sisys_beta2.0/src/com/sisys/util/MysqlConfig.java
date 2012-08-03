package com.sisys.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

public class MysqlConfig {
	private String driver;
	private String url;
	private String user;
	private String password;
	private String path;
	
	public MysqlConfig(){
		try{
			Properties props = new Properties();
			File paramFile = new File("./src/mysql.ini");
			if(!paramFile.exists()){
				URL url = new URL(DatabaseConnect.class.getClassLoader().getResource("") + "mysql.ini");
				paramFile = new File(url.toURI());
			}
			props.load(new FileInputStream(paramFile));
			driver = props.getProperty("driver");
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");
			path = props.getProperty("path");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public String getDriver(){
		return driver;
	}
	
	public String getUrl(){
		return url;
	}
	
	public String getUser(){
		return user;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getPath(){
		return path;
	}
}
