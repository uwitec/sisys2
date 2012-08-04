package com.sisys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sisys.bean.StaffKind;
import com.sisys.bean.mapping.StaffKindMapping;
import com.sisys.util.GenericQueryImpl;
import com.sisys.util.GenericTemplate;

public class StaffKindDAO extends GenericQueryImpl<StaffKind, StaffKindMapping> {

	//GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<StaffKind> list;
	static StaffKindMapping staffKindMapping = new StaffKindMapping();
	
	/**
	 * 构造函数
	 */
	public StaffKindDAO() {
		super(StaffKind.class, staffKindMapping);
		//genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<StaffKind>();
	}
	
	public int create(StaffKind entity) {
		// TODO Auto-generated method stub
		sql = "insert into staffkind values (?,?,?,?)";
		
		value.add(entity.getId());		
		value.add(entity.getKindDesc());
		value.add(entity.getIsDelete());
		value.add(entity.getDeleteTime());
		
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

	public int delete(StaffKind entity) {
		// TODO Auto-generated method stub
		sql = "update staffkind set isDelete=?,deleteTime=? where id=?";
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

	public int update(StaffKind entity) {
		// TODO Auto-generated method stub
		sql = "update staffkind set kindDesc=?,isDelete=?,deleteTime=? where id=?";
		
		value.add(entity.getKindDesc());
		value.add(entity.getIsDelete());
		value.add(entity.getDeleteTime());
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

	public List<StaffKind> read(StaffKind entity) {
		// TODO Auto-generated method stub
		StaffKindMapping staffKindMapping = new StaffKindMapping();
		StaffKind staffKind = null;
		ResultSet resultSet;
		sql = "select * from staffkind where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			staffKind = staffKindMapping.mapping(resultSet);
			list.add(staffKind);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<StaffKind> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		StaffKindMapping staffKindMapping = new StaffKindMapping();
		StaffKind staffKind = null;
		ResultSet resultSet;
		sql = "select * from staffkind where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			staffKind = staffKindMapping.mapping(resultSet);
			list.add(staffKind);
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		return list;
	}

	public int count() {
		// TODO Auto-generated method stub
		ResultSet resultSet;
		sql = "select count(*) from staffkind";
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
