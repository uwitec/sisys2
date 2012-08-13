package com.sisys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sisys.bean.BackKind;
import com.sisys.bean.mapping.BackKindMapping;
import com.sisys.util.GenericQueryImpl;

public class BackKindDAO extends GenericQueryImpl<BackKind, BackKindMapping>{
	
	//GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<BackKind> list;
	static BackKindMapping backKindMapping = new BackKindMapping();

	/**
	 * 构造函数
	 */
	public BackKindDAO() {
		super(BackKind.class, backKindMapping);
		//genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<BackKind>();
	}
	
	public List<BackKind> findAll(){
		BackKindMapping backKindMapping = new BackKindMapping();
		BackKind backKind = null;
		ResultSet resultSet;
		sql = "select * from backkind";
		
		genericTemplate.setSqlValue(sql);
		try {
			resultSet = genericTemplate.executeQuery();
			while(resultSet.next()){
				backKind = backKindMapping.mapping(resultSet);
				list.add(backKind);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}


}
