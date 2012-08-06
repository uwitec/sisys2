package com.sisys.action;


import java.util.HashMap;


import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.sisys.bean.User;
import com.sisys.service.NewIndexService;

public class NewIndexAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String level;
	Map<String,Object> mapNI=new HashMap<String,Object>(); 
	public String NewIndex() throws Exception {
		NewIndexService nis = new NewIndexService();
		mapNI=nis.updateStatus();
		if(mapNI.size()==0){
		mapNI=nis.NewIndex();
		}
		User user = (User)ActionContext.getContext().getSession().get("user");
		int level = user.getLevel();
		request.setAttribute("mapNI", mapNI);
		System.out.println(mapNI);
		if (mapNI.get("result").equals("success")) {
			switch(level){
			case 1:
				return "viewer";
			case 2:
				return "operator";
			case 3:
				return "admin";
			}
		} 
		return ERROR;		
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
