package com.sisys.bean;

import java.util.Date;

/**
 * 
 * @author huangxin 员工信息
 */
public class Staff {

	private Integer id = null;
	private int deptId;
	private String kind;
	private String staName;
	private String staNo;
	private int isDelete;
	private Date deleteTime;

	// get和set方法
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getStaName() {
		return staName;
	}

	public void setStaName(String staName) {
		this.staName = staName;
	}

	public String getStaNo() {
		return staNo;
	}

	public void setStaNo(String staNo) {
		this.staNo = staNo;
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
		result = prime * result + deptId;
		result = prime * result + id;
		result = prime * result + isDelete;
		result = prime * result + ((kind == null) ? 0 : kind.hashCode());
		result = prime * result + ((staName == null) ? 0 : staName.hashCode());
		result = prime * result + ((staNo == null) ? 0 : staNo.hashCode());
		return result;
	}

	// 构造函数
	public Staff(int id, int deptId, String kind, String staName, String staNo,
			int isDelete, Date deleteTime) {
		super();
		this.id = id;
		this.deptId = deptId;
		this.kind = kind;
		this.staName = staName;
		this.staNo = staNo;
		this.isDelete = isDelete;
		this.deleteTime = deleteTime;
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
		Staff other = (Staff) obj;
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
		if (kind != other.kind)
			return false;
		if (staName == null) {
			if (other.staName != null)
				return false;
		} else if (!staName.equals(other.staName))
			return false;
		if (staNo == null) {
			if (other.staNo != null)
				return false;
		} else if (!staNo.equals(other.staNo))
			return false;
		return true;
	}

	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 转换为字符串
	@Override
	public String toString() {
		return "Staff [id=" + id + ", deptId=" + deptId + ", kind=" + kind
				+ ", staName=" + staName + ", staNo=" + staNo + ", isDelete="
				+ isDelete + ", deleteTime=" + deleteTime + "]";
	}

}
