package com.sisys.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 函数createQuerySql(List<Element> elements)是产生拼接字符串的，在实际中被调用
 * @author huangxin
 *
 * 2012-07-09
 * 
 * CreateHqlUtil
 */
public class CreateSqlUtil {
	
	public static String createQuerySql(List<Element> elements){
		Cnd cnd=new Cnd();
		if (elements!=null&&elements.size()>0) {
			Iterator<Element> iterator=elements.iterator();
			while (iterator.hasNext()) {
				Element element=iterator.next();
				String name=element.getName();
				OP op=element.getOp();
				Object[] value=element.getObject();
				switch (element.link) {
				case WHERE:
					cnd.where(name, op, value);						
					break;
				case AND:
					cnd.and(name, op, value);
					break;
				case OR:
					cnd.or(name, op, value);
					break;
				case ORDER:
					cnd.orderBy(name, element.isAsc());
					break;
				default:
					break;
				}
			}
		}
		 return cnd.getSb().toString();
	}
	
	/**
	 * 动态生成 where查询语句
	 * 
	 * @param equalsMap
	 * @param inMap
	 * @param likeMap
	 * @return
	 */
	public static String createQuerySql(Map<String, ?> equalsMap,
			Map<String, ? extends Object[]> inMap, Map<String, String> likeMap) {
		/**
		 * 是否有"="查询语句
		 */
		boolean hasEquals = (equalsMap == null) ? false : !equalsMap.isEmpty();

		/**
		 * 是否有"in"查询语句
		 */
		boolean hasIn = (inMap == null) ? false : !inMap.isEmpty();

		/**
		 * 是否有"like"查询语句
		 */
		boolean hasLike = (likeMap == null) ? false : !likeMap.isEmpty();

		if (!hasEquals && !hasIn && !hasLike) {
			System.out.println("查询参数不能全部为空！");
		}

		StringBuffer sql = new StringBuffer(" where ");

		/**
		 * 生成"="查询语句
		 */

		if (hasEquals) {
			int equalsSize = equalsMap.size();
			int equalsCount = 0;
			for (Map.Entry<String, ?> entry : equalsMap.entrySet()) {
				equalsCount++;
				if(entry.getValue() instanceof Integer) {
					sql.append(entry.getKey() + "=" + entry.getValue() );
				} else {
					sql.append(entry.getKey() + "='" + entry.getValue() + "'");
				}
				
				if (equalsCount < equalsSize) {
					sql.append(" and ");
				}
			}
		}

		/**
		 * 生成"in()"查询语句
		 */
		if (hasIn) {
			if (hasEquals) {
				sql.append(" and ");
			}
			int inSize = inMap.size();
			int inCount = 0;
			for (Map.Entry<String, ? extends Object[]> entry : inMap.entrySet()) {
				inCount++;
				sql.append(entry.getKey() + " in(");
				Object[] values = entry.getValue();
				int length = values.length;
				for (int i = 0; i < length; i++) {
					sql.append("'" + values[i] + "'");
					if (i != (length - 1)) {
						sql.append(",");
					}
				}

				sql.append(")");

				if (inCount < inSize) {
					sql.append(" and ");
				}
			}
		}

		if (hasLike) {
			if (hasEquals || hasIn) {
				sql.append(" and ");
			}
			for (Iterator<Map.Entry<String, String>> iterator = likeMap
					.entrySet().iterator(); iterator.hasNext();) {
				Map.Entry<String, String> entry = iterator.next();
				sql.append(entry.getKey() + " like '" + entry.getValue() + "'");
				if (iterator.hasNext()) {
					sql.append(" and ");
				}
			}
		}
		return sql.toString();
	}
	public static String createQuerySql(Map<String, ?> equalsMap,
			Map<String, ? extends Object[]> inMap){
		return createQuerySql(equalsMap, inMap, null);
	}
}
