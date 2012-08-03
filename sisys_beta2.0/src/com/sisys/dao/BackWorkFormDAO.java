package com.sisys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sisys.bean.BackWorkForm;
import com.sisys.bean.mapping.BackWorkFormMapping;
import com.sisys.util.GenericQueryImpl;

public class BackWorkFormDAO  extends GenericQueryImpl<BackWorkForm, BackWorkFormMapping> {

	//GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<BackWorkForm> list;
	static BackWorkFormMapping backWorkFormMapping = new BackWorkFormMapping();
	
	/**
	 * 构造函数
	 */
	public BackWorkFormDAO() {
		super(BackWorkForm.class, backWorkFormMapping);
		//genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<BackWorkForm>();
	}
	
	public int create(BackWorkForm entity) {
		// TODO Auto-generated method stub
		sql = "insert into backWorkForm values (?,?,?,?,?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getStaNo());
		value.add(entity.getKind());
		value.add(entity.getWorkHours());
		value.add(entity.getName());
		value.add(entity.getCheckNo());
		value.add(entity.getRespNo());
		value.add(entity.getIsDelete());
		value.add(entity.getDeleteDate());
		
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

	public int delete(BackWorkForm entity) {
		// TODO Auto-generated method stub
		sql = "update backWorkForm set isDelete=?,deleteTime=? where id=?";
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

	public int update(BackWorkForm entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update backWorkForm set staNo=?,kind=?,workHours=?,name=?,checkNo=?," +
				"respNo=?,isDelete=?,deleteDate=? where Id=?";


		value.add(entity.getStaNo());
		value.add(entity.getKind());
		value.add(entity.getWorkHours());
		value.add(entity.getName());
		value.add(entity.getCheckNo());
		value.add(entity.getRespNo());
		value.add(entity.getIsDelete());
		value.add(entity.getDeleteDate());
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

	public List<BackWorkForm> read(BackWorkForm entity) {
		// TODO Auto-generated method stub
		BackWorkFormMapping backWorkFormMapping = new BackWorkFormMapping();
		BackWorkForm backWorkForm = null;
		ResultSet resultSet;
		sql = "select * from backWorkForm where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			backWorkForm = backWorkFormMapping.mapping(resultSet);
			list.add(backWorkForm);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<BackWorkForm> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		BackWorkFormMapping backWorkFormMapping = new BackWorkFormMapping();
		BackWorkForm backWorkForm = null;
		ResultSet resultSet;
		sql = "select * from backWorkForm where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			backWorkForm = backWorkFormMapping.mapping(resultSet);
			list.add(backWorkForm);
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
		sql = "select count(*) from backWorkForm";
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

