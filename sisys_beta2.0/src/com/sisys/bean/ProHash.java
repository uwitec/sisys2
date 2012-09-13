package com.sisys.bean;

public class ProHash {
	private int id;
	private String proNo;
	private String date;
	private int own;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getOwn() {
		return own;
	}
	public void setOwn(int own) {
		this.own = own;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + own;
		result = prime * result + ((proNo == null) ? 0 : proNo.hashCode());
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
		ProHash other = (ProHash) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (own != other.own)
			return false;
		if (proNo == null) {
			if (other.proNo != null)
				return false;
		} else if (!proNo.equals(other.proNo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProHash [id=" + id + ", proNo=" + proNo + ", date=" + date
				+ ", own=" + own + "]";
	}
	public ProHash(int id, String proNo, String date, int own) {
		super();
		this.id = id;
		this.proNo = proNo;
		this.date = date;
		this.own = own;
	}
	public ProHash() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
