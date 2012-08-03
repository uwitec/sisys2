package com.sisys.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sisys.bean.Processes;

public class ProcessesMapping extends BasicMapping<Processes> {

	@Override
	public Processes mapping(ResultSet rs)  {
		
		Processes process = new Processes();
		try {
			process.setColorNo(rs.getString("colorNo"));
			process.setDeleteTime(rs.getDate("deleteTime"));
			process.setId(rs.getInt("Id"));
			process.setIsDelete(rs.getInt("isDelete"));
			process.setProcName(rs.getString("procName"));
			process.setProcNo(rs.getString("procNo"));
			process.setUnitCost(rs.getDouble("unitCost"));
			process.setUnitOutput(rs.getInt("unitOutput"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return process;
	}

}
