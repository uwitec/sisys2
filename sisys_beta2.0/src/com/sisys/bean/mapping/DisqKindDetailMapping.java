package com.sisys.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sisys.bean.DisqKindDetail;


public class DisqKindDetailMapping extends BasicMapping<DisqKindDetail> {
	
	@Override
	public DisqKindDetail mapping(ResultSet rs) {
		
		DisqKindDetail disqKindDetail = new DisqKindDetail();
		try {
			disqKindDetail.setDisqKId(rs.getInt("disqKId"));
			disqKindDetail.setId(rs.getInt("Id"));
			disqKindDetail.setTime(rs.getDate("time"));
			disqKindDetail.setDisqKNum(rs.getInt("disqKNum"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return disqKindDetail;
		
	}

}
