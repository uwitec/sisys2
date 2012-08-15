package com.sisys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.sisys.bean.BWFstandard;
import com.sisys.bean.BackKind;
import com.sisys.bean.BackWorkForm;
import com.sisys.bean.Batch;
import com.sisys.bean.Processes;
import com.sisys.bean.Product;
import com.sisys.bean.Staff;
import com.sisys.dao.BackKindDAO;
import com.sisys.dao.BackWorkFormDAO;
import com.sisys.dao.BatchDAO;
import com.sisys.dao.ProcessesDAO;
import com.sisys.dao.ProductDAO;
import com.sisys.dao.StaffDAO;
import com.sisys.service.ManageWorkFormService;

public class BackWorkFormService {
	
	public Map<String,Object> backFormAddIndex(){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		BackKindDAO backKindDAO = new BackKindDAO();
		List<BackKind> kindList = backKindDAO.findAll();
		if(kindList.size() == 0){
			resultMap.put("result", "error");
			return resultMap;
		}
		resultMap.put("result", "success");
		resultMap.put("kindList", kindList);
		return resultMap;
	}
	
	public String backFormAdd(BackWorkForm backWorkForm){
		BackWorkFormDAO bwfDAO = new BackWorkFormDAO();
		BatchDAO batchDAO = new BatchDAO();
		if(backWorkForm != null){
			String batchNo = backWorkForm.getBatchNo();
			Map<String,String> equalsMap = new HashMap<String,String>();
			equalsMap.put("batchNo", batchNo);
			List<Batch> list = batchDAO.findEntity(equalsMap);
			if(list.size() == 0){
				return "batchError";
			}
			int result = bwfDAO.create(backWorkForm);
			if(result == 1){
				return "success";
			}
		}
		return "error";
	}
	
	public Map<String,Object> backFormSearch(String key,String staNo){
		BackWorkFormDAO bwfDAO = new BackWorkFormDAO();
		Map<String,String> equalsMap = new HashMap<String,String>();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<BWFstandard> bwfList = new ArrayList<BWFstandard>();
		
		if(key == null){
			resultMap.put("result", "error");
			resultMap.put("message", "查找失败！");
			return resultMap;
		}else{
			if(key.equals("check")){
				equalsMap.put("checkNo", staNo);
			}
			if(key.equals("resp")){
				equalsMap.put("respNo", staNo);
			}
			if(key.equals("sta")){
				equalsMap.put("staNo", staNo);
			}
			equalsMap.put("isDelete", "0");
			List<BackWorkForm> list = bwfDAO.findEntity(equalsMap);
			System.out.println(list);
			if(list.size() == 0){
				resultMap.put("result", "error");
				resultMap.put("message", "未找到符合条件的返工工单！");
				return resultMap;
			}
			bwfList = bwfTobwfs(list);
		}
		resultMap.put("result", "success");
		resultMap.put("list", bwfList);
		return resultMap;
	}
	
	public Map<String,Object> backFormShow(String id){
		BackWorkFormDAO bwfDAO = new BackWorkFormDAO();
		Map<String,String> equalsMap = new HashMap<String,String>();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<BWFstandard> bwfList = new ArrayList<BWFstandard>();
		
		equalsMap.put("Id", id);
		List<BackWorkForm> list = bwfDAO.findEntity(equalsMap);
		if(list.size() == 0){
			resultMap.put("result", "error");
			resultMap.put("message", "逻辑错误！");
			return resultMap;
		}
		bwfList = bwfTobwfs(list);
		resultMap.put("result", "success");
		resultMap.put("bwf", bwfList.get(0));
		return resultMap;
		
	}
	
	public String backFormDelete(String id){
		BackWorkFormDAO bwfDAO = new BackWorkFormDAO();
		Map<String,String> equalsMap = new HashMap<String,String>();
		
		equalsMap.put("Id", id);
		List<BackWorkForm> list = bwfDAO.findEntity(equalsMap);
		if(list.size() == 0){
			return "error";
		}
		bwfDAO = new BackWorkFormDAO();
		int result = bwfDAO.delete(list.get(0));
		if(result != 1){
			return "error";
		}
		return "success";
	}
	
	public Map<String,Object> backFormUpdate(BackWorkForm backWorkForm){
		BackWorkFormDAO bwfDAO = new BackWorkFormDAO();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		BatchDAO batchDAO = new BatchDAO();
		if(backWorkForm != null){
			String batchNo = backWorkForm.getBatchNo();
			Map<String,String> equalsMap = new HashMap<String,String>();
			equalsMap.put("batchNo", batchNo);
			List<Batch> list = batchDAO.findEntity(equalsMap);
			if(list.size() == 0){
				resultMap.put("result", "error");
			}else{
				int result = bwfDAO.update(backWorkForm, backWorkForm.getId());
				if(result == 1){
					resultMap.put("result", "success");
				}else{
					resultMap.put("result", "error");
				}
			}
		}
		bwfDAO = new BackWorkFormDAO();
		List<BWFstandard> bwfList = new ArrayList<BWFstandard>();
		Map<String,String> equalsMap = new HashMap<String,String>();
		equalsMap.put("Id", String.valueOf(backWorkForm.getId()));
		List<BackWorkForm> list = bwfDAO.findEntity(equalsMap);
		bwfList = bwfTobwfs(list);
		if(!resultMap.containsKey("result")){
			resultMap.put("result", "error");
		}
		resultMap.put("bwf", bwfList.get(0));
		return resultMap;
	}
	
	private List<BWFstandard> bwfTobwfs(List<BackWorkForm> list){
		if(list.size() == 0){
			return null;
		}
		List<BWFstandard> bwfList = new ArrayList<BWFstandard>();
		for(BackWorkForm bwf : list){
			BWFstandard bwfs = new BWFstandard();
			bwfs.setId(bwf.getId());
			bwfs.setProNo(bwf.getProNo());
			bwfs.setBatchNo(bwf.getBatchNo());
			bwfs.setProcNo(bwf.getProcNo());
			bwfs.setStaNo(bwf.getStaNo());
			bwfs.setKind(bwf.getKind());
			bwfs.setWorkHours(bwf.getWorkHours());
			bwfs.setName(bwf.getName());
			bwfs.setCheckNo(bwf.getCheckNo());
			bwfs.setRespNo(bwf.getRespNo());
			bwfs.setTime(bwf.getTime());
			bwfs.setIsDelete(bwf.getIsDelete());
			bwfs.setDeleteTime(bwf.getDeleteTime());
			
			Map<String,String> equalsMap = new HashMap<String,String>();
			
			ProductDAO productDAO = new ProductDAO();
			equalsMap.clear();
			equalsMap.put("proNo", bwf.getProNo());
			List<Product> proList = productDAO.findEntity(equalsMap);
			bwfs.setProName(proList.get(0).getProName());
			
			ManageWorkFormService mwf =new ManageWorkFormService();
			bwfs.setProcName(mwf.preAddProcNo(bwf.getProcNo(), bwf.getProNo(), bwf.getBatchNo()));
			/*ProcessesDAO processesDAO = new ProcessesDAO();
			equalsMap.clear();
			equalsMap.put("procNo", bwf.getProcNo());
			List<Processes> procList = processesDAO.findEntity(equalsMap);
			bwfs.setProcName(procList.get(0).getProcName());*/
			
			equalsMap.clear();
			equalsMap.put("staNo", bwf.getStaNo());
			List<Staff> staList = new StaffDAO().findEntity(equalsMap);
			bwfs.setStaName(staList.get(0).getStaName());
			
			equalsMap.put("staNo", bwf.getCheckNo());
			staList = new StaffDAO().findEntity(equalsMap);
			bwfs.setCheckName(staList.get(0).getStaName());
			
			equalsMap.put("staNo", bwf.getRespNo());
			staList = new StaffDAO().findEntity(equalsMap);
			bwfs.setRespName(staList.get(0).getStaName());
			
			bwfList.add(bwfs);
		}
		return bwfList;
	}
	
	public static void main(String[] args) {
		BackWorkFormService ss = new BackWorkFormService();
		ss.backFormSearch("sta", "51022");
	}
}
