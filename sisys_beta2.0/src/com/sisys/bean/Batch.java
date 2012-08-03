package com.sisys.bean;

import java.util.Date;

/**
 * 
 * @author huangxin 批次
 */
public class Batch {

	private int id;
	private String batchNo;
	private int flowId; // 流程Id号
	private int proId;
	private int status; // 0代表正在生产，1代表已完成，2代表超期未完成，3代表已处理,4代表超期完成
	private Date startTime;
	private Date endTime;
	private int totalNum; // 目标生产数量
	private String note;//备注，对超期批次的修改
	private int isDelete;
	private Date deleteTime;

	// get和set方法
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public int getFlowId() {
		return flowId;
	}

	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
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
	
	public Batch() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Batch(int id, String batchNo, int flowId, int proId, int status,
			Date startTime, Date endTime, int totalNum, String note,
			int isDelete, Date deleteTime) {
		super();
		this.id = id;
		this.batchNo = batchNo;
		this.flowId = flowId;
		this.proId = proId;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalNum = totalNum;
		this.note = note;
		this.isDelete = isDelete;
		this.deleteTime = deleteTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchNo == null) ? 0 : batchNo.hashCode());
		result = prime * result
				+ ((deleteTime == null) ? 0 : deleteTime.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + flowId;
		result = prime * result + id;
		result = prime * result + isDelete;
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + proId;
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + status;
		result = prime * result + totalNum;
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
		Batch other = (Batch) obj;
		if (batchNo == null) {
			if (other.batchNo != null)
				return false;
		} else if (!batchNo.equals(other.batchNo))
			return false;
		if (deleteTime == null) {
			if (other.deleteTime != null)
				return false;
		} else if (!deleteTime.equals(other.deleteTime))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (flowId != other.flowId)
			return false;
		if (id != other.id)
			return false;
		if (isDelete != other.isDelete)
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (proId != other.proId)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (status != other.status)
			return false;
		if (totalNum != other.totalNum)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Batch [id=" + id + ", batchNo=" + batchNo + ", flowId="
				+ flowId + ", proId=" + proId + ", status=" + status
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", totalNum=" + totalNum + ", note=" + note + ", isDelete="
				+ isDelete + ", deleteTime=" + deleteTime + "]";
	}
	
}
