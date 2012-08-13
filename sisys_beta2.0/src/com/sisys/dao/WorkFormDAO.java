package com.sisys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sisys.bean.WorkForm;
import com.sisys.bean.mapping.WorkFormMapping;
import com.sisys.util.GenericQueryImpl;


public class WorkFormDAO extends GenericQueryImpl<WorkForm, WorkFormMapping> {

	//GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<WorkForm> list;
	static WorkFormMapping workFormMapping = new WorkFormMapping();
	
	/**
	 * 构造函数
	 */
	public WorkFormDAO() {
		super(WorkForm.class, workFormMapping);
		//genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<WorkForm>();
	}
	
	public int create(WorkForm entity) {
		// TODO Auto-generated method stub
		sql = "insert into workForm values (?,?,?,?,?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getBatchId());
		value.add(entity.getProcId());
		value.add(entity.getQuaNum());
		value.add(entity.getDisquaNum());
		value.add(entity.getTime());
		value.add(entity.getIsDelete());
		value.add(entity.getDeleteTime());
		value.add(entity.getName());
		
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

	public int delete(WorkForm entity) {
		// TODO Auto-generated method stub
		sql = "update workForm set isDelete=?,deleteTime=? where id=?";
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

	public int update(WorkForm entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update workForm set batchId=?,procId=?,quaNum=?,disqNum=?,time=?," +
				"isDelete=?,deleteTime=?,name=? where Id=?";

		value.add(entity.getBatchId());
		value.add(entity.getProcId());
		value.add(entity.getQuaNum());
		value.add(entity.getDisquaNum());
		value.add(entity.getTime());
		value.add(entity.getIsDelete());
		value.add(entity.getDeleteTime());
		value.add(entity.getName());
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

	public List<WorkForm> read(WorkForm entity) {
		// TODO Auto-generated method stub
		WorkFormMapping workFormMapping = new WorkFormMapping();
		WorkForm workForm = null;
		ResultSet resultSet;
		sql = "select * from workForm where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			workForm = workFormMapping.mapping(resultSet);
			list.add(workForm);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<WorkForm> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		WorkFormMapping workFormMapping = new WorkFormMapping();
		WorkForm workForm = null;
		ResultSet resultSet;
		sql = "select * from workForm where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			workForm = workFormMapping.mapping(resultSet);
			list.add(workForm);
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
		sql = "select count(*) from workForm";
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
