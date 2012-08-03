package com.sisys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sisys.bean.Flowpath;
import com.sisys.bean.mapping.FlowpathMapping;
import com.sisys.util.GenericQueryImpl;

public class FlowpathDAO  extends GenericQueryImpl<Flowpath, FlowpathMapping> {

	//GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<Flowpath> list;
	static FlowpathMapping flowpathMapping = new FlowpathMapping();
	
	/**
	 * 构造函数
	 */
	public FlowpathDAO() {
		super(Flowpath.class, flowpathMapping);
		//genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<Flowpath>();
	}
	
	public int create(Flowpath entity) {
		// TODO Auto-generated method stub
		sql = "insert into flowpath values (?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getSequence());
		value.add(entity.getProId());
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

	public int delete(Flowpath entity) {
		// TODO Auto-generated method stub
		sql = "update flowpath set isDelete=?,deleteTime=? where id=?";
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

	public int update(Flowpath entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update flowpath set sequence=?,proId=?,isDelete=?,deleteTime=? where Id=?";

		value.add(entity.getSequence());
		value.add(entity.getProId());
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

	public List<Flowpath> read(Flowpath entity) {
		// TODO Auto-generated method stub
		FlowpathMapping flowpathMapping = new FlowpathMapping();
		Flowpath flowpath = null;
		ResultSet resultSet;
		sql = "select * from flowpath where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			flowpath = flowpathMapping.mapping(resultSet);
			list.add(flowpath);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<Flowpath> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		FlowpathMapping flowpathMapping = new FlowpathMapping();
		Flowpath flowpath = null;
		ResultSet resultSet;
		sql = "select * from flowpath where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			flowpath = flowpathMapping.mapping(resultSet);
			list.add(flowpath);
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
		sql = "select count(*) from flowpath";
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
