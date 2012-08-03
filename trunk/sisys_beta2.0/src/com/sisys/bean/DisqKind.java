package com.sisys.bean;

import java.util.Date;

/**
 * 
 * @author huangxin 不合格品种类信息
 */
public class DisqKind {

	private Integer id = null;
	private String disDesc;
	private int kind; // 0代表工废，1代表料废
	private int isDelete;
	private Date deleteTime;

	// get和set方法
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDisDesc() {
		return disDesc;
	}

	public void setDisDesc(String disDesc) {
		this.disDesc = disDesc;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
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
		result = prime * result + ((disDesc == null) ? 0 : disDesc.hashCode());
		result = prime * result + id;
		result = prime * result + isDelete;
		result = prime * result + kind;
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
		DisqKind other = (DisqKind) obj;
		if (deleteTime == null) {
			if (other.deleteTime != null)
				return false;
		} else if (!deleteTime.equals(other.deleteTime))
			return false;
		if (disDesc == null) {
			if (other.disDesc != null)
				return false;
		} else if (!disDesc.equals(other.disDesc))
			return false;
		if (id != other.id)
			return false;
		if (isDelete != other.isDelete)
			return false;
		if (kind != other.kind)
			return false;
		return true;
	}

	// 构造函数
	public DisqKind(int id, String disDesc, int kind, int isDelete,
			Date deleteTime) {
		super();
		this.id = id;
		this.disDesc = disDesc;
		this.kind = kind;
		this.isDelete = isDelete;
		this.deleteTime = deleteTime;
	}

	public DisqKind() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 转换为字符串
	@Override
	public String toString() {
		return "DisqKind [id=" + id + ", disDesc=" + disDesc + ", kind=" + kind
				+ ", isDelete=" + isDelete + ", deleteTime=" + deleteTime + "]";
	}

}
