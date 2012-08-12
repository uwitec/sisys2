package com.sisys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sisys.bean.Processes;
import com.sisys.bean.Product;
import com.sisys.bean.Staff;
import com.sisys.dao.ProcessesDAO;
import com.sisys.dao.ProductDAO;
import com.sisys.dao.StaffDAO;
import com.sisys.util.Element;

public class ManageWorkFormService {

	public String preAddProNo(String proNo) {
		StringBuffer sb = new StringBuffer("");
		ProductDAO pdao = new ProductDAO();
		Map<String, Object> equalsMap = new HashMap<String, Object>();
		equalsMap.put("proNo", proNo);
		List<Product> pList = pdao.findEntity(equalsMap);
		if(pList.size() != 0) {
			sb.append(pList.get(0).getProName());
		} else {
			sb.append("error");
		}
		return sb.toString();
	}
	
	public String preAddProcNo(String procNo) {
		StringBuffer sb = new StringBuffer("");
		ProcessesDAO pdao = new ProcessesDAO();
		Map<String, Object> equalsMap = new HashMap<String, Object>();
		equalsMap.put("procNo", procNo);
		List<Processes> pList = pdao.findEntity(equalsMap);
		if(pList.size() != 0) {
			sb.append(pList.get(0).getProcName());
		} else {
			sb.append("error");
		}
		return sb.toString();
	}
	
	public String preAddStaNo(String staNo) {
		StringBuffer sb = new StringBuffer("");
		StaffDAO sdao = new StaffDAO();
		Map<String, Object> equalsMap = new HashMap<String, Object>();
		equalsMap.put("staNo", staNo);
		List<Staff> sList = sdao.findEntity(equalsMap);
		if(sList.size() != 0) {
			sb.append(sList.get(0).getStaName());
		} else {
			sb.append("error");
		}
		return sb.toString();
	}
}
