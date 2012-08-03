package com.sisys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sisys.bean.DisqKindDetail;
import com.sisys.bean.mapping.DisqKindDetailMapping;
import com.sisys.util.GenericQueryImpl;


public class DisqKindDetailDAO  extends GenericQueryImpl<DisqKindDetail, DisqKindDetailMapping> {
	
	//GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<DisqKindDetail> list;
	static DisqKindDetailMapping disqKindDetailMapping = new DisqKindDetailMapping();
	
	/**
	 * 构造函数
	 */
	public DisqKindDetailDAO() {
		super(DisqKindDetail.class, disqKindDetailMapping);
		//genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<DisqKindDetail>();
	}
	
	public int create(DisqKindDetail entity) {
		// TODO Auto-generated method stub
		sql = "insert into disqkinddetail values (?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getDisqKId());
		value.add(entity.getDisqKNum());
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

	public int delete(DisqKindDetail entity) {
		// TODO Auto-generated method stub
		sql = "delete from disqkindDetail where Id=?";
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

	public int update(DisqKindDetail entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update disqkindDetail set disqKId=?,disqKNum=?,time=? where Id=?";

		value.add(entity.getDisqKId());
		value.add(entity.getDisqKNum());
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

	public List<DisqKindDetail> read(DisqKindDetail entity) {
		// TODO Auto-generated method stub
		DisqKindDetailMapping disqDetailMapping = new DisqKindDetailMapping();
		DisqKindDetail disqDetail = null;
		ResultSet resultSet;
		sql = "select * from disqKindDetail where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			disqDetail = disqDetailMapping.mapping(resultSet);
			list.add(disqDetail);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<DisqKindDetail> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		DisqKindDetailMapping disqDetailMapping = new DisqKindDetailMapping();
		DisqKindDetail disqDetail = null;
		ResultSet resultSet;
		sql = "select * from disqKindDetail where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			disqDetail = disqDetailMapping.mapping(resultSet);
			list.add(disqDetail);
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
		sql = "select count(*) from disqKindDetail";
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
