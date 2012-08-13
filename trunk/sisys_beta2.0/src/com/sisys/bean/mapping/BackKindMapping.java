package com.sisys.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.sisys.bean.BackKind;

public class BackKindMapping extends BasicMapping<BackKind>{

	@Override
	public BackKind mapping(ResultSet rs) throws ParseException {
		// TODO Auto-generated method stub
		BackKind backKind = new BackKind();
		try {
			backKind.setId(rs.getInt("id"));
			backKind.setName(rs.getString("name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return backKind;
	}

}
