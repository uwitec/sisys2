package com.sisys.bean;

import java.util.Date;

public class Product {
	private int id;
	private int deptId;
	private int prolineId; // 生产线ID号
	private String proNo;
	private String proName;
	private int proCycle; //生产周期
	private int isDelete;
	private Date deleteTime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public int getProlineId() {
		return prolineId;
	}
	public void setProlineId(int prolineId) {
		this.prolineId = prolineId;
	}
	public String getProNo() {
		return proNo;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public int getProCycle() {
		return proCycle;
	}
	public void setProCycle(int proCycle) {
		this.proCycle = proCycle;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deleteTime == null) ? 0 : deleteTime.hashCode());
		result = prime * result + deptId;
		result = prime * result + id;
		result = prime * result + isDelete;
		result = prime * result + proCycle;
		result = prime * result + ((proName == null) ? 0 : proName.hashCode());
		result = prime * result + ((proNo == null) ? 0 : proNo.hashCode());
		result = prime * result + prolineId;
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
		Product other = (Product) obj;
		if (deleteTime == null) {
			if (other.deleteTime != null)
				return false;
		} else if (!deleteTime.equals(other.deleteTime))
			return false;
		if (deptId != other.deptId)
			return false;
		if (id != other.id)
			return false;
		if (isDelete != other.isDelete)
			return false;
		if (proCycle != other.proCycle)
			return false;
		if (proName == null) {
			if (other.proName != null)
				return false;
		} else if (!proName.equals(other.proName))
			return false;
		if (proNo == null) {
			if (other.proNo != null)
				return false;
		} else if (!proNo.equals(other.proNo))
			return false;
		if (prolineId != other.prolineId)
			return false;
		return true;
	}
	public Product(int id, int deptId, int prolineId, String proNo,
			String proName, int proCycle, int isDelete, Date deleteTime) {
		super();
		this.id = id;
		this.deptId = deptId;
		this.prolineId = prolineId;
		this.proNo = proNo;
		this.proName = proName;
		this.proCycle = proCycle;
		this.isDelete = isDelete;
		this.deleteTime = deleteTime;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", deptId=" + deptId + ", prolineId="
				+ prolineId + ", proNo=" + proNo + ", proName=" + proName
				+ ", proCycle=" + proCycle + ", isDelete=" + isDelete
				+ ", deleteTime=" + deleteTime + "]";
	}
	
	
}
