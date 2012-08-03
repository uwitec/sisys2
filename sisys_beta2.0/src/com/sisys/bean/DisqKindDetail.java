package com.sisys.bean;

import java.util.Date;

public class DisqKindDetail {
	private Integer id = null;
	private int disqKId;
	private int disqKNum;
	private Date time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getDisqKId() {
		return disqKId;
	}
	public void setDisqKId(int disqKId) {
		this.disqKId = disqKId;
	}
	public int getDisqKNum() {
		return disqKNum;
	}
	public void setDisqKNum(int disqKNum) {
		this.disqKNum = disqKNum;
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
		result = prime * result + disqKId;
		result = prime * result + disqKNum;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		DisqKindDetail other = (DisqKindDetail) obj;
		if (disqKId != other.disqKId)
			return false;
		if (disqKNum != other.disqKNum)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DisqKindDetail [id=" + id + ", disqKId=" + disqKId
				+ ", disqKNum=" + disqKNum + ", time=" + time + "]";
	}
	public DisqKindDetail(Integer id, int disqKId, int disqKNum, Date time) {
		super();
		this.id = id;
		this.disqKId = disqKId;
		this.disqKNum = disqKNum;
		this.time = time;
	}
	public DisqKindDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

}
