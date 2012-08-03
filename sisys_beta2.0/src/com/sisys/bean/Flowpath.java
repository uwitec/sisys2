package com.sisys.bean;

import java.util.Date;

	/**
	 * 
	 * @author huangxin 流程信息 一种产品对应多个流程，一个批次对应该产品下的一种流程
	 * 
	 */
	public class Flowpath {

		private Integer id = null;
		private String sequence;
		private int proId;
		private int isDelete;
		private Date deleteTime;

		// get和set方法
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getSequence() {
			return sequence;
		}

		public void setSequence(String sequence) {
			this.sequence = sequence;
		}

		public int getProId() {
			return proId;
		}

		public void setProId(int proId) {
			this.proId = proId;
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
			result = prime * result + id;
			result = prime * result + isDelete;
			result = prime * result + proId;
			result = prime * result
					+ ((sequence == null) ? 0 : sequence.hashCode());
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
			Flowpath other = (Flowpath) obj;
			if (id != other.id)
				return false;
			if (isDelete != other.isDelete)
				return false;
			if (proId != other.proId)
				return false;
			if (sequence == null) {
				if (other.sequence != null)
					return false;
			} else if (!sequence.equals(other.sequence))
				return false;
			return true;
		}

		// 构造函数
		public Flowpath(int id, String sequence, int proId, int isDelete,
				Date deleteTime) {
			super();
			this.id = id;
			this.sequence = sequence;
			this.proId = proId;
			this.isDelete = isDelete;
			this.deleteTime = deleteTime;
		}

		public Flowpath() {
			super();
			// TODO Auto-generated constructor stub
		}

		// 转换为字符串
		@Override
		public String toString() {
			return "flowpath [id=" + id + ", sequence=" + sequence + ", proId="
					+ proId + ", isDelete=" + isDelete + "]";
		}

	}

