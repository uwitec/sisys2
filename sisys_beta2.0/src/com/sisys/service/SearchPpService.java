package com.sisys.service;

import java.sql.SQLException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sisys.bean.Department;
import com.sisys.bean.Processes;
import com.sisys.bean.Product;
import com.sisys.bean.SmallWf;
import com.sisys.bean.Staff;
import com.sisys.dao.DepartmentDAO;
import com.sisys.dao.ProcessesDAO;
import com.sisys.dao.ProductDAO;
import com.sisys.dao.SmallWfDAO;
import com.sisys.dao.StaffDAO;


public class SearchPpService {
	
	List<SmallWf> list = new ArrayList<SmallWf>();
	List<PeopleComp> peopleComp = new ArrayList<PeopleComp>();
	private String staName;
	private String deptName;
	String sql;
	java.text.DecimalFormat   df=new   java.text.DecimalFormat("#0.00"); 
	
	public Map<String,Object> SearchPp(String staNo,String starttime,String endtime) throws SQLException, ParseException{
		Map<String,Object> map = new HashMap<String, Object>();
	//在staff表中查找staID
		StaffDAO staffDAO=new StaffDAO();
		List<Staff> staff = new ArrayList<Staff>();
		Map<String, String> equalsmap = new HashMap<String, String>();
		equalsmap.put("staNo", staNo);
		equalsmap.put("isDelete", "0");
		staff=staffDAO.findEntity(equalsmap);
		if(staff.size() == 0){
			map.put("result", "error");
			map.put("message", "工号不存在！请重新输入！");
			return map;
		}
					
		System.out.println(staff);
		int deptId=staff.get(0).getDeptId();
		setStaName(staff.get(0).getStaName());
	//查找部门表
		DepartmentDAO departmentDAO=new DepartmentDAO();
		List<Department> department = new ArrayList<Department>();
		Map<String, Integer> equalsmap1 = new HashMap<String, Integer>();
		equalsmap1.put("Id", deptId);
		equalsmap1.put("isDelete", 0);
		department=departmentDAO.findEntity(equalsmap1);
		if(department.size() == 0){
			map.put("result", "error");
			map.put("message", "部门不存在！请重新输入！");
			return map;
		}
		setDeptName(department.get(0).getDeptName());
		
	//在workform中查找其他信息	
		SmallWfDAO smallWfDAO =new SmallWfDAO();
		sql="select * from smallWf where staffNo='"+staNo+"' and time between '"+starttime+"' and '"+endtime+"'";
		//workFormDAO.queryWorkForm(sql);
		list=smallWfDAO.findEntityByList(sql);
		//System.out.println(list.get(0));
		if(list.size() == 0){
			map.put("result", "error");
			map.put("message", "工单不存在！请重新输入！");
			return map;
		}
		
		
		//合并工单中同一产品同一工序的数据
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getBworkHours());
			for(int j=i+1;j<list.size();j++){
				if(list.get(j).getProNo().equals(list.get(i).getProNo())&&list.get(j).getProcId()==list.get(i).getProcId()){
					list.get(i).setQuaNum(list.get(i).getQuaNum()+list.get(j).getQuaNum());
					list.get(i).setgWasteNum(list.get(i).getgWasteNum()+list.get(j).getgWasteNum());
					list.get(i).setlWasteNum(list.get(i).getlWasteNum()+list.get(j).getlWasteNum());
					list.get(i).setBworkHours(list.get(i).getBworkHours()+list.get(j).getBworkHours());
					list.remove(j);
					j--;
				}
			}
		}
		//根据工单找出其他信息
	
		for(int i=0;i<list.size();i++){
			PeopleComp tmp = new PeopleComp();
			String proNo=list.get(i).getProNo();
			ProductDAO productDAO=new ProductDAO();
			List<Product> product=new ArrayList<Product>();
			Map<String, Object> equalsmap2 = new HashMap<String, Object>();
			equalsmap2.put("proNo", proNo);
			equalsmap2.put("isDelete", 0);
			product=productDAO.findEntity(equalsmap2);
			if(product.size() == 0){
				map.put("result", "error");
				map.put("message", "产品不存在！请重新输入！");
				return map;
			}
			
			
			int procId=list.get(i).getProcId();
			ProcessesDAO processesDAO = new ProcessesDAO();
			List<Processes> processes=new ArrayList<Processes>();
			Map<String, Integer> equalsmap3 = new HashMap<String, Integer>();
			equalsmap3.put("Id", procId);
			equalsmap3.put("isDelete", 0);
			processes=processesDAO.findEntity(equalsmap3);
			if(processes.size() == 0){
				map.put("result", "error");
				map.put("message", "工序不存在！请重新输入！");
				return map;
			}
			
			tmp.proName=product.get(0).getProName();
			tmp.proNo=list.get(i).getProNo();
			tmp.procName=processes.get(0).getProcName();
			tmp.quaNum=list.get(i).getQuaNum();
			tmp.gWaste=list.get(i).getgWasteNum();
			tmp.lWaste=list.get(i).getlWasteNum();
			tmp.workHours=Double.parseDouble(df.format(list.get(i).getBworkHours()));
			
			peopleComp.add(tmp);
		}
		
		map.put("result", "success");
		map.put("list", peopleComp);
		map.put("staName", staName);
		map.put("deptName", deptName);
		return map;
	}
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws SQLException, ParseException{
		SearchPpService s=new SearchPpService();
		List<PeopleComp> list=new ArrayList<PeopleComp>();
		String staName =new String();
		Map<String,Object> map = new HashMap<String, Object>();
		map=s.SearchPp("51022","2012-08-01","2012-08-30");
		
		list=(List<PeopleComp>) map.get("list");
		staName=(String) map.get("staName");
		System.out.println(map);
		Iterator<PeopleComp> it =list.iterator();
		//list.iterator();
		while(it.hasNext())
		System.out.println(it.next());
	}
	public String getStaName() {
		return staName;
	}
	public void setStaName(String staName) {
		this.staName = staName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
class PeopleComp{
	public String proName;
	public String proNo;
	public String procName;
	public int quaNum;
	public int gWaste;
	public int lWaste;	
	public double workHours;
}
}
