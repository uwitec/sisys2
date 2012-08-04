package com.sisys.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sisys.bean.ProductLine;

public class ProductLineMapping extends BasicMapping<ProductLine> {

	public ProductLine mapping(ResultSet rs) {
		
		ProductLine productLine = new ProductLine();
		try {
			productLine.setLineNo(rs.getString("lineNo"));
			productLine.setDeleteTime(rs.getDate("deleteTime"));
			productLine.setId(rs.getInt("Id"));
			productLine.setIsDelete(rs.getInt("isDelete"));
			productLine.setLineDesc(rs.getString("lineDesc"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return productLine;
	}

}
