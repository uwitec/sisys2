package com.sisys.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.sisys.bean.BackWorkForm;


public class BackWorkFormMapping extends BasicMapping<BackWorkForm> {

	@Override
	public BackWorkForm mapping(ResultSet rs) throws ParseException {
		// TODO Auto-generated method stub
		BackWorkForm backWorkForm = new BackWorkForm();
		try {
			backWorkForm.setProNo(rs.getString("proNo"));
			backWorkForm.setBatchNo(rs.getString("batchNo"));
			backWorkForm.setProcNo(rs.getString("procNo"));
			backWorkForm.setCheckNo(rs.getString("checkNo"));
			backWorkForm.setDeleteTime(rs.getDate("deleteTime"));
			backWorkForm.setId(rs.getInt("id"));
			backWorkForm.setIsDelete(rs.getInt("isDelete"));
			backWorkForm.setKind(rs.getString("kind"));
			backWorkForm.setName(rs.getString("name"));
			backWorkForm.setRespNo(rs.getString("respNo"));
			backWorkForm.setTime(rs.getDate("time"));
			backWorkForm.setStaNo(rs.getString("staNo"));
			backWorkForm.setWorkHours(rs.getDouble("workHours"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}		

		return backWorkForm;
	}

}

