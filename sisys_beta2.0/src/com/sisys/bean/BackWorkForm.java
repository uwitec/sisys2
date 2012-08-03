package com.sisys.bean;

import java.util.Date;

public class BackWorkForm {
	private int id;
	private String staNo;
	private String kind;
	private double workHours;
	private String name;
	private String checkNo;
	private String respNo;
	private int isDelete;
	private Date deleteDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checkNo == null) ? 0 : checkNo.hashCode());
		result = prime * result
				+ ((deleteDate == null) ? 0 : deleteDate.hashCode());
		result = prime * result + id;
		result = prime * result + isDelete;
		result = prime * result + ((kind == null) ? 0 : kind.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((respNo == null) ? 0 : respNo.hashCode());
		result = prime * result + ((staNo == null) ? 0 : staNo.hashCode());
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
		if (checkNo == null) {
			if (other.checkNo != null)
				return false;
		} else if (!checkNo.equals(other.checkNo))
			return false;
		if (deleteDate == null) {
			if (other.deleteDate != null)
				return false;
		} else if (!deleteDate.equals(other.deleteDate))
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
		if (Double.doubleToLongBits(workHours) != Double
				.doubleToLongBits(other.workHours))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "backWorkForm [id=" + id + ", staNo=" + staNo + ", kind=" + kind
				+ ", workHours=" + workHours + ", name=" + name + ", checkNo="
				+ checkNo + ", respNo=" + respNo + ", isDelete=" + isDelete
				+ ", deleteDate=" + deleteDate + "]";
	}
	public BackWorkForm(int id, String staNo, String kind, double workHours,
			String name, String checkNo, String respNo, int isDelete,
			Date deleteDate) {
		super();
		this.id = id;
		this.staNo = staNo;
		this.kind = kind;
		this.workHours = workHours;
		this.name = name;
		this.checkNo = checkNo;
		this.respNo = respNo;
		this.isDelete = isDelete;
		this.deleteDate = deleteDate;
	}
	public BackWorkForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
