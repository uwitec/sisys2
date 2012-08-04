package com.sisys.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.sisys.bean.Product;
import com.sisys.bean.mapping.ProductMapping;

import com.sisys.util.GenericQueryImpl;


public class ProductDAO  extends GenericQueryImpl<Product, ProductMapping> {

	//GenericTemplate genericTemplate;
	List<Object> value;
	String sql;
	int result;
	boolean flag;
	List<Product> list;
	static ProductMapping productMapping = new ProductMapping();
	
	/**
	 * 构造函数
	 */
	public ProductDAO() {
		super(Product.class, productMapping);
		//genericTemplate = new GenericTemplate();
		value = new ArrayList<Object>();
		result = 0;
		flag = false;
		list = new ArrayList<Product>();
	}
	
	public int create(Product entity) {
		// TODO Auto-generated method stub
		sql = "insert into product values (?,?,?,?,?,?,?,?)";
		
		value.add(entity.getId());
		value.add(entity.getDeptId());
		value.add(entity.getProlineId());
		value.add(entity.getProNo());
		value.add(entity.getProName());
		value.add(entity.getProCycle());
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

	public int delete(Product entity) {
		// TODO Auto-generated method stub
		sql = "update product set isDelete=?,deleteTime=? where id=?";
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

	public int update(Product entity, Integer pk) {
		// TODO Auto-generated method stub
		sql = "update product set deptId=?,prolineId=?,proNo=?,proName=?," +
				"proCycle=?,isDelete=?,deleteTime=? where Id=?";

		value.add(entity.getDeptId());
		value.add(entity.getProlineId());
		value.add(entity.getProNo());
		value.add(entity.getProName());
		value.add(entity.getProCycle());
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

	public List<Product> read(Product entity) {
		// TODO Auto-generated method stub
		ProductMapping productMapping = new ProductMapping();
		Product product = null;
		ResultSet resultSet;
		sql = "select * from product where Id=?";
		value.add(entity.getId());
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			//System.out.print(genericTemplate.executeQuery());
			product = (Product) productMapping.mapping(resultSet);
			list.add(product);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			genericTemplate.close();
		}
		return list;
	}

	public List<Product> readByPk(Integer pk) {
		// TODO Auto-generated method stub
		ProductMapping productMapping = new ProductMapping();
		Product product = null;
		ResultSet resultSet;
		sql = "select * from product where Id=?";
		value.add(pk);
		genericTemplate.setSqlValue(sql);
		genericTemplate.setValues(value);
		try {
			resultSet = genericTemplate.executeQuery();
			product = (Product) productMapping.mapping(resultSet);
			list.add(product);
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
		sql = "select count(*) from product";
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