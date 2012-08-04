package com.sisys.bean;

import java.util.Date;

public class WorkForm {

	private int id;
	private int procId;
	private int batchId;
	private int quaNum;
	private int disquaNum;
	private Date time; // 记录工单存入系统的时间
	private int isDelete; // 标识是否删除，0未删除，1删除
	private Date deleteTime; // 若删除，则记录删除时间
	private String name;	//记录统计员姓名
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProcId() {
		return procId;
	}
	public void setProcId(int procId) {
		this.procId = procId;
	}
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public int getQuaNum() {
		return quaNum;
	}
	public void setQuaNum(int quaNum) {
		this.quaNum = quaNum;
	}
	public int getDisquaNum() {
		return disquaNum;
	}
	public void setDisquaNum(int disquaNum) {
		this.disquaNum = disquaNum;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + batchId;
		result = prime * result
				+ ((deleteTime == null) ? 0 : deleteTime.hashCode());
		result = prime * result + disquaNum;
		result = prime * result + id;
		result = prime * result + isDelete;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + procId;
		result = prime * result + quaNum;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		WorkForm other = (WorkForm) obj;
		if (batchId != other.batchId)
			return false;
		if (deleteTime == null) {
			if (other.deleteTime != null)
				return false;
		} else if (!deleteTime.equals(other.deleteTime))
			return false;
		if (disquaNum != other.disquaNum)
			return false;
		if (id != other.id)
			return false;
		if (isDelete != other.isDelete)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (procId != other.procId)
			return false;
		if (quaNum != other.quaNum)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	public WorkForm(int id, int procId, int batchId, int quaNum, int disquaNum,
			Date time, int isDelete, Date deleteTime, String name) {
		super();
		this.id = id;
		this.procId = procId;
		this.batchId = batchId;
		this.quaNum = quaNum;
		this.disquaNum = disquaNum;
		this.time = time;
		this.isDelete = isDelete;
		this.deleteTime = deleteTime;
		this.name = name;
	}
	public WorkForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "WorkForm [id=" + id + ", procId=" + procId + ", batchId="
				+ batchId + ", quaNum=" + quaNum + ", disquaNum=" + disquaNum
				+ ", time=" + time + ", isDelete=" + isDelete + ", deleteTime="
				+ deleteTime + ", name=" + name + "]";
	}
	
	
}
