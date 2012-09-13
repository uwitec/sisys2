package com.sisys.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.sisys.bean.ProHash;

public class ProHashMapping extends BasicMapping<ProHash> {

	@Override
	public ProHash mapping(ResultSet rs) throws ParseException {
		// TODO Auto-generated method stub
		ProHash proHash = new ProHash();
		try {
			proHash.setDate(rs.getString("date"));
			proHash.setId(rs.getInt("id"));
			proHash.setOwn(rs.getInt("own"));
			proHash.setProNo(rs.getString("proNo"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proHash;
	}

}
