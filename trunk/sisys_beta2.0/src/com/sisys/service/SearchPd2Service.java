package com.sisys.service;

import java.sql.SQLException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sisys.bean.Batch;
import com.sisys.bean.Processes;
import com.sisys.bean.Product;
import com.sisys.bean.ScheduleTab;
import com.sisys.dao.BatchDAO;
import com.sisys.dao.ProcessesDAO;
import com.sisys.dao.ProductDAO;
import com.sisys.dao.ScheduleTabDAO;




public class SearchPd2Service {
	List<DisqBatch> disqBatch =new ArrayList<DisqBatch>();
	 String proName=new String();
	 int completeNum=0;
	 int disqNum=0;
	 double disqPercent=0.00;
	
	String sql;
	
	public Map<String,Object> SearchPd2(String batchNo,String proNo,String starttime,String endtime) throws SQLException{
		Map<String,Object> map = new HashMap<String, Object>();
	
		//在产品表中查找proID
		ProductDAO productDAO=new ProductDAO();
		List<Product> product = new ArrayList<Product>();
		Map<String, String> equalsmap = new HashMap<String, String>();
		equalsmap.put("proNo", proNo);
		equalsmap.put("isDelete", "0");
		product=productDAO.findEntity(equalsmap);
		if(product.size() == 0){
			map.put("result", "error");
			map.put("message", "产品编号不存在！请重新输入！");
			return map;
		}
		System.out.println(product.size());
		
		proName=product.get(0).getProName();
		int proId=product.get(0).getId();
		
		//在batch中查找flowId
		BatchDAO batchDAO=new BatchDAO();
		List<Batch> batch = new ArrayList<Batch>();
		Map<String, Object> equalsmap1 = new HashMap<String, Object>();
		equalsmap1.put("batchNo", batchNo);
		equalsmap1.put("proId", proId);
		equalsmap1.put("isDelete", 0);
		batch=batchDAO.findEntity(equalsmap1);
		if(batch.size() == 0){
			map.put("result", "error");
			map.put("message", "批次编号不存在！请重新输入！");
			return map;
		}
		int batchId=batch.get(0).getId();
		
	//在ScheduleTab中查找工序信息	
		ScheduleTabDAO scheduleTabDAO =new ScheduleTabDAO();
		List<ScheduleTab> scheduleTab = new ArrayList<ScheduleTab>();
		Map<String, Object> equalsmap2 = new HashMap<String, Object>();
		equalsmap2.put("batchId", batchId);
		equalsmap2.put("isDelete", 0);
		scheduleTab=scheduleTabDAO.findEntity(equalsmap2);
		if(scheduleTab.size() == 0){
			map.put("result", "error");
			map.put("message", "流程编号不存在！请重新输入！");
			return map;
		}
		System.out.println(scheduleTab);
		
//封装每到工序的数据
		DisqBatch tmp=new DisqBatch();
		for(int i=0;i<scheduleTab.size();i++){
			ProcessesDAO processesDAO =new ProcessesDAO();
			List<Processes> processes = new ArrayList<Processes>();
			Map<String, Object> equalsmap3 = new HashMap<String, Object>();
			equalsmap3.put("Id", scheduleTab.get(i).getProcId());
			equalsmap3.put("isDelete", 0);
			processes=processesDAO.findEntity(equalsmap3);
			if(processes.size() == 0){
				map.put("result", "error");
				map.put("message", "工序不存在！请重新输入！");
				return map;
			}
			System.out.println(processes);
			tmp.procName=processes.get(0).getProcName();
			tmp.procNo=processes.get(0).getProcNo();
			tmp.disqNum=scheduleTab.get(i).getDisqNum();
			tmp.totalNum=scheduleTab.get(i).getDisqNum()+scheduleTab.get(i).getQuaNum();
			
			double d = (double)((tmp.disqNum*100)/tmp.totalNum);
			tmp.disqPercent= (tmp.disqNum*100)%tmp.totalNum == 0 ? d/100 : (d+1)/100;
			
			disqBatch.add(tmp);
		}
		
		//计算总量
		for(int i=0;i<disqBatch.size();i++){
			disqNum=disqNum+disqBatch.get(i).disqNum;
		}
		completeNum=disqBatch.get(0).totalNum+disqBatch.get(0).disqNum;
		double d = (double)((disqNum*100)/completeNum);
		disqPercent= (disqNum*100)%completeNum == 0 ? d/100 : (d+1)/100;
		
		map.put("result", "success");
		map.put("list", disqBatch);
		map.put("proName", proName);
		map.put("completeNum", completeNum);
		map.put("disqNum", disqNum);
		map.put("disqPercent", disqPercent);
		
		return map;
	}	
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws SQLException, ParseException{
		SearchPd2Service s=new SearchPd2Service();
		List<DisqBatch> list=new ArrayList<DisqBatch>();
		Map<String,Object> map = new HashMap<String, Object>();
		map=s.SearchPd2("1","1","2012-07-08","2012-08-08");
		
		list=(List<DisqBatch>) map.get("list");
		Iterator<DisqBatch> it =list.iterator();
		//list.iterator();
		while(it.hasNext())
		System.out.println(it.next().disqPercent);
	}
	
	
}

 class DisqBatch{
	
	public String procNo;
	public String procName;
	public int totalNum;
	public int disqNum;
	public double disqPercent;
	
	public DisqBatch(){
		super();
	}
}