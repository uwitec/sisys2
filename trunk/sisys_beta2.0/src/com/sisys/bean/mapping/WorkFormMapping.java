package com.sisys.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.sisys.bean.WorkForm;


public class WorkFormMapping extends BasicMapping<WorkForm> {

	@Override
	public WorkForm mapping(ResultSet rs) throws ParseException {
		// TODO Auto-generated method stub
		WorkForm workForm = new WorkForm();
		try {
			workForm.setId(rs.getInt("id"));
			
			workForm.setBatchId(rs.getInt("batchId"));
			workForm.setProcId(rs.getInt("procId"));
			workForm.setQuaNum(rs.getInt("quaNum"));
			workForm.setDisquaNum(rs.getInt("disqNum"));
			workForm.setName(rs.getString("name"));
			
			workForm.setTime(rs.getDate("time"));
			workForm.setIsDelete(rs.getInt("isDelete"));
			workForm.setDeleteTime(rs.getDate("deleteTime"));
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}		

		return workForm;
	}

}