package com.sisys.bean;

import java.util.Date;

/**
 * 
 * @author huangxin 工种信息
 */
public class StaffKind {

	private Integer id = null;
	private String kindDesc;
	private int isDelete;
	private Date deleteTime;

	// get和set方法
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKindDesc() {
		return kindDesc;
	}

	public void setKindDesc(String kindDesc) {
		this.kindDesc = kindDesc;
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
		result = prime * result + id;
		result = prime * result + isDelete;
		result = prime * result
				+ ((kindDesc == null) ? 0 : kindDesc.hashCode());
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
		StaffKind other = (StaffKind) obj;
		if (deleteTime == null) {
			if (other.deleteTime != null)
				return false;
		} else if (!deleteTime.equals(other.deleteTime))
			return false;
		if (id != other.id)
			return false;
		if (isDelete != other.isDelete)
			return false;
		if (kindDesc == null) {
			if (other.kindDesc != null)
				return false;
		} else if (!kindDesc.equals(other.kindDesc))
			return false;
		return true;
	}

	// 构造函数
	public StaffKind(int id, String kindDesc, int isDelete, Date deleteTime) {
		super();
		this.id = id;
		this.kindDesc = kindDesc;
		this.isDelete = isDelete;
		this.deleteTime = deleteTime;
	}

	public StaffKind() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 转换为字符串
	@Override
	public String toString() {
		return "StaffKind [id=" + id + ", kindDesc=" + kindDesc + ", isDelete="
				+ isDelete + ", deleteTime=" + deleteTime + "]";
	}

}
