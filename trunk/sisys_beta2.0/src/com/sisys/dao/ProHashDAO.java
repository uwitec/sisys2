package com.sisys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sisys.bean.Batch;
import com.sisys.bean.ProHash;
import com.sisys.bean.mapping.BatchMapping;
import com.sisys.bean.mapping.ProHashMapping;
import com.sisys.util.GenericQueryImpl;

public class ProHashDAO  extends GenericQueryImpl<ProHash, ProHashMapping>{

	//GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<ProHash> list;
	static ProHashMapping proHashMapping = new ProHashMapping();
	
	public ProHashDAO(Class<? extends ProHash> entityClass,
			ProHashMapping entity) {
		super(entityClass, entity);
		// TODO Auto-generated constructor stub
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<ProHash>();
	}
	
	public ProHashDAO(){
		super(ProHash.class,proHashMapping);
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<ProHash>();
	}
	public int create(ProHash entity) {
		// TODO Auto-generated method stub
		sql = "insert into proHash values (?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getProNo());
		value.add(entity.getHash());
		value.add(entity.getOwn());		
		
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

	public int delete(ProHash entity) {
		// TODO Auto-generated method stub
		sql = "delete from proHash where id=?";
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

	public int update(ProHash entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update proHash set proNo=?,hash=?,own=? where id=?";

		value.add(entity.getProNo());
		value.add(entity.getHash());
		value.add(entity.getOwn());
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

	public List<ProHash> read(ProHash entity) {
		// TODO Auto-generated method stub
		ProHashMapping proHashMapping = new ProHashMapping();
		ProHash proHash = null;
		ResultSet resultSet;
		sql = "select * from proHash where id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			proHash = proHashMapping.mapping(resultSet);
			list.add(proHash);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<ProHash> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		ProHashMapping proHashMapping = new ProHashMapping();
		ProHash proHash = null;
		ResultSet resultSet;
		sql = "select * from proHash where id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			proHash = proHashMapping.mapping(resultSet);
			list.add(proHash);
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
		sql = "select count(*) from proHash";
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
	/**
	 * 查询未使用的hash码
	 * @param proNo
	 * @return
	 */
	public List<ProHash> readByProNo(String proNo) {
		List<ProHash> pList = new ArrayList<ProHash>();
		ProHashMapping proHashMapping = new ProHashMapping();
		ProHash proHash = null;
		ResultSet resultSet;
		sql = "select * from proHash where proNo=? and own=?";
		value.add(proNo);
		value.add(0);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			if(resultSet.next()) {
				proHash = proHashMapping.mapping(resultSet);
			}
			pList.add(proHash);
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		return pList;
	}


}
