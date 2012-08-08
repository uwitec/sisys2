package com.sisys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;

import com.sisys.bean.SmallWf;
import com.sisys.bean.mapping.SmallWfMapping;
import com.sisys.util.GenericQueryImpl;


public class SmallWfDAO extends GenericQueryImpl<SmallWf, SmallWfMapping> {

	//GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<SmallWf> list;
	static SmallWfMapping SmallWfMapping = new SmallWfMapping();
	
	/**
	 * 构造函数
	 */
	public SmallWfDAO() {
		super(SmallWf.class, SmallWfMapping);
		//genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<SmallWf>();
	}
	
	public int create(SmallWf entity) {
		// TODO Auto-generated method stub
		sql = "insert into smallwf values (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getWfId());
		value.add(entity.getProNo());
		value.add(entity.getProcId());
		value.add(entity.getQuaNum());
		value.add(entity.getDisqDetail());
		value.add(entity.getStaNo());
		value.add(entity.getBworkHours());
		value.add(entity.getSalary());
		
		
		value.add(entity.getgWasteNum());
		value.add(entity.getlWasteNum());
		value.add(entity.getTime());
		
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

	public int delete(SmallWf entity) {
		// TODO Auto-generated method stub
		sql = "delete from smallWf where id=?";
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

	public int update(SmallWf entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update smallWf set wfId=?,proNo=?,procId=?,quaNum=?," +
				"disqDetail=?,staffNo=?,bworkHours=?,salary=?,gWasteNum=?,lWasteNum=?,time=? where Id=?";

		value.add(entity.getWfId());
		value.add(entity.getProNo());
		value.add(entity.getProcId());
		value.add(entity.getQuaNum());
		value.add(entity.getDisqDetail());
		value.add(entity.getStaNo());
		value.add(entity.getBworkHours());
		value.add(entity.getSalary());
		value.add(entity.getgWasteNum());
		value.add(entity.getlWasteNum());
		value.add(entity.getTime());
		
		
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

	public List<SmallWf> read(SmallWf entity) {
		// TODO Auto-generated method stub
		SmallWfMapping smallWfMapping = new SmallWfMapping();
		SmallWf smallWf = null;
		ResultSet resultSet;
		sql = "select * from smallWf where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			smallWf = smallWfMapping.mapping(resultSet);
			list.add(smallWf);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<SmallWf> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		SmallWfMapping smallWfMapping = new SmallWfMapping();
		SmallWf smallWf = null;
		ResultSet resultSet;
		sql = "select * from smallWf where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			smallWf = smallWfMapping.mapping(resultSet);
			list.add(smallWf);
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
		sql = "select count(*) from smallWf";
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
	
	public void queryWorkForm(String sql) {
		this.findEntityByList(sql);
	}
}
