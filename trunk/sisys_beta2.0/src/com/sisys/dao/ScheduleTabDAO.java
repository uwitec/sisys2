package com.sisys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sisys.bean.ScheduleTab;
import com.sisys.bean.mapping.ScheduleTabMapping;
import com.sisys.util.GenericQueryImpl;


public class ScheduleTabDAO extends GenericQueryImpl<ScheduleTab, ScheduleTabMapping> {

	//GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<ScheduleTab> list;
	static ScheduleTabMapping scheduleTabMapping = new ScheduleTabMapping();
	
	/**
	 * 构造函数
	 */
	public ScheduleTabDAO() {
		super(ScheduleTab.class, scheduleTabMapping);
		//genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<ScheduleTab>();
	}
	
	public int create(ScheduleTab entity) {
		// TODO Auto-generated method stub
		sql = "insert into scheduleTab values (?,?,?,?,?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getBatchId());
		value.add(entity.getProcId());
		value.add(entity.getTime());
		value.add(entity.getColorNo());
		value.add(entity.getNum());
		value.add(entity.getQuaNum());
		value.add(entity.getDisqNum());
		value.add(entity.getIsEnd());
		
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

	public int delete(ScheduleTab entity) {
		// TODO Auto-generated method stub
		sql = "delete from scheduleTab where Id=?";
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

	public int update(ScheduleTab entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update scheduleTab set batchId=?,procId=?,time=?,colorNo=?,num=?,quaNum=?," +
				"disqNum=?,isEnd=? where Id=?";

		value.add(entity.getBatchId());
		value.add(entity.getProcId());
		value.add(entity.getTime());
		value.add(entity.getColorNo());
		value.add(entity.getNum());
		value.add(entity.getQuaNum());
		value.add(entity.getDisqNum());
		value.add(entity.getIsEnd());
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

	public List<ScheduleTab> read(ScheduleTab entity) {
		// TODO Auto-generated method stub
		ScheduleTabMapping scheduleTabMapping = new ScheduleTabMapping();
		ScheduleTab scheduleTab = null;
		ResultSet resultSet;
		sql = "select * from scheduleTab where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			scheduleTab = scheduleTabMapping.mapping(resultSet);
			list.add(scheduleTab);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<ScheduleTab> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		ScheduleTabMapping scheduleTabMapping = new ScheduleTabMapping();
		ScheduleTab scheduleTab = null;
		ResultSet resultSet;
		sql = "select * from scheduleTab where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			scheduleTab = scheduleTabMapping.mapping(resultSet);
			list.add(scheduleTab);
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
		sql = "select count(*) from scheduleTab";
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
