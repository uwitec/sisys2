package com.sisys.util;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sisys.bean.mapping.BasicMapping;


/**
 * 查询类
 * @author huangxin
 *
 * @param <PK>
 * @param <E>
 */
public class GenericQueryImpl<E, M extends BasicMapping> {

	protected GenericTemplate genericTemplate;
	
	public GenericTemplate getGenericTemplate() {
		return genericTemplate;
	}


	public void setGenericTemplate(GenericTemplate genericTemplate) {
		this.genericTemplate = genericTemplate;
	}

	protected Class<? extends E> entityClass;
	
	protected E entity;
	
	protected BasicMapping<E> entityMappingClass;
	
	//构造函数
	public GenericQueryImpl(Class<? extends E> entityClass, M entity) {
		
		this.entityClass = entityClass;
		this.entityMappingClass = entity;
		genericTemplate = new GenericTemplate();
	}
	
	
	public Class<? extends E> getEntityClass() {
		return entityClass;
	}
	
	/*public BeanMapping getEntityMappingClass() {
		return entityMappingClass;
	}*/
	/**
	 * 生成SQL语句
	 * @param elements
	 * @return
	 */
	private String createQuerySql(List<Element> elements) {
		return CreateSqlUtil.createQuerySql(elements);
	}
	
	/**
	 * 生成SQL语句
	 * @param equalsMap
	 * @param inMap
	 * @param likeMap
	 * @return
	 */
	private String createQuerySql(Map<String, ?> equalsMap,
			Map<String, ? extends Object[]> inMap, Map<String, String> likeMap) {
		return CreateSqlUtil.createQuerySql(equalsMap, inMap, likeMap);
	}
	
	/**
	 * 查询对象
	 * @param equalsMap
	 * @return
	 * @throws  
	 */
	public List<E> findEntity(Map<String, ?> equalsMap) {
		// TODO Auto-generated method stub
		List<E> list = new ArrayList<E>();
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer("select * from "
				+ getEntityClass().getSimpleName()
				+ this.createQuerySql(equalsMap, null, null));
		try {
			genericTemplate.setSqlValue(sql.toString());
			rs = genericTemplate.executeQuery();
			while(rs.next()){
				entity = entityMappingClass.mapping(rs);
				list.add(entity);
			} 
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		return list;
	}
	
	/**
	 * 查询列表
	 * @param elements
	 * @return
	 * @throws 
	 */
	public List<E> findEntityByList(List<Element> elements) {

		List<E> list = new ArrayList<E>();
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer("select * from "
				+ getEntityClass().getSimpleName() + this.createQuerySql(elements));
		try {
			genericTemplate.setSqlValue(sql.toString());
			rs = genericTemplate.executeQuery();
			while(rs.next()) {
				entity = entityMappingClass.mapping(rs);
				list.add(entity);
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		return list;
	}

	/**
	 * 传入SQL语句进行查询
	 * @param sql
	 * @return
	 */
	public List<E> findEntityByList(String sql) {

		List<E> list = new ArrayList<E>();
		ResultSet rs = null;
		try {
			genericTemplate.setSqlValue(sql);
			rs = genericTemplate.executeQuery();
			while(rs.next()) {
				entity = entityMappingClass.mapping(rs);
				list.add(entity);
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		return list;
	}
	
	/**
	 * 查询列表中存在的个数
	 * @param elements
	 * @return
	 */
	public long countEntityByList(List<Element> elements) {
		
		long result = 0;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer("select count(*) from "
				+ getEntityClass().getSimpleName() + this.createQuerySql(elements));
		try {
			genericTemplate.setSqlValue(sql.toString());
			rs = genericTemplate.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count(*)");
			}		
		} catch (Exception re) {
			re.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		return result;
	}
	
	/**
	 * 限制范围进行查询列表
	 * @param elements
	 * @param firstResult
	 * @param maxResult
	 * @return
	 * @throws 
	 */
	public List<E>  findEntityByList(List<Element> elements, int firstResult, int maxResult) {
		
		List<E> list = new ArrayList<E>();
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer("select count(*) from "
				+ getEntityClass().getSimpleName() + this.createQuerySql(elements));
		sql.append("limit " + firstResult + "," + maxResult);
		try {
			genericTemplate.setSqlValue(sql.toString());
			rs = genericTemplate.executeQuery();
			while(rs.next()) {
				entity = entityMappingClass.mapping(rs);
				list.add(entity);
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		
		return list;
	}
	
	/**
	 * 查询接口
	 * @param equalsMap
	 * @param inMap
	 * @param likeMap
	 * @param orderMap
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<E> findEntity(Map<String, ?> equalsMap,
			Map<String, ? extends Object[]> inMap, Map<String, String> likeMap,
			Map<String, OrderType> orderMap, int firstResult, int maxResult) {

		List<E> list = new ArrayList<E>();
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer("select * from "
				+ getEntityClass().getSimpleName()
				+ this.createQuerySql(equalsMap, inMap, likeMap));

		/**
		 * 动态生成排序语句
		 */
		boolean hasOrder = (orderMap == null) ? false : !orderMap.isEmpty();
		if (hasOrder) {
			sql.append(" order by ");
			for (Iterator<Map.Entry<String, OrderType>> iterator = orderMap
					.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry<String, OrderType> entry = iterator.next();
				sql.append(entry.getKey() + " " + entry.getValue());
				if (iterator.hasNext()) {
					sql.append(",");
				}
			}
		}

		try {
			/**
			 * 当maxResult大于零的时候设置分页属性
			 */
			if (maxResult > 0) {
				sql.append(" limit " + firstResult + "," + maxResult);
			}
			genericTemplate.setSqlValue(sql.toString());
			rs = genericTemplate.executeQuery();
			while(rs.next()) {
				entity = entityMappingClass.mapping(rs);
				list.add(entity);
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		return list;
	}
	
	public List<E> findEntity(Map<String, ?> equalsMap,
			Map<String, ? extends Object[]> inMap,
			Map<String, OrderType> orderMap, int firstResult, int maxResult) {
		return this.findEntity(equalsMap, inMap, null, orderMap, firstResult,
				maxResult);
	}

	public List<E> findEntity(Map<String, ?> equalsMap,
			Map<String, OrderType> orderMap, int firstResult, int maxResult) {
		return this.findEntity(equalsMap, null, null, orderMap, firstResult,
				maxResult);
	}

	public List<E> findEntity(Map<String, ?> equalsMap, int firstResult, int maxResult) {
		return this.findEntity(equalsMap, null, null, null, firstResult,
				maxResult);
	}

	public List<E> findEntity(Map<String, ?> equalsMap,
			Map<String, ? extends Object[]> inMap, Map<String, String> likeMap,
			Map<String, OrderType> orderMap) {
		return this.findEntity(equalsMap, inMap, null, orderMap, 0, -1);
	}
	
	/**
	 * 计算查询结果个数的接口
	 * @param equalsMap
	 * @param inMap
	 * @param likeMap
	 * @return
	 * @throws DBErrorException
	 * @throws DBDataErrorException
	 */
	public long countEntity(Map<String, ?> equalsMap,
			Map<String, ? extends Object[]> inMap, Map<String, String> likeMap) {

		long result = 0;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer("select count(*) from "
				+ getEntityClass().getSimpleName()
				+ this.createQuerySql(equalsMap, inMap, likeMap));

		try {
			genericTemplate.setSqlValue(sql.toString());
			rs = genericTemplate.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count(*)");
			}
		} catch (Exception re) {
			re.printStackTrace();
		}finally {
			genericTemplate.close();
		}
		return result;
	}

	public long countEntity(Map<String, ?> equalsMap,
			Map<String, ? extends Object[]> inMap)  {
		return this.countEntity(equalsMap, inMap, null);
	}

	public long countEntity(Map<String, ?> equalsMap) {
		return this.countEntity(equalsMap, null, null);
	}
}


