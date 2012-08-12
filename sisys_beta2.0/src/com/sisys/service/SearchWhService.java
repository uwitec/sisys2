package com.sisys.service;

import java.math.BigDecimal;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sisys.bean.BackWorkForm;
import com.sisys.bean.Department;
import com.sisys.bean.SmallWf;
import com.sisys.bean.Staff;
import com.sisys.dao.BackWorkFormDAO;
import com.sisys.dao.DepartmentDAO;
import com.sisys.dao.SmallWfDAO;
import com.sisys.dao.StaffDAO;

public class SearchWhService {
	
	
	//private Date startTime;
	//private Date endTime
	String sql;
	
	Map<String,Object> map = new HashMap<String, Object>();
	
	public Map<String,Object> Search(String deptNo,String starttime,String endtime) throws SQLException, ParseException{
	//在staff表中查找staID
	/*  startTime= dateformat.parse(starttime);
		endTime= dateformat.parse(endtime);
		System.out.println(startTime+"	"+endTime);
	*/
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
			
		List<Department> department=new ArrayList<Department>();
		DepartmentDAO departmentDAO =new DepartmentDAO();
		Map<String, Object> equalsmap0 = new HashMap<String, Object>();
		equalsmap0.put("deptNo", deptNo);
		equalsmap0.put("isDelete", 0);
		department=departmentDAO.findEntity(equalsmap0);
		if(department.size() == 0){
			map.put("result", "error");
			map.put("message", "部门编号不存在！");
			return map;
		}
		int deptId=department.get(0).getId();
		String deptName = department.get(0).getDeptName();
				
		StaffDAO staffDAO=new StaffDAO();
		List<Staff> staff = new ArrayList<Staff>();
		Map<String, Integer> equalsmap = new HashMap<String, Integer>();
		equalsmap.put("deptId", deptId);
		equalsmap.put("isDelete", 0);
		staff=staffDAO.findEntity(equalsmap);
		if(staff.size() == 0){
			map.put("result", "error");
			map.put("message", "部门员工不存在！");
			return map;
		}
		int flag = 0;
		for(int i=0;i<staff.size();i++){
			Map<String,Object> Whmap = new HashMap<String, Object>();
			System.out.println(staff);
			String staNo= staff.get(i).getStaNo();
		//在smallWF中查找其他信息	
			SmallWfDAO smallWfDAO =new SmallWfDAO();
			sql="select * from smallWf where staNo='"+staNo+"' and time between '"+starttime+"' and '"+endtime+"'";
			List<SmallWf> smallWf = new ArrayList<SmallWf>();
			smallWf=smallWfDAO.findEntityByList(sql);
			
		//在返工工单中查找返工工时
			BackWorkFormDAO backWorkFormDAO =new BackWorkFormDAO();
			String sql1="select * from BackWorkForm where staNo='"+staNo+"' and time between '"+starttime+"' and '"+endtime+"'";
			List<BackWorkForm> backWorkForm = new ArrayList<BackWorkForm>();
			backWorkForm=backWorkFormDAO.findEntityByList(sql1);
			if(smallWf.size() == 0&& backWorkForm.size() == 0){
				continue;
			}
			flag = 1;
			System.out.println(smallWf.get(0).getTime());
			
			//合并smallWF表中中同一人一天工时的数据
					for(int k=0;k<smallWf.size();k++){
						System.out.println(smallWf.get(k));
						for(int j=k+1;j<smallWf.size();j++){
							if(smallWf.get(j).getTime().compareTo(smallWf.get(k).getTime()) == 0){
								smallWf.get(k).setBworkHours(smallWf.get(k).getBworkHours()+smallWf.get(j).getBworkHours());
								smallWf.get(k).setSalary(smallWf.get(k).getSalary()+smallWf.get(j).getSalary());
								smallWf.remove(j);
								j--;
							}
						}
					}
			//合并BworkForm表中中同一人一天工时的数据
					for(int k=0;k<backWorkForm.size();k++){
						System.out.println(backWorkForm.get(k));
						for(int j=k+1;j<backWorkForm.size();j++){
							if(backWorkForm.get(j).getTime().compareTo(backWorkForm.get(k).getTime()) == 0){
								backWorkForm.get(k).setWorkHours(backWorkForm.get(k).getWorkHours()+backWorkForm.get(j).getWorkHours());
								
								backWorkForm.remove(j);
								j--;
							}
						}
					}
		
		System.out.println(smallWf.get(0).getTime());			
			
		Whmap.put("smallWf", smallWf);
		Whmap.put("backWorkForm", backWorkForm);
		Whmap.put("staName",staff.get(i).getStaName());
		Whmap.put("staNo",staff.get(i).getStaNo());
		Whmap.put("staId",staff.get(i).getId());
		
		listMap.add(Whmap);
		
		}
		if(flag == 0){
			map.put("result", "error");
			map.put("message", "该时间段该部门员工无工时信息！！");
			return map;
		}
		
		map.put("result", "success");
		map.put("listMap", listMap);
		map.put("starttime", starttime);
		map.put("endTime", endtime);
		map.put("deptName", deptName);
		return map;
	}
	//显示
	public Map ShowWh(Map mapWh) throws Exception{
		List<Map<String,Object>> listMap = (List<Map<String,Object>>) mapWh.get("listMap");
		List<String> dateNo = new ArrayList<String>();
		
		Double workHours = 0.0;
		Double salary = 0.0;
		
		Calendar startTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		System.out.println(mapWh.get("starttime"));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		startTime.setTime(format.parse(mapWh.get("starttime").toString()));
		endTime.setTime(format.parse(mapWh.get("endTime").toString()));
		//时间段编号
		while(startTime.compareTo(endTime) <= 0){
			dateNo.add(startTime.getTime().getMonth()+1 + "-" + startTime.getTime().getDate());
			startTime.add(Calendar.DATE, 1);
		}
		
		Calendar curTime = Calendar.getInstance();
		for(int i = 0;i < listMap.size();i++){
			List<SmallWf> list = (List<SmallWf>)listMap.get(i).get("list");
			Map<String,SmallWf> map = new HashMap<String,SmallWf>();
			Map<String,Double> totalMap = new HashMap<String,Double>();
			workHours = 0.00;
			salary = 0.00;
			for(int j = 0;j < list.size();j++){
				WorkHoursTab wh = list.get(j);
				curTime.setTime(wh.getTime());
				String day = curTime.getTime().getMonth()+1 + "-" + curTime.getTime().getDate();
				//根据当前对应时间段编号存储wh信息
				map.put(day, wh);
				workHours += wh.getWorkHours();
				salary += wh.getSalary();
			}
			list.clear();
			totalMap.put("workHours", new BigDecimal(workHours).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			totalMap.put("salary", new BigDecimal(salary).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
			
			listMap.get(i).put("whMap", map);
			listMap.get(i).put("totalMap", totalMap);
		}
		
		mapWh.put("dateNo", dateNo);
		System.out.println(mapWh);
		return mapWh;
	}
	
	
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws Exception{
		SearchWhService s=new SearchWhService();
		List<WorkHoursTab> list=new ArrayList<WorkHoursTab>();
		String staName =new String();
		Map<String,Object> map = new HashMap<String, Object>();
		map=s.Search("5","2012-07-01","2012-07-19");
		map = s.ShowWh(map);
		System.out.println(map);
		
//		list=(List<WorkHoursTab>) map.get("list");
//		staName=(String) map.get("staff");
//		System.out.println(staName);
//		Iterator<WorkHoursTab> it =list.iterator();
//		//list.iterator();
//		while(it.hasNext())
//		System.out.println(it.next().getWorkHours());
	}
}
