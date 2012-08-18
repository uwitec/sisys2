package com.sisys.bean;

public class OutDueBatchCopy {

	private int id;
	private String proName;
	private String batchNo;
	private int isHandle;
	private int isOver;
	private String note;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public int getIsHandle() {
		return isHandle;
	}
	public void setIsHandle(int isHandle) {
		this.isHandle = isHandle;
	}
	public int getIsOver() {
		return isOver;
	}
	public void setIsOver(int isOver) {
		this.isOver = isOver;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "OutDueBatchCopy [id=" + id + ", proName=" + proName
				+ ", batchNo=" + batchNo + ", isHandle=" + isHandle
				+ ", isOver=" + isOver + ", note=" + note + "]";
	}
	
	
}
