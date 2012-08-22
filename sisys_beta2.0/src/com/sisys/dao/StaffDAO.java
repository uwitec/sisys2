package com.sisys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sisys.bean.Staff;
import com.sisys.bean.mapping.StaffMapping;
import com.sisys.util.GenericQueryImpl;
import com.sisys.util.GenericTemplate;

public class StaffDAO extends GenericQueryImpl<Staff, StaffMapping> {

	//GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<Staff> list;
	static StaffMapping staffMapping = new StaffMapping();
	final String[] checkStaffNo={"10001","10002","10004","10005","10008","12055","13082",
			"20001","20002","21004","22019",
			"30001","30002","30003","30004","30005","30008","300010",
			"40001","40005","40006","41006","42027","43049","44071",
			"50001","50002","51005","52053",
			"60001","60002","61013","62036","63063","64114"};
	
	/**
	 * 构造函数
	 */
	public StaffDAO() {
		super(Staff.class, staffMapping);
		//genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<Staff>();
	}
	
	public int create(Staff entity) {
		// TODO Auto-generated method stub
		sql = "insert into staff values (?,?,?,?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getDeptId());
		value.add(entity.getKind());
		value.add(entity.getStaName());
		value.add(entity.getStaNo());
		value.add(entity.getIsDelete());
		value.add(entity.getDeleteTime());
		value.add(entity.getIsValidater());
		
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			result = genericTemplate.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		
		return result;
	}

	public int delete(Staff entity) {
		// TODO Auto-generated method stub
		sql = "update staff set isDelete=?,deleteTime=? where id=?";
		value.add(1);
		value.add(new Date());
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			result = genericTemplate.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}

		return result;
	}

	public int update(Staff entity) {
		// TODO Auto-generated method stub
		sql = "update staff set deptId=?,kind=?,staName=?,staNo=?,isDelete=?,deleteTime=?,isValidater=? where id=?";


		value.add(entity.getDeptId());
		value.add(entity.getKind());
		value.add(entity.getStaName());
		value.add(entity.getStaNo());
		value.add(entity.getIsDelete());
		value.add(entity.getDeleteTime());
		value.add(entity.getIsValidater());
		value.add(entity.getId());
		
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			result = genericTemplate.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		return result;
	}

	public List<Staff> read(Staff entity) {
		// TODO Auto-generated method stub
		StaffMapping staffMapping = new StaffMapping();
		Staff staff = null;
		ResultSet resultSet;
		sql = "select * from staff where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			staff = staffMapping.mapping(resultSet);
			list.add(staff);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<Staff> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		StaffMapping staffMapping = new StaffMapping();
		Staff staff = null;
		ResultSet resultSet;
		sql = "select * from staff where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			staff = staffMapping.mapping(resultSet);
			list.add(staff);
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		return list;
	}
	//修改审批人权限
	public int checkStaff(){
		sql = "update staff set isValidater=1 where staNo=";
		for(int i=0;i<checkStaffNo.length;i++){				
		sql=sql+"'"+checkStaffNo[i]+"' or staNo=";
		}
		sql=sql+"'10000'";	
		System.out.println(sql);
		genericTemplate.setSqlValue(sql);
		try {
			result = genericTemplate.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}	
		return result;
	}
	public int count() {
		// TODO Auto-generated method stub
		ResultSet resultSet;
		sql = "select count(*) from staff";
		genericTemplate.setSqlValue(sql);
		try {
			resultSet = genericTemplate.executeQuery();
			if(resultSet.next()) {
				result = resultSet.getInt("count(*)");
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		return result;
	}
	

}
