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
import com.sisys.bean.ProductLine;
import com.sisys.bean.ScheduleTab;

import com.sisys.dao.BatchDAO;

import com.sisys.dao.ProductDAO;
import com.sisys.dao.ProductLineDAO;
import com.sisys.dao.ScheduleTabDAO;





public class SearchPd5Service {
	
	List<ProLineDisq> list=new ArrayList<ProLineDisq>();
	 String proLineName=new String();
	 int completeNum=0;
	 int disqNum=0;
	 double disqPercent=0.00;
	String sql;

	
	public Map<String,Object> SearchPd5(String lineNo,String starttime,String endtime) throws SQLException{
		Map<String,Object> map = new HashMap<String, Object>();

		//在生产线表中查找proID
		ProductLineDAO productLineDAO =new ProductLineDAO();
		List<ProductLine> productLine = new ArrayList<ProductLine>();
		Map<String, Object> equalsmap = new HashMap<String, Object>();
		equalsmap.put("LineNo", lineNo);
		equalsmap.put("isDelete", 0);
		productLine=productLineDAO.findEntity(equalsmap);
		if(productLine.size() == 0){
			map.put("result", "error");
			map.put("message", "生产线编号不存在！请重新输入！");
			return map;
		}
		proLineName=productLine.get(0).getLineDesc();
		System.out.println(proLineName);
		int proLineId=productLine.get(0).getId();
		//查找产品表，得到生产线所有产品	
		ProductDAO productDAO=new ProductDAO();
		List<Product> product = new ArrayList<Product>();
		sql="select * from product where prolineId='"+proLineId+"' and isDelete='0'";
		product=productDAO.findEntityByList(sql);
		if(product.size() == 0){
			map.put("result", "error");
			map.put("message", "该生产线没有产品");
			return map;
		}
		//查找批次表，得到这段时间内生产的产品批次
		for(int i=0;i<product.size();i++){
			ProLineDisq tmp=new ProLineDisq();
			tmp.proNo=product.get(i).getProNo();
			tmp.proName=product.get(i).getProName();
			List<Batch> batch=new ArrayList<Batch>();
			BatchDAO batchDAO=new BatchDAO();
			String sql1="select * from batch where proId ='"+product.get(i).getId()+"' and endtime between '"+starttime+"' and '"+endtime+"' and isDelete='0' and status in(1,3,4)";
			batch=batchDAO.findEntityByList(sql1);
			if(batch.size() == 0){
				continue;
			}			
			//查找进度表，得到每一个批次生产产品数量情况
			for(int j=0;j<batch.size();j++){
				tmp.totalNum=tmp.totalNum+batch.get(j).getTotalNum();
				List<ScheduleTab> scheduleTab=new ArrayList<ScheduleTab>();
				ScheduleTabDAO scheduleTabDAO=new ScheduleTabDAO();
				String sql2="select * from scheduleTab where batchId ='"+batch.get(j).getId()+"'and isEnd='1'";
				scheduleTab=scheduleTabDAO.findEntityByList(sql2);
				if(scheduleTab.size() == 0){
					map.put("result", "error");
					map.put("message", "时间段内没有产品");
					return map;
				}
				//temp_totalNum+=scheduleTab.get(j).getDisqNum();
				tmp.disqNum+=scheduleTab.get(0).getDisqNum()+scheduleTab.get(0).getNum();
			}
				if (tmp.totalNum==0){
					continue;
				}	
				else{
						double d = (double)((tmp.disqNum*100)/tmp.totalNum);
					 tmp.disqPercent= (tmp.disqNum*100)%tmp.totalNum <5 ? d/100 : (d+1)/100;
						System.out.println(tmp.disqPercent);
				}
				list.add(tmp);
				completeNum+=tmp.totalNum;
				disqNum+=tmp.disqNum;
		}
		
			if (completeNum==0){
				map.put("result", "error");
				map.put("message", "部门没有产品！请重新输入！");
				return map;	
			}
			else{
		double d = (double)((disqNum*100)/completeNum);
		disqPercent= (disqNum*100)%completeNum <5 ? d/100 : (d+1)/100;
			}						
		map.put("result", "success");
		map.put("list", list);
		map.put("lineDesc", proLineName);
		map.put("completeNum", completeNum);
		map.put("disqNum", disqNum);
		map.put("disqPercent", disqPercent);
		
		return map;
	}
	class ProLineDisq{
		public String proName;
		public String proNo;
		public int totalNum;
		public int disqNum;
		public double disqPercent;
		
	}

	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws SQLException, ParseException{
		SearchPd4Service s=new SearchPd4Service();
		List<Product> list=new ArrayList<Product>();
		String deptName =new String();
		Map<String,Object> map = new HashMap<String, Object>();
		map=s.SearchPd4("5","2012-07-10","2012-07-16");
		
		list=(List<Product>) map.get("list");
		deptName=(String) map.get("deptName");
		System.out.println(deptName);
		Iterator<Product> it =list.iterator();
		//list.iterator();
		while(it.hasNext())
		System.out.println(it.next().getProName());
	}
	
	
}
