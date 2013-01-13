package com.sisys.service;

import java.sql.SQLException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import com.sisys.bean.Department;
import com.sisys.bean.DisqKind;
import com.sisys.bean.SmallWf;
import com.sisys.bean.Staff;
import com.sisys.dao.DepartmentDAO;
import com.sisys.dao.DisqKindDAO;
import com.sisys.dao.SmallWfDAO;
import com.sisys.dao.StaffDAO;


public class SearchPd6Service {
	java.text.DecimalFormat   df=new   java.text.DecimalFormat("#0.00");
	
	List<DisqStaff> disqStaff = new ArrayList<DisqStaff>();
	List<DisqKind> disqKind = new ArrayList<DisqKind>();
	List<Integer> total =new ArrayList<Integer>();
	String sql;
	String deptName;

	public Map<String, Object> SearchPd6(String deptNo,String starttime,String endtime) throws SQLException {
		Map<String, Object> map= new HashMap<String, Object>();
		// 查找出不合格的料废种类和工废种类
		DisqKindDAO disqKindDAO = new DisqKindDAO();
		Map<String, Object> equalsmap1 = new HashMap<String, Object>();
		equalsmap1.put("isDelete", 0);
		disqKind = disqKindDAO.findEntity(equalsmap1);
		System.out.println(disqKind);
		//查找该部门员工
		//在部门表中查找proID
		DepartmentDAO departmentDAO =new DepartmentDAO();
		List<Department> department = new ArrayList<Department>();
		Map<String, Object> equalsmap = new HashMap<String, Object>();
		equalsmap.put("deptNo", deptNo);
		equalsmap.put("isDelete", 0);
		department=departmentDAO.findEntity(equalsmap);
		if(department.size() == 0){
			map.put("result", "error");
			map.put("message", "部门编号不存在！请重新输入！");
			return map;
		}
		deptName=department.get(0).getDeptName();
		System.out.println(deptName);
		int deptId=department.get(0).getId();
		//查找员工
		StaffDAO staffDAO =new StaffDAO();
		List<Staff> staff = new ArrayList<Staff>();
		equalsmap.clear();
		equalsmap.put("deptId", deptId);
		equalsmap.put("isDelete", 0);
		staff=staffDAO.findEntity(equalsmap);
		if(staff.size() == 0){
			map.put("result", "error");
			map.put("message", "部门没有员工！请重新输入！");
			return map;
		}
		
		// 在SmallWorkForm中查找时间符合的工单
		
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
		//System.out.println(smallWf);
		// 解析不合格品
		for (int i = 0; i < smallWf.size(); i++) {
			int flag=0;
			for(int j=0;j<staff.size();j++){
				if(smallWf.get(i).getStaNo().equals(staff.get(j).getStaNo())){
					flag=j;
					break;
				}
			}
			if(flag!=0){
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
	
				tmp.staName = staff.get(flag).getStaName();
				tmp.staNo = staff.get(flag).getStaNo();
				tmp.bworkHours=smallWf.get(i).getBworkHours();
				//System.out.println(tmp.staName);
				disqStaff.add(tmp);	
			}
		}
		// 合并工单中同一不合格类型的数据
		for (int i = 0; i <disqStaff.size(); i++) {
			for (int j = i + 1; j < disqStaff.size(); j++) {
				if (disqStaff.get(j).staNo.equals(disqStaff.get(i).staNo)) {
					disqStaff.get(i).bworkHours+=disqStaff.get(j).bworkHours;
					for(int k = 0; k <disqStaff.get(i).disqTypeNum.size(); k++) {
						disqStaff.get(i).disqTypeNum.set(k, disqStaff.get(i).disqTypeNum.get(k)+disqStaff.get(j).disqTypeNum.get(k));									
					}
					disqStaff.remove(j);
					j--;
				}								
			}
			disqStaff.get(i).bworkHours=Double.parseDouble(df.format(disqStaff.get(i).bworkHours));
		}
		//计算合计
		for(int k=0;k<disqStaff.get(0).disqTypeNum.size();k++){
			int tmp_total=0;
			for (int i = 0; i <disqStaff.size(); i++) {
				tmp_total=tmp_total+disqStaff.get(i).disqTypeNum.get(k);
			}
			total.add(tmp_total);
		}
		
		
		map.put("disqkind", disqKind);
		map.put("result", "success");
		map.put("disqStaff", disqStaff);
		map.put("total", total);
		map.put("deptName", deptName);
		
		
		return map;
	}
	
	public static void main(String args[]) throws SQLException, ParseException{
		SearchPd6Service s=new SearchPd6Service();
		Map<String,Object> map = new HashMap<String, Object>();
		map=s.SearchPd6("3","2012-12-01","2012-12-31");	
		System.out.println(map);
	}
	class DisqStaff {
		public String staName;
		public String staNo;
		public Double bworkHours;
		public List<Integer> disqTypeNum = new ArrayList<Integer>();
	}
}