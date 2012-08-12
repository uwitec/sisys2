package com.sisys.bean;

public class ProHash {
	private int id;
	private String proNo;
	private int hash;
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
	public int getHash() {
		return hash;
	}
	public void setHash(int hash) {
		this.hash = hash;
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
		result = prime * result + hash;
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
		if (hash != other.hash)
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
		return "ProHash [id=" + id + ", proNo=" + proNo + ", hash=" + hash
				+ ", own=" + own + "]";
	}
	public ProHash(int id, String proNo, int hash, int own) {
		super();
		this.id = id;
		this.proNo = proNo;
		this.hash = hash;
		this.own = own;
	}
	public ProHash() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
