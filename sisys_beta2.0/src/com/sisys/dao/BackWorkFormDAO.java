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
		sql = "insert into backWorkForm values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getProNo());
		value.add(entity.getBatchNo());
		value.add(entity.getProcNo());
		value.add(entity.getStaNo());
		value.add(entity.getKind());
		value.add(entity.getWorkHours());
		value.add(entity.getName());
		value.add(entity.getCheckNo());
		value.add(entity.getRespNo());
		value.add(entity.getTime());
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
		sql = "update backWorkForm set proNo=?,batchNo=?,procNo=?,staNo=?,kind=?,workHours=?,name=?,checkNo=?," +
				"respNo=?,time=?,isDelete=?,deleteTime=? where Id=?";

		value.add(entity.getProNo());
		value.add(entity.getBatchNo());
		value.add(entity.getProcNo());
		value.add(entity.getStaNo());
		value.add(entity.getKind());
		value.add(entity.getWorkHours());
		value.add(entity.getName());
		value.add(entity.getCheckNo());
		value.add(entity.getRespNo());
		value.add(entity.getTime());
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
	
	public static void main(String[] args) {
		BackWorkFormDAO bwf = new BackWorkFormDAO();
		BackWorkForm backWf = new BackWorkForm(0,"760402","2012","1","51016","test",2.34,"admin","51021","51024",new Date(),0,new Date());
		System.out.println(bwf.create(backWf));
		System.out.println(new BackWorkFormDAO().count());
	}
}

