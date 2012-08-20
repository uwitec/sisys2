package com.sisys.service;

import java.sql.SQLException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sisys.bean.Batch;
import com.sisys.bean.Product;
import com.sisys.bean.ScheduleTab;
import com.sisys.dao.BatchDAO;
import com.sisys.dao.ProductDAO;
import com.sisys.dao.ScheduleTabDAO;






public class SearchPdService {
	
	List<DisqProduct> disqProduct=new ArrayList<DisqProduct>();
	 String proName=new String();
	 int totalNum=0;
	 int disqNum=0;
	 double disqPercent=0.00;
	String sql;

	
	public Map<String,Object> SearchPd(String proNo,String starttime,String endtime) throws SQLException{
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
		proName=product.get(0).getProName();
		System.out.println(product.size());
		int proId= product.get(0).getId();
	//在batch中查找批次ID信息	
		BatchDAO batchDAO =new BatchDAO();
		sql="select * from batch where proId='"+proId+"'and ('"+starttime+"'<=endTime or '"+endtime+"'>=startTime) and isDelete='0' and status in(1,4)";
		List<Batch> batch = new ArrayList<Batch>();
		batch=batchDAO.findEntityByList(sql);
		if(batch.size() == 0){
			map.put("result", "error");
			map.put("message", "时间段内批次不存在！请重新输入！");
			return map;
		}
		System.out.println(batch);		
		for(int i=0;i<batch.size();i++){
			DisqProduct tmp=new DisqProduct();
			tmp.batchNo=batch.get(i).getBatchNo();
			tmp.totalNum=batch.get(i).getTotalNum();
			ScheduleTabDAO scheduleTabDAO = new ScheduleTabDAO();
			List<ScheduleTab> scheduleTab=new ArrayList<ScheduleTab>();
			Map<String, Integer> equalsmap1 = new HashMap<String, Integer>();
			equalsmap1.put("batchId", batch.get(i).getId());
			scheduleTab=scheduleTabDAO.findEntity(equalsmap1);
			//计算批次的所有不合格品数量
			int batchDisqNum=0;
			for(int j=0;j<scheduleTab.size();j++){
				batchDisqNum+=scheduleTab.get(j).getDisqNum();
			}
			tmp.disqNum=batchDisqNum;
			double d = (double)((tmp.disqNum*100)/tmp.totalNum);
			tmp.disqPercent= (tmp.disqNum*100)%tmp.totalNum == 0 ? d/100 : (d+1)/100;
			
			disqProduct.add(tmp);
			
			disqNum+=tmp.disqNum;
			totalNum+=tmp.totalNum;
		}	
		double d = (double)((disqNum*100)/totalNum);
		disqPercent= (disqNum*100)%totalNum == 0 ? d/100 : (d+1)/100;
		System.out.println(disqPercent);
		
		map.put("result", "success");
		map.put("disqProduct", disqProduct);
		map.put("proName", proName);
		map.put("totalNum", totalNum);
		map.put("disqNum", disqNum);
		map.put("disqPercent", disqPercent);
		
		return map;
	}
	
	class DisqProduct{
		public String batchNo;
		public int totalNum;
		public int disqNum;
		public double disqPercent;
	}
	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws SQLException, ParseException{
		SearchPdService s=new SearchPdService();
		List<Batch> list=new ArrayList<Batch>();
		String proName =new String();
		Map<String,Object> map = new HashMap<String, Object>();
		map=s.SearchPd("1","2012-07-10","2012-07-12");
		
		list=(List<Batch>) map.get("list");
		proName=(String) map.get("proName");
		System.out.println(proName);
		Iterator<Batch> it =list.iterator();
		//list.iterator();
		while(it.hasNext())
		System.out.println(it.next());
	}
	
	
}
