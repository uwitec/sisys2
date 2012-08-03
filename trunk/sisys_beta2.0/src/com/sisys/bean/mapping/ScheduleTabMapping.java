package com.sisys.bean.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sisys.bean.ScheduleTab;


public class ScheduleTabMapping extends BasicMapping<ScheduleTab> {

	@Override
	public ScheduleTab mapping(ResultSet rs) {
		
		ScheduleTab scheduleTab = new ScheduleTab();
		try {
			scheduleTab.setBatchId(rs.getInt("batchId"));
			scheduleTab.setColorNo(rs.getString("colorNo"));
			scheduleTab.setId(rs.getInt("Id"));
			scheduleTab.setNum(rs.getInt("num"));
			scheduleTab.setTime(rs.getDate("time"));
			scheduleTab.setDisqNum(rs.getInt("disqNum"));
			scheduleTab.setIsEnd(rs.getInt("isEnd"));
			scheduleTab.setProcId(rs.getInt("procId"));
			scheduleTab.setQuaNum(rs.getInt("quaNum"));
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return scheduleTab;
		
	}

}
