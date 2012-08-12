package com.sisys.service;

import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sisys.bean.Batch;
import com.sisys.bean.Product;
import com.sisys.bean.ScheduleTab;
import com.sisys.dao.BatchDAO;
import com.sisys.dao.ProductDAO;
import com.sisys.dao.ScheduleTabDAO;






public class SearchJdService {
	
	List<ScheduleTab> list = new ArrayList<ScheduleTab>();
	private String proName;
	private String batchNo;
	private int status;
	String sql;

	
	public Map<String,Object> SearchJd(String proNo,String starttime,String endTime) throws SQLException, ParseException{
		Map<String,Object> map = new HashMap<String, Object>();
		//在product表中查找proName和Id
	
		ProductDAO productDAO=new ProductDAO();
		List<Product> product = new ArrayList<Product>();
		Map<String, String> equalsmap = new HashMap<String, String>();
		equalsmap.put("proNo", proNo);
		product=productDAO.findEntity(equalsmap);
		if(product.size() == 0){
			map.put("result", "error");
			map.put("message", "产品编号不存在！请重新输入！");
			return map;
		}
					
		proName= product.get(0).getProName();
		int proId=product.get(0).getId();
	
		//在batch表中中查找batchId	
		BatchDAO batchDAO =new BatchDAO();
		List<Batch> batch = new ArrayList<Batch>();
		String sql = "select * from batch where proId = " + proId + " and status in (0,1,2) order by status";
		batch=batchDAO.findEntityByList(sql);
		if(batch.size() == 0){
			map.put("result", "error");
			map.put("message", "未找到该产品对应批次信息！");
			return map;
		}
		
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		for(int i = 0;i < batch.size();i++){
			Map<String,Object> jdMap = new HashMap<String,Object>();
			int batchId=batch.get(i).getId();
			status=batch.get(i).getStatus();
			
			//在schedule中查找进度信息
			ScheduleTabDAO scheduleTabDAO=new ScheduleTabDAO();
			//	List<ScheduleTab> scheduleTab=new ArrayList<ScheduleTab>();
			
			sql="select * from scheduletab where batchId='"+batchId+"' and time>='"+starttime+"'and time<='"+endTime+"'";		
			list=scheduleTabDAO.findEntityByList(sql);
			if(status == 1 && list.size() == 0){
				continue;
			}
			jdMap.put("list", list);
			jdMap.put("status", status);
			jdMap.put("batchNo", batch.get(i).getBatchNo());
			
			listMap.add(jdMap);
		}
		
		map.put("proName", proName);
		map.put("proNo", proNo);
		map.put("result", "success");
		map.put("listMap", listMap);
		map.put("starttime", starttime);
		map.put("endTime", endTime);
		System.out.println(map);
		
		return map;
	}
	
	public Map ShowJd(Map mapJd) throws Exception{
		List<Map<String,Object>> listMap = (List<Map<String,Object>>) mapJd.get("listMap");
		List<String> dateNo = new ArrayList<String>();
		
		Calendar startTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		System.out.println(mapJd.get("starttime"));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		startTime.setTime(format.parse(mapJd.get("starttime").toString()));
		endTime.setTime(format.parse(mapJd.get("endTime").toString()));
		while(startTime.compareTo(endTime) <= 0){
			dateNo.add(startTime.getTime().getMonth()+1 + "-" + startTime.getTime().getDate());
			startTime.add(Calendar.DATE, 1);
		}
		
		Calendar curTime = Calendar.getInstance();
		for(int i = 0;i < listMap.size();i++){
			List<ScheduleTab> list = (List<ScheduleTab>)listMap.get(i).get("list");
			Map<Integer,Map<String,ScheduleTab>> jdMap= new HashMap<Integer,Map<String,ScheduleTab>>();
			int count = 1;
			while(list.size() != 0){
				Map<String,ScheduleTab> map = new HashMap<String,ScheduleTab>();
				for(int j = 0;j < list.size();j++){
					ScheduleTab sc = list.get(j);
					curTime.setTime(sc.getTime());
					String day = curTime.getTime().getMonth()+1 + "-" + curTime.getTime().getDate();
					if(map.containsKey(day)){
						continue;
					}
					map.put(day, sc);
					list.remove(j);
				}
				jdMap.put(count, map);
				count++;
			}
			
			listMap.get(i).put("jdMap", jdMap);
		}
		
		mapJd.put("dateNo", dateNo);
		return mapJd;
	}
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws Exception{
		SearchJdService s=new SearchJdService();
		List<ScheduleTab> list=new ArrayList<ScheduleTab>();
		String staName =new String();
		Map<String,Object> map = new HashMap<String, Object>();
		map=s.SearchJd("760402","2012-07-14","2012-07-16");
		
		map = s.ShowJd(map);
		
		System.out.println(map);
//		System.out.println(map.size());
//		list=(List<ScheduleTab>) map.get("list");
//		staName=(String) map.get("proName");
//		System.out.println(staName);
//		Iterator<ScheduleTab> it =list.iterator();
//		Calendar cl = Calendar.getInstance();
//		//list.iterator();
//		while(it.hasNext()){
//			cl.setTime(it.next().getTime());
//			System.out.println(cl.getTime().getDate());
//		}
	}
}
