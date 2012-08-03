package com.sisys.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sisys.bean.Flowpath;


public class FlowpathMapping extends BasicMapping<Flowpath> {

	@Override
	public Flowpath mapping(ResultSet rs) {

		Flowpath flowpath = new Flowpath();
		try {
			flowpath.setDeleteTime(rs.getDate("deleteTime"));
			flowpath.setId(rs.getInt("Id"));
			flowpath.setIsDelete(rs.getInt("isDelete"));
			flowpath.setProId(rs.getInt("proId"));
			flowpath.setSequence(rs.getString("sequence"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return flowpath;
	}

}
