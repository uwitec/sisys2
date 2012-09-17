package com.sisys.bean;

import java.util.Date;

/**
 * 
 * @author ganjun 系统用户信息
 */
public class User {

	private Integer id = null;
	private String username;
	private String password;
	private int level; // 用户权限，1代表查看人员，2代表普通统计员，3代表管理员
	private int isDelete;
	private Date deleteTime;
	private String deptName;

	// get和set方法
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setLevel(String level) {
		System.out.println(level);
		this.level = Integer.parseInt(level);
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	// 构造函数
	public User(int id, String username, String password, int level,
			int isDelete, Date deleteTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.level = level;
		this.isDelete = isDelete;
		this.deleteTime = deleteTime;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 得到hashCode
	

	// 判断两个类是否相同
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deleteTime == null) ? 0 : deleteTime.hashCode());
		result = prime * result + ((deptName == null) ? 0 : deptName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + isDelete;
		result = prime * result + level;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (deleteTime == null) {
			if (other.deleteTime != null)
				return false;
		} else if (!deleteTime.equals(other.deleteTime))
			return false;
		if (deptName == null) {
			if (other.deptName != null)
				return false;
		} else if (!deptName.equals(other.deptName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isDelete != other.isDelete)
			return false;
		if (level != other.level)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	// 转换为字符串
	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", level=" + level + ", isDelete=" + isDelete
				+ ", deleteTime=" + deleteTime + ", deptName=" + deptName + "]";
	}

	public void setDeptName(String deptNo) {
		this.deptName = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

}
