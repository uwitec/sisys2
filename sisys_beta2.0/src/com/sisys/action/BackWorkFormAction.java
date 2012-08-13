package com.sisys.action;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sisys.bean.BWFstandard;
import com.sisys.bean.BackWorkForm;
import com.sisys.bean.User;
import com.sisys.dao.BackWorkFormDAO;
import com.sisys.service.BackWorkFormService;


@SuppressWarnings("serial")
public class BackWorkFormAction extends BaseAction {
	private String proNo;
	private String batchNo;
	private String procNo;
	private String staNo;
	private String kind;
	private double workHours;
	private String checkNo;
	private String respNo;
	
	private String level;
	private String name;
	private BackWorkFormService wfs = new BackWorkFormService();
	
	public String execute() throws Exception {
		return null;
	}
	
	public String backFormAddIndex(){
		Map<String,Object> resultMap = wfs.backFormAddIndex();
		if(resultMap.get("result").equals("error"))
			return ERROR;
		String result = request.getParameter("result");
		System.out.println(result);
		request.setAttribute("result", result);
		request.setAttribute("kindList", resultMap.get("kindList"));
		return SUCCESS;
	}

	public String backFormAdd(){
		BackWorkForm backWorkForm = new BackWorkForm();
		name = ((User)session.getAttribute("user")).getUsername();
		backWorkForm.setProNo(proNo);
		backWorkForm.setBatchNo(batchNo);
		backWorkForm.setProcNo(procNo);
		backWorkForm.setStaNo(staNo);
		backWorkForm.setName(name);
		backWorkForm.setKind(kind);
		backWorkForm.setWorkHours(workHours);
		backWorkForm.setCheckNo(checkNo);
		backWorkForm.setRespNo(respNo);
		backWorkForm.setTime(new Date());
		
		String result = wfs.backFormAdd(backWorkForm);
		return result;
	}
	
	public String backFormSearch(){
		String key = request.getParameter("key");
		Map<String,Object> resultMap = wfs.backFormSearch(key, staNo);
		request.setAttribute("resultMap", resultMap);
		if(resultMap.get("result").equals("error")){
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String backFormShow(){
		String id = request.getParameter("id");
		if(id == null){
			return ERROR;
		}
		Map<String,Object> resultMap = wfs.backFormShow(id);
		if(resultMap.get("result").equals("error")){
			return ERROR;
		}
		request.setAttribute("bwf", resultMap.get("bwf"));
		return SUCCESS;
	}
	
	public String backFormDelete(){
		String id = request.getParameter("id");
		if(id == null){
			return ERROR;
		}
		String result = wfs.backFormDelete(id);
		return result;
	}
	
	public String backFormUpdate(){
		BackWorkForm backWorkForm = new BackWorkForm();
		String id = request.getParameter("id");
		
		backWorkForm.setId(Integer.parseInt(id));
		backWorkForm.setProNo(proNo);
		backWorkForm.setBatchNo(batchNo);
		backWorkForm.setProcNo(procNo);
		backWorkForm.setStaNo(staNo);
		backWorkForm.setName(name);
		backWorkForm.setKind(kind);
		backWorkForm.setWorkHours(workHours);
		backWorkForm.setCheckNo(checkNo);
		backWorkForm.setRespNo(respNo);
		backWorkForm.setTime(new Date());
		
		Map<String,Object> resultMap = wfs.backFormUpdate(backWorkForm);
		request.setAttribute("bwf", resultMap.get("bwf"));
		
		return resultMap.get("result").toString();
	}
	
	
	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getProcNo() {
		return procNo;
	}

	public void setProcNo(String procNo) {
		this.procNo = procNo;
	}

	public String getStaNo() {
		return staNo;
	}

	public void setStaNo(String staNo) {
		this.staNo = staNo;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public double getWorkHours() {
		return workHours;
	}

	public void setWorkHours(double workHours) {
		this.workHours = workHours;
	}

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public String getRespNo() {
		return respNo;
	}

	public void setRespNo(String respNo) {
		this.respNo = respNo;
	}
	
	public String getLevel() {
		User user = (User)session.getAttribute("user");
		switch(user.getLevel()){
		case 1:
			level = "viewer";
			break;
		case 2:
			level = "operator";
			break;
		case 3:
			level = "admin";
			break;
		}
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
