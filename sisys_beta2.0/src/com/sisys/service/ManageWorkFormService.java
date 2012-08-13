package com.sisys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sisys.bean.Batch;
import com.sisys.bean.Flowpath;
import com.sisys.bean.Processes;
import com.sisys.bean.Product;
import com.sisys.bean.Staff;
import com.sisys.dao.BatchDAO;
import com.sisys.dao.FlowpathDAO;
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
	
	public String preAddProcNo(String procNo, String proNo, String batNo) {
		String sql;
		sql = "select * from product where proNo='" + proNo + "'";
		ProductDAO prod = new ProductDAO();
		List<Product> prolist = prod.findEntityByList(sql);
		sql = "select * from batch where batchNo='" + batNo + "' and proId=" + prolist.get(0).getId();
		BatchDAO batd = new BatchDAO();
		List<Batch> batlist = batd.findEntityByList(sql);
		if (batlist.size() == 0) {
			return "error";
		}
		sql = "select * from flowpath where Id=" + batlist.get(0).getFlowId();
		FlowpathDAO fpd = new FlowpathDAO();
		List<Flowpath> fplist = fpd.findEntityByList(sql);
		String[] seq;
		seq = fplist.get(0).getSequence().split("-");
		if (Integer.parseInt(procNo) > seq.length) {
			return "error";
		}
		sql = "select * from processes where Id=" + seq[Integer.parseInt(procNo) - 1];
		ProcessesDAO pd = new ProcessesDAO();
		List<Processes> plist = pd.findEntityByList(sql);
		if (plist.size() == 0) {
			return "error";
		}
		return plist.get(0).getProcName();
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
