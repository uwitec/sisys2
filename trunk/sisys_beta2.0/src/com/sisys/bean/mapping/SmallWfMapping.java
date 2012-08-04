package com.sisys.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import com.sisys.bean.SmallWf;

public class SmallWfMapping extends BasicMapping<SmallWf>{

	@Override
	public SmallWf mapping(ResultSet rs) throws ParseException {
		// TODO Auto-generated method stub
		SmallWf smallWf = new SmallWf();
		try {
			
			smallWf.setId(rs.getInt("id"));
			smallWf.setWfId(rs.getInt("wfId"));
			smallWf.setProNo(rs.getString("proNo"));			
			smallWf.setProcId(rs.getInt("proId"));
			smallWf.setQuaNum(rs.getInt("quaNum"));
			smallWf.setDisqDetail(rs.getString("disqDetail"));
			smallWf.setStaNo(rs.getString("staNo"));
			smallWf.setBworkHours(rs.getDouble("bworkHours"));
			smallWf.setSalary(rs.getDouble("salary"));
			smallWf.setgWasteNum(rs.getInt("gWasteNum"));
			smallWf.setlWasteNum(rs.getInt("lWasteNum"));
			smallWf.setTime(rs.getDate("time"));
			
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}		

		return smallWf;
	}

}
