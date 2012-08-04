package com.sisys.bean;

import java.util.Date;

public class SmallWf {
	private int Id;
	private int wfId;
	private String proNo;
	private int procId;
	private int quaNum;
	private String disqDetail;
	private String StaNo;
	private double bworkHours;
	private double salary;
	private int gWasteNum;
	private int lWasteNum;
	private Date time;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getWfId() {
		return wfId;
	}
	public void setWfId(int wfId) {
		this.wfId = wfId;
	}
	public String getProNo() {
		return proNo;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
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
	public String getDisqDetail() {
		return disqDetail;
	}
	public void setDisqDetail(String disqDetail) {
		this.disqDetail = disqDetail;
	}
	public String getStaNo() {
		return StaNo;
	}
	public void setStaNo(String staNo) {
		StaNo = staNo;
	}
	public double getBworkHours() {
		return bworkHours;
	}
	public void setBworkHours(double bworkHours) {
		this.bworkHours = bworkHours;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public int getgWasteNum() {
		return gWasteNum;
	}
	public void setgWasteNum(int gWasteNum) {
		this.gWasteNum = gWasteNum;
	}
	public int getlWasteNum() {
		return lWasteNum;
	}
	public void setlWasteNum(int lWasteNum) {
		this.lWasteNum = lWasteNum;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		result = prime * result + ((StaNo == null) ? 0 : StaNo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(bworkHours);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((disqDetail == null) ? 0 : disqDetail.hashCode());
		result = prime * result + gWasteNum;
		result = prime * result + lWasteNum;
		result = prime * result + ((proNo == null) ? 0 : proNo.hashCode());
		result = prime * result + procId;
		result = prime * result + quaNum;
		temp = Double.doubleToLongBits(salary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + wfId;
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
		SmallWf other = (SmallWf) obj;
		if (Id != other.Id)
			return false;
		if (StaNo == null) {
			if (other.StaNo != null)
				return false;
		} else if (!StaNo.equals(other.StaNo))
			return false;
		if (Double.doubleToLongBits(bworkHours) != Double
				.doubleToLongBits(other.bworkHours))
			return false;
		if (disqDetail == null) {
			if (other.disqDetail != null)
				return false;
		} else if (!disqDetail.equals(other.disqDetail))
			return false;
		if (gWasteNum != other.gWasteNum)
			return false;
		if (lWasteNum != other.lWasteNum)
			return false;
		if (proNo == null) {
			if (other.proNo != null)
				return false;
		} else if (!proNo.equals(other.proNo))
			return false;
		if (procId != other.procId)
			return false;
		if (quaNum != other.quaNum)
			return false;
		if (Double.doubleToLongBits(salary) != Double
				.doubleToLongBits(other.salary))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (wfId != other.wfId)
			return false;
		return true;
	}
	public SmallWf(int id, int wfId, String proNo, int procId, int quaNum,
			String disqDetail, String staNo, double bworkHours, double salary,
			int gWasteNum, int lWasteNum, Date time) {
		super();
		Id = id;
		this.wfId = wfId;
		this.proNo = proNo;
		this.procId = procId;
		this.quaNum = quaNum;
		this.disqDetail = disqDetail;
		StaNo = staNo;
		this.bworkHours = bworkHours;
		this.salary = salary;
		this.gWasteNum = gWasteNum;
		this.lWasteNum = lWasteNum;
		this.time = time;
	}
	public SmallWf() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SmallWf [Id=" + Id + ", wfId=" + wfId + ", proNo=" + proNo
				+ ", procId=" + procId + ", quaNum=" + quaNum + ", disqDetail="
				+ disqDetail + ", StaNo=" + StaNo + ", bworkHours="
				+ bworkHours + ", salary=" + salary + ", gWasteNum="
				+ gWasteNum + ", lWasteNum=" + lWasteNum + ", time=" + time
				+ "]";
	}
	
	
}
