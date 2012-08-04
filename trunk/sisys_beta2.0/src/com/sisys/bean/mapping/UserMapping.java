package com.sisys.bean.mapping;



import java.sql.ResultSet;
import java.sql.SQLException;

import com.sisys.bean.User;

public class UserMapping extends BasicMapping<User>{

	@Override
	public User mapping(ResultSet rs)  {
		
		User user = new User();
		try {
				
				user.setId(rs.getInt("Id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setIsDelete(rs.getInt("isDelete"));
				user.setDeleteTime(rs.getDate("deleteTime"));
				user.setLevel(rs.getInt("level"));
			
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return user;
	}

}
