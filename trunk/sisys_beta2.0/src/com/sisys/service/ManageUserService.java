package com.sisys.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.sisys.bean.Department;
import com.sisys.bean.User;
import com.sisys.dao.DepartmentDAO;
import com.sisys.dao.UserDAO;

public class ManageUserService {
	
	ActionContext actionContext = ActionContext.getContext();
	Map session = actionContext.getSession();
	HttpServletRequest request = (HttpServletRequest) actionContext
			.get(StrutsStatics.HTTP_REQUEST);

	
	//登录
	public String login(User user) {
		System.out.println(user);
		if(user.getLevel()==0 || user.getPassword()=="" || user.getUsername()== "") {
			return "empty";
		}
		UserDAO userDao = new UserDAO();
		Map<String, Object> equalsMap = new HashMap<String, Object>();
		equalsMap.put("username", user.getUsername());
		equalsMap.put("password", user.getPassword());
		equalsMap.put("level", user.getLevel());
		equalsMap.put("isDelete", 0);
		List<User> uList = userDao.findEntity(equalsMap);
		if(uList.size() == 0) {
			return "false";
		} else {
			User u = uList.get(0);
			ActionContext actionContext = ActionContext.getContext(); 
		    Map session = actionContext.getSession();
		    session.remove("user");
		    session.put("user", u);
		    if(u.getLevel() == 1) {
		    	return "viewer";
		    } else if(u.getLevel() == 2) {
		    	return "operator";
		    } else {
		    	return "admin";
		    }
		}
		//return "default";
	}
	
	//进入添加员工界面
	public String preAdd() {
		DepartmentDAO dDao = new DepartmentDAO();
		List<Department> dList = dDao.readAll();
		request.setAttribute("list", dList);
		if(dList != null && dList.size() > 0) {
			return "success";
		} else {
			return "noDept";
		}
		
	}
	
	//增加人员
	public String add(User user) {
		if(user.getLevel()==0 || user.getPassword()=="" || user.getUsername()=="") {
			return "empty";
		}
		System.out.println("user:" + user);
		
		UserDAO userDao = new UserDAO();
		int num = userDao.count();
		//System.out.println("num:" + num);
		user.setId(++num);
		Map<String, Object> equalsMap = new HashMap<String, Object>();
		equalsMap.put("username", user.getUsername());
		equalsMap.put("level", user.getLevel());
		UserDAO userDao1 = new UserDAO();
		List<User> uList = userDao1.findEntity(equalsMap);
		//System.out.println("uList:" + uList);
		if(uList.size()!=0) {
			return "nameError";
		} else if(user.getLevel()==2 && "无".equals(user.getDeptName())) {
			return "false";
		} else {
			
			UserDAO userDao2 = new UserDAO();
			int row = userDao2.create(user);
			//System.out.println("row:" + row);
			if(row == 1) {
				
				//记录管理员操作信息
				ActionContext actionContext = ActionContext.getContext(); 
			    Map session = actionContext.getSession();
			    //LogInfo logInfo = new LogInfo();
			    User user1 = (User)session.get("user");
			    String content = "管理员" + user1.getUsername() + "增加用户。用户名称：" + user.getUsername()
			    			+ ",用户等级：" + (user.getLevel()==1 ? "查看人员" : (user.getLevel()==2? "录入人员" : "管理员"));
			    //logInfo.saveLog(user1, content, System.currentTimeMillis());
			    
				
				return "success";
			} else {
				return "false";
			}
		}				
	}
	
	//修改个人信息
	public String modify(User user) {
		
		if(user.getPassword()=="" || user.getUsername()=="") {
			return "empty";
		}

	    Map<String, Object> equalsMap = new HashMap<String, Object>();
		equalsMap.put("username", user.getUsername());
		equalsMap.put("level", user.getLevel());
		UserDAO userDao1 = new UserDAO();
		List<User> uList = userDao1.findEntity(equalsMap);
		//System.out.println("uList:" + uList);
		if(uList.size()!=0) {
			return "nameError";
		}
		
		ActionContext actionContext = ActionContext.getContext(); 
	    Map session = actionContext.getSession();
	    User u = (User) session.get("user");
	    UserDAO userDao = new UserDAO();
	    user.setId(u.getId());
	    user.setLevel(u.getLevel());
	    
		int num = userDao.update(user);
		if(num == 1) {
		    session.put("user", user);
			return "success";
		} else {
			return "false";
		}
		
	}
}
