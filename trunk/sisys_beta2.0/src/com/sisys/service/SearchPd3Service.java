package com.sisys.service;

import java.sql.SQLException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import com.sisys.bean.DisqKind;
import com.sisys.bean.SmallWf;
import com.sisys.bean.Staff;
import com.sisys.dao.DisqKindDAO;
import com.sisys.dao.SmallWfDAO;
import com.sisys.dao.StaffDAO;


public class SearchPd3Service {
	List<DisqStaff> disqStaff = new ArrayList<DisqStaff>();
	List<DisqKind> disqKind = new ArrayList<DisqKind>();
	List<Integer> total =new ArrayList<Integer>();
	String sql;

	public Map<String, Object> SearchPd3(String starttime,String endtime) throws SQLException {
		Map<String, Object> map= new HashMap<String, Object>();
		// 查找出不合格的料废种类和工废种类
		DisqKindDAO disqKindDAO = new DisqKindDAO();
		Map<String, Object> equalsmap1 = new HashMap<String, Object>();
		equalsmap1.put("isDelete", 0);
		disqKind = disqKindDAO.findEntity(equalsmap1);
		System.out.println(disqKind);

		// 在WorkForm中查找时间符合的工单
		SmallWfDAO smallWfDAO = new SmallWfDAO();
		List<SmallWf> smallWf = new ArrayList<SmallWf>();
		sql = "select * from smallWf where time between '" + starttime
				+ "' and '" + endtime + "' and isDelete ='0'";
		smallWf = smallWfDAO.findEntityByList(sql);
		if(smallWf.size() == 0){
			map.put("result", "error");
			map.put("message", "该时间段内无工单信息！");
			return map;
		}
		System.out.println(smallWf);
		// 解析不合格品
		for (int i = 0; i < smallWf.size(); i++) {
			DisqStaff tmp = new DisqStaff();
			String tempt = smallWf.get(i).getDisqDetail();
			String[] disqNum = null;
				
			disqNum = tempt.split("-");	
			int totalDisq=0;
			for (int j = 0; j < disqNum.length; j++) {
				totalDisq=totalDisq+Integer.parseInt(disqNum[j]);
				tmp.disqTypeNum.add(Integer.parseInt(disqNum[j]));
			}
			tmp.disqTypeNum.add(totalDisq);

			StaffDAO staffDAO = new StaffDAO();
			List<Staff> staff = new ArrayList<Staff>();
			Map<String, Object> equalsmap5 = new HashMap<String, Object>();
			equalsmap5.put("staffNo", smallWf.get(i).getStaNo());
			equalsmap5.put("isDelete", 0);
			staff = staffDAO.findEntity(equalsmap5);
			
			tmp.staName = staff.get(0).getStaName();
			tmp.staNo = staff.get(0).getStaNo();
			disqStaff.add(tmp);			
		}
		// 合并工单中同一不合格类型的数据
		for (int i = 0; i <disqStaff.size(); i++) {
			for (int j = i + 1; j < disqStaff.size(); j++) {
				if (disqStaff.get(j).staNo.equals(disqStaff.get(i).staNo)) {
					for(int k = 0; k <disqStaff.get(i).disqTypeNum.size(); k++) {
						disqStaff.get(i).disqTypeNum.set(k, disqStaff.get(i).disqTypeNum.get(k)+disqStaff.get(j).disqTypeNum.get(k));									
					}
				}
				disqStaff.remove(j);
				j--;					
			}
		}
		//计算合计
		
		for(int k=0;k<disqStaff.get(0).disqTypeNum.size();k++){
			int tmp_total=0;
			for (int i = 0; i <disqStaff.size(); i++) {
				tmp_total=tmp_total+disqStaff.get(i).disqTypeNum.get(k);
			}
			total.add(tmp_total);
		}
		
		
		
		map.put("result", "success");
		map.put("disqStaff", disqStaff);
		map.put("total", total);
		
		return map;
	}
	
	public static void main(String args[]) throws SQLException, ParseException{
		SearchPd3Service s=new SearchPd3Service();
		Map<String,Object> map = new HashMap<String, Object>();
		map=s.SearchPd3("2012-07-08","2012-08-08");	
		System.out.println(map);
	}
	class DisqStaff {
		String staName;
		String staNo;
		int disqNum;
		List<Integer> disqTypeNum = new ArrayList<Integer>();
	}
}