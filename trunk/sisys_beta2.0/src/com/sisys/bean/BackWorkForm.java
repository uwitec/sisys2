package com.sisys.bean;

import java.util.Date;

public class BackWorkForm {
	private int id;
	private String proNo;
	private String batchNo;
	private String procNo;
	private String staNo;
	private String kind;
	private double workHours;
	private String name;
	private String checkNo;
	private String respNo;
	private Date time;
	private int isDelete;
	private Date deleteTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProNo() {
		return proNo;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getProcNo() {
		return procNo;
	}
	public void setProcNo(String procNo) {
		this.procNo = procNo;
	}
	public String getStaNo() {
		return staNo;
	}
	public void setStaNo(String staNo) {
		this.staNo = staNo;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public double getWorkHours() {
		return workHours;
	}
	public void setWorkHours(double workHours) {
		this.workHours = workHours;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public String getRespNo() {
		return respNo;
	}
	public void setRespNo(String respNo) {
		this.respNo = respNo;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchNo == null) ? 0 : batchNo.hashCode());
		result = prime * result + ((checkNo == null) ? 0 : checkNo.hashCode());
		result = prime * result
				+ ((deleteTime == null) ? 0 : deleteTime.hashCode());
		result = prime * result + id;
		result = prime * result + isDelete;
		result = prime * result + ((kind == null) ? 0 : kind.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((proNo == null) ? 0 : proNo.hashCode());
		result = prime * result + ((procNo == null) ? 0 : procNo.hashCode());
		result = prime * result + ((respNo == null) ? 0 : respNo.hashCode());
		result = prime * result + ((staNo == null) ? 0 : staNo.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		long temp;
		temp = Double.doubleToLongBits(workHours);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		BackWorkForm other = (BackWorkForm) obj;
		if (batchNo == null) {
			if (other.batchNo != null)
				return false;
		} else if (!batchNo.equals(other.batchNo))
			return false;
		if (checkNo == null) {
			if (other.checkNo != null)
				return false;
		} else if (!checkNo.equals(other.checkNo))
			return false;
		if (deleteTime == null) {
			if (other.deleteTime != null)
				return false;
		} else if (!deleteTime.equals(other.deleteTime))
			return false;
		if (id != other.id)
			return false;
		if (isDelete != other.isDelete)
			return false;
		if (kind == null) {
			if (other.kind != null)
				return false;
		} else if (!kind.equals(other.kind))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (proNo == null) {
			if (other.proNo != null)
				return false;
		} else if (!proNo.equals(other.proNo))
			return false;
		if (procNo == null) {
			if (other.procNo != null)
				return false;
		} else if (!procNo.equals(other.procNo))
			return false;
		if (respNo == null) {
			if (other.respNo != null)
				return false;
		} else if (!respNo.equals(other.respNo))
			return false;
		if (staNo == null) {
			if (other.staNo != null)
				return false;
		} else if (!staNo.equals(other.staNo))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (Double.doubleToLongBits(workHours) != Double
				.doubleToLongBits(other.workHours))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BackWorkForm [batchNo=" + batchNo + ", checkNo=" + checkNo
				+ ", deleteTime=" + deleteTime + ", id=" + id + ", isDelete="
				+ isDelete + ", kind=" + kind + ", name=" + name + ", proNo="
				+ proNo + ", procNo=" + procNo + ", respNo=" + respNo
				+ ", staNo=" + staNo + ", time=" + time + ", workHours="
				+ workHours + "]";
	}
	public BackWorkForm(int id, String proNo, String batchNo, String procNo,
			String staNo, String kind, double workHours, String name,
			String checkNo, String respNo, Date time, int isDelete,
			Date deleteTime) {
		super();
		this.id = id;
		this.proNo = proNo;
		this.batchNo = batchNo;
		this.procNo = procNo;
		this.staNo = staNo;
		this.kind = kind;
		this.workHours = workHours;
		this.name = name;
		this.checkNo = checkNo;
		this.respNo = respNo;
		this.time = time;
		this.isDelete = isDelete;
		this.deleteTime = deleteTime;
	}
	public BackWorkForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
