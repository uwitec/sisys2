package com.sisys.action;

public class LoginAction extends BaseAction{
	private String level;
	private String username;
	private String password;
	
	@Override
	public String execute() throws Exception{
		level = request.getParameter("level");
		username = request.getParameter("username");
		password = request.getParameter("password");
		System.out.println(level + username + password);
		
		//LoginService loginService = new LoginService();
		//UserBean user = loginService.checkLogin(username, password, Integer.valueOf(level));
		/*if(user != null){
			return SUCCESS;
		}*/
		return INPUT;
		
	}
	
	public void setLevel(String level){
		this.level = level;
	}
	public String getLevel(){
		return level;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
}
