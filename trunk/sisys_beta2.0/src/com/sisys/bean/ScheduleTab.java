package com.sisys.bean;

import java.util.Date;

/**
 * 
 * @author huangxin 进度表信息，汇总每天已完成工序及其数量。用于导出进度表
 */
public class ScheduleTab {

	private Integer id = null;
	private int batchId;
	private int procId;
	private Date time;
	private String colorNo;
	private int num;
	private int quaNum;
	private int disqNum;
	private int isEnd;

	// get和set方法
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getColorNo() {
		return colorNo;
	}

	public void setColorNo(String colorNo) {
		this.colorNo = colorNo;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	

	public int getProcId() {
		return procId;
	}

	public void setProcId(int procId) {
		this.procId = procId;
	}

	public int getQuaNum() {
		return quaNum;
	}

	public void setQuaNum(int quaNum) {
		this.quaNum = quaNum;
	}

	public int getDisqNum() {
		return disqNum;
	}

	public void setDisqNum(int disqNum) {
		this.disqNum = disqNum;
	}

	public int getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(int isEnd) {
		this.isEnd = isEnd;
	}

	
	// 得到hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + batchId;
		result = prime * result + ((colorNo == null) ? 0 : colorNo.hashCode());
		result = prime * result + disqNum;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + isEnd;
		result = prime * result + num;
		result = prime * result + procId;
		result = prime * result + quaNum;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	
	// 构造函数
	public ScheduleTab(Integer id, int batchId, int procId, Date time,
			String colorNo, int num, int quaNum, int disqNum, int isEnd) {
		super();
		this.id = id;
		this.batchId = batchId;
		this.procId = procId;
		this.time = time;
		this.colorNo = colorNo;
		this.num = num;
		this.quaNum = quaNum;
		this.disqNum = disqNum;
		this.isEnd = isEnd;
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
		ScheduleTab other = (ScheduleTab) obj;
		if (batchId != other.batchId)
			return false;
		if (colorNo == null) {
			if (other.colorNo != null)
				return false;
		} else if (!colorNo.equals(other.colorNo))
			return false;
		if (disqNum != other.disqNum)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isEnd != other.isEnd)
			return false;
		if (num != other.num)
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

	public ScheduleTab() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 转换为字符串
	@Override
	public String toString() {
		return "ScheduleTab [id=" + id + ", batchId=" + batchId + ", procId="
				+ procId + ", time=" + time + ", colorNo=" + colorNo + ", num="
				+ num + ", quaNum=" + quaNum + ", disqNum=" + disqNum
				+ ", isEnd=" + isEnd + "]";
	}

}
