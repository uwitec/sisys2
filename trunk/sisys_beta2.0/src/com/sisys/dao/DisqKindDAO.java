package com.sisys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sisys.bean.DisqKind;
import com.sisys.bean.mapping.DisqKindMapping;
import com.sisys.util.GenericQueryImpl;


public class DisqKindDAO  extends GenericQueryImpl<DisqKind, DisqKindMapping>{

	//GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<DisqKind> list;
	static DisqKindMapping disqKindMapping = new DisqKindMapping();
	
	/**
	 * 构造函数
	 */
	public DisqKindDAO() {
		super(DisqKind.class, disqKindMapping);
		//genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<DisqKind>();
	}
	
	public int create(DisqKind entity) {
		// TODO Auto-generated method stub
		sql = "insert into disqKind values (?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getDisDesc());
		value.add(entity.getKind());
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

	public int delete(DisqKind entity) {
		// TODO Auto-generated method stub
		sql = "update disqkind set isDelete=?,deleteTime=? where id=?";
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

	public int update(DisqKind entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update disqKind set disDesc=?,kind=?,isDelete=?,deleteTime=? where Id=?";

		value.add(entity.getDisDesc());
		value.add(entity.getKind());
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

	public List<DisqKind> read(DisqKind entity) {
		// TODO Auto-generated method stub
		DisqKindMapping disqKindMapping = new DisqKindMapping();
		DisqKind disqKind = null;
		ResultSet resultSet;
		sql = "select * from disqKind where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			disqKind = disqKindMapping.mapping(resultSet);
			list.add(disqKind);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<DisqKind> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		DisqKindMapping disqKindMapping = new DisqKindMapping();
		DisqKind disqKind = null;
		ResultSet resultSet;
		sql = "select * from disqKind where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			disqKind = disqKindMapping.mapping(resultSet);
			list.add(disqKind);
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
		sql = "select count(*) from disqKind";
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
	
	//得到不合格种类表内的所有记录
	public List<DisqKind> readAll() {
		// TODO Auto-generated method stub
		DisqKindMapping disqKindMapping = new DisqKindMapping();
		DisqKind disqKind = null;
		ResultSet resultSet;
		sql = "select * from disqKind";
		genericTemplate.setSqlValue(sql);
		try {
			resultSet = genericTemplate.executeQuery();
			while(resultSet.next()) {
				disqKind = (DisqKind) disqKindMapping.mapping(resultSet);			
				list.add(disqKind);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		return list;
	}
}
