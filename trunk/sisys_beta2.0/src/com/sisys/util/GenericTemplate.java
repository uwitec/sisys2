package com.sisys.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.sisys.bean.mapping.BasicMapping;


public class GenericTemplate {
	    // 定义数据库连接
		private int result;
		private DatabaseConnect databaseConnect;
	    private Connection connection;
	 
	    // 定义sql语句
	    private String sqlValue;
	 
	    // 定义sql语句参数列表
	    private List<Object> values;
	    
	    //定义BeanMapping映射
	    private BasicMapping mapping;
	 
	    private PreparedStatement preparedStatement;
	    private Statement statement;
	    private ResultSet resultSet;
	    /*
	     * 构造函数
	     */
	    public GenericTemplate() {
	    	 databaseConnect = new DatabaseConnect();
		     connection = databaseConnect.getConnection();
		     result = 0;
	    }
	    
	    /**
	     * 设定SQL语句
	     */
	    public void setSqlValue(String sqlValue) {
	        this.sqlValue = sqlValue;
	    }
	 
	    /**
	     * 设定SQL语句的参数列表
	     */
	    public void setValues(List<Object> values) {
	        this.values = values;
	    }
	    
	    /**
	     * 设定BeanMapping映射
	     */
	    public void setMapping(BasicMapping mapping){
	    	this.mapping = mapping;
	    }
	 
	    /**
	     * 将SQL语句参数列表中的值赋给预执行语句.
	     *
	     * @param pstmt
	     *            预执行语句
	     * @param values
	     *            sql语句参数列表
	     * @throws SQLException 
	     */
	    private void setValues(PreparedStatement pstmt, List<Object> values) throws SQLException {
	        // 循环，将SQL语句参数列表中的值依次赋给预执行语句
	    	
	        for (int i = 0; i < values.size(); i++) {
	            Object v = values.get(i);
	            // 注意，setObject()方法的索引值从1开始，所以有i+1
	            pstmt.setObject(i + 1, v);
	        }
	    }
	 
	    /**
	     * 执行查询
	     *
	     * @return a javax.servlet.jsp.jstl.sql.Result
	     *                返回Result对象result
	     * @exception SQLException
	     *                定义sql异常
	     */
	    public ResultSet executeQuery() throws SQLException {
	    	
            if (values != null && values.size() > 0) {
                // 使用预处理语句，并设定所有的sql语句所有参数值
                preparedStatement = (PreparedStatement) connection.prepareStatement(sqlValue);
                setValues(preparedStatement, values);
                //System.out.print(values);
                // 执行查询sql语句，返回查询结果集
                resultSet = preparedStatement.executeQuery();
            } else {
            	System.out.println(sqlValue);
                statement = (Statement) connection.createStatement();
                resultSet = statement.executeQuery(sqlValue);
                //System.out.println(resultSet);
            }
	        return resultSet;
	    }
	 
	    /**
	     * 执行Update语句
	     *
	     * @return numOfRows
	     *                返回受影响的行数
	     * @exception SQLException
	     *                定义sql异常
	     */
	    public int executeUpdate() throws SQLException {
	        
	            if (values != null && values.size() > 0) {
	                // 使用预处理语句，并设定所有的sql语句所有参数值
	            	System.out.println(sqlValue);
	                preparedStatement = (PreparedStatement) connection.prepareStatement(sqlValue);
	                setValues(preparedStatement, values);
	                result = preparedStatement.executeUpdate();
	            } else {
	                // 执行更新sql语句，返回受影响的行数
	            	statement = (Statement) connection.createStatement();
	            	result = statement.executeUpdate(sqlValue);
	            }
	        
	        return result;
	    }
	    public void close() {
		
				try {
					if(resultSet != null) {
						resultSet.close();
						//resultSet = null;
					}
					if(statement != null) {
						statement.close();
						statement = null;
					}
					if(preparedStatement != null) {
						preparedStatement.close();
						preparedStatement = null;
					}
					if(connection != null) {
						connection.close();
						connection = null;
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
				
		}

}
