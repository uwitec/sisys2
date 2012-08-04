package com.sisys.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sisys.bean.StaffKind;

public class StaffKindMapping extends BasicMapping<StaffKind>{

	@Override
	public StaffKind mapping(ResultSet rs) {

		StaffKind StaffKind = new StaffKind();
		
		try {			
			StaffKind.setId(rs.getInt("Id"));
			StaffKind.setKindDesc(rs.getString("kindDesc"));
			StaffKind.setIsDelete(rs.getInt("isDelete"));
			StaffKind.setDeleteTime(rs.getDate("deleteTime"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return StaffKind;
	}

}
