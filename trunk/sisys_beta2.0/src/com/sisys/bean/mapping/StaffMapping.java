package com.sisys.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sisys.bean.Staff;

public class StaffMapping extends BasicMapping<Staff>{

	@Override
	public Staff mapping(ResultSet rs) {

		Staff Staff = new Staff();
		
		try {			
			Staff.setId(rs.getInt("Id"));
			Staff.setDeptId(rs.getInt("deptId"));
			Staff.setKind(rs.getString("kind"));
			Staff.setStaName(rs.getString("staName"));
			Staff.setStaNo(rs.getString("staNo"));			
			Staff.setIsDelete(rs.getInt("isDelete"));
			Staff.setDeleteTime(rs.getDate("deleteTime"));

		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return Staff;
	}

}
