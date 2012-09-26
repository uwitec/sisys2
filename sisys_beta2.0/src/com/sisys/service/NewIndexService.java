package com.sisys.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sisys.bean.Batch;
import com.sisys.bean.Department;
import com.sisys.bean.Product;
import com.sisys.dao.BatchDAO;
import com.sisys.dao.DepartmentDAO;
import com.sisys.dao.ProductDAO;


public class NewIndexService {
	
	Map<String,Object> map=new HashMap<String,Object>();
	List<Department> department=new ArrayList<Department>();
	List<Product> product_ing=new ArrayList<Product>();
	List<String> product_ing_deptNo=new ArrayList<String>();
	
	List<Product> product_od=new ArrayList<Product>();
	List<String> product_od_deptNo=new ArrayList<String>();
	
	public Map<String,Object> NewIndex(){
		map.put("result", "success");
		map.put("error_ing", "success");
		map.put("error_od", "success");
		DepartmentDAO departmentDAO=new DepartmentDAO();
		Map<String,Integer> equalsmap=new HashMap<String,Integer>();
		equalsmap.put("isDelete", 0);
		department=departmentDAO.findEntity(equalsmap);
		if(department.size() == 0){
			map.put("result", "error");
			map.put("message", "请导入数据！");
			return map;
		}
		for(int i = 0;i < department.size();i++){
			if(department.get(i).getDeptNo().equals("0")){
				department.remove(i);
			}
		}
		map.put("department",department);
//查找正在生产产品
		
		List<Batch> batch1=new ArrayList<Batch>();
		BatchDAO batch1DAO=new BatchDAO();
		String sql="select * from batch where status='0' group by proId";
		batch1=batch1DAO.findEntityByList(sql);
		if(batch1.size() == 0){
			map.put("error_ing", "error");
			map.put("message_ing", "没有正在生产的批次！");
		}
		//map.put("PRbatch", batch1);
		for(int i=0;i<batch1.size();i++){
			ProductDAO productDAO=new ProductDAO();
			List<Product> product1 =new ArrayList<Product>();
			Map<String,Integer> equalsmap1=new HashMap<String,Integer>();		
			equalsmap1.put("isDelete", 0);
			equalsmap1.put("Id", batch1.get(i).getProId());
			product1=productDAO.findEntity(equalsmap1);
			if(product1.size() == 0){
				map.put("error_ing", "error");
				map.put("message_ing", "没有正在生产批次对应产品！");
				return map;
			}
			//查找产品所属部门编号
			DepartmentDAO department1DAO=new DepartmentDAO();
			List<Department> department1 =new ArrayList<Department>();
			Map<String,Integer> equalsmap_temp=new HashMap<String,Integer>();		
			equalsmap_temp.put("isDelete", 0);
			equalsmap_temp.put("Id", product1.get(i).getDeptId());
			department1 =department1DAO.findEntity(equalsmap_temp);
			product_ing_deptNo.add(department1.get(0).getDeptNo());
			product_ing.add(product1.get(0));
			
		
		}
			map.put("product_ing_deptNo", product_ing_deptNo);
			map.put("product_ing", product_ing);			
		
	//查找超期产品
			List<Batch> batch=new ArrayList<Batch>();
			BatchDAO batchDAO=new BatchDAO();
			String sql1="select * from batch where status='2' group by proId";
			batch=batchDAO.findEntityByList(sql1);
			if(batch.size() == 0){
				map.put("error_od", "error");
				map.put("message_od", "没有超期批次！");
			}
			for(int i=0;i<batch.size();i++){
				ProductDAO productDAO=new ProductDAO();
				List<Product> product2 =new ArrayList<Product>();
				Map<String,Integer> equalsmap2=new HashMap<String,Integer>();		
				equalsmap2.put("isDelete", 0);
				equalsmap2.put("Id", batch.get(i).getProId());
				product2=productDAO.findEntity(equalsmap2);
				if(product2.size()==0){
					map.put("error_od", "error");
					map.put("message_od", "没有超期批次对应产品！");
					return map;
				}
				//查找产品所属部门编号
				DepartmentDAO department2DAO=new DepartmentDAO();
				List<Department> department2 =new ArrayList<Department>();
				Map<String,Integer> equalsmap_temp=new HashMap<String,Integer>();		
				equalsmap_temp.put("isDelete", 0);
				equalsmap_temp.put("Id", product2.get(i).getDeptId());
				department2 =department2DAO.findEntity(equalsmap_temp);
				product_od_deptNo.add(department2.get(0).getDeptNo());
				
				product_od.add(product2.get(0));
			}
				map.put("product_od_deptNo", product_od_deptNo);	
				map.put("product_od", product_od);		
		return map;
	}
	
	public Map<String, Object> updateStatus(){
		Date now = new Date();
		List<Batch> batchList = new ArrayList<Batch>();
		BatchDAO batchDAO = new BatchDAO();
		Map<String,Integer> equalMap = new HashMap<String,Integer>();
		equalMap.put("status", 0);
		equalMap.put("isDelete", 0);
		batchList = batchDAO.findEntity(equalMap);
		/*if(batchList.size() == 0){
			map.put("result", "error");
			map.put("message", "请导入数据！");
			return map;
		}*/
		
		for(int i = 0;i < batchList.size() ;i++){
			if(batchList.get(i).getEndTime().compareTo(now) < 0){
				batchList.get(i).setStatus(2);
				BatchDAO batchDAO1=new BatchDAO();
				batchDAO1.update(batchList.get(i), i);
			}
		}
		return map;
	}
}
