package com.sisys.bean;

import java.util.Date;

/**
 * 
 * @author ganjun 部门
 */
public class Department {

	private Integer id = null;
	private String deptNo;
	private String deptName;
	private int isDelete;
	private Date deleteTime;

	// get和set方法
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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

	// 得到hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deleteTime == null) ? 0 : deleteTime.hashCode());
		result = prime * result
				+ ((deptName == null) ? 0 : deptName.hashCode());
		result = prime * result + ((deptNo == null) ? 0 : deptNo.hashCode());
		result = prime * result + id;
		result = prime * result + isDelete;
		return result;
	}

	// 判断两个类是否相同
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
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
		if (deptNo == null) {
			if (other.deptNo != null)
				return false;
		} else if (!deptNo.equals(other.deptNo))
			return false;
		if (id != other.id)
			return false;
		if (isDelete != other.isDelete)
			return false;
		return true;
	}

	// 构造函数
	public Department(int id, String deptNo, String deptName, int isDelete,
			Date deleteTime) {
		super();
		this.id = id;
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.isDelete = isDelete;
		this.deleteTime = deleteTime;
	}

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 转换为字符串
	@Override
	public String toString() {
		return "Department [id=" + id + ", deptNo=" + deptNo + ", deptName="
				+ deptName + ", isDelete=" + isDelete + ", deleteTime="
				+ deleteTime + "]";
	}

}
