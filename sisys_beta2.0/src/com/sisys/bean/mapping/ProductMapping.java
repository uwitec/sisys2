package com.sisys.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sisys.bean.Product;

public class ProductMapping extends BasicMapping<Product> {

	public Product mapping(ResultSet rs) {
		
		Product product = new Product();		
		try {
			
			product.setDeleteTime(rs.getDate("deleteTime"));
			product.setDeptId(rs.getInt("deptId"));			
			product.setId(rs.getInt("Id"));
			product.setIsDelete(rs.getInt("isDelete"));
			product.setProlineId(rs.getInt("prolineId"));
			product.setProName(rs.getString("proName"));
			product.setProNo(rs.getString("proNo"));
			product.setProCycle(rs.getInt("proCycle"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return product;
	}

}