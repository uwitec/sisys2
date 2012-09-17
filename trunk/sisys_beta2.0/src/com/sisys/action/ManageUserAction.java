package com.sisys.action;

import com.sisys.bean.User;
import com.sisys.service.ManageUserService;

public class ManageUserAction extends BaseAction{
	
	private ManageUserService mus = new ManageUserService();
	
	private User user = new User();
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String execute(){
		return null;
	}
	
	//登录
	public String login() {
		return mus.login(user);
		
	}
	
	//进入添加用户界面
	public String preAdd() {
		return mus.preAdd();
	}
	
	//增加用户
	public String add() {
		return mus.add(user);
	}
	
	//修改个人信息
	public String modify() {
		return mus.modify(user);
	}
}
