package com.sisys.bean;

import java.util.Date;

/**
 * 
 * @author huangxin 生产线信息
 */
public class ProductLine {

	private Integer id = null;
	private String lineNo;

	public ProductLine(Integer id, String lineNo, String lineDesc,
			int isDelete, Date deleteTime) {
		super();
		this.id = id;
		this.lineNo = lineNo;
		this.lineDesc = lineDesc;
		this.isDelete = isDelete;
		this.deleteTime = deleteTime;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	private String lineDesc;
	private int isDelete;
	private Date deleteTime;

	// get 和set 方法
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLineDesc() {
		return lineDesc;
	}

	public void setLineDesc(String lineDesc) {
		this.lineDesc = lineDesc;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + isDelete;
		result = prime * result
				+ ((lineDesc == null) ? 0 : lineDesc.hashCode());
		result = prime * result + ((lineNo == null) ? 0 : lineNo.hashCode());
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
		ProductLine other = (ProductLine) obj;
		if (deleteTime == null) {
			if (other.deleteTime != null)
				return false;
		} else if (!deleteTime.equals(other.deleteTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isDelete != other.isDelete)
			return false;
		if (lineDesc == null) {
			if (other.lineDesc != null)
				return false;
		} else if (!lineDesc.equals(other.lineDesc))
			return false;
		if (lineNo == null) {
			if (other.lineNo != null)
				return false;
		} else if (!lineNo.equals(other.lineNo))
			return false;
		return true;
	}

	// 构造函数

	public ProductLine() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ProductLine [id=" + id + ", lineNo=" + lineNo + ", lineDesc="
				+ lineDesc + ", isDelete=" + isDelete + ", deleteTime="
				+ deleteTime + "]";
	}

	public String getLineNo() {
		return lineNo;
	}

}
