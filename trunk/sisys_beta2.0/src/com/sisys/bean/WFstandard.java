package com.sisys.bean;

import java.util.Arrays;

public class WFstandard {
	private int wfId;
	private String BatchNo;
	private String proName;
	private String proNo;
	private String procName;
	private String procNo;
	private int quaNum;
	private String status;
	private int disqNum;
	private String deletetime;
	private String staName1;
	private String staName2;
	private String staName3;
	private String staName4;
	private String staName5;
	private String staNo1;
	private String staNo2;
	private String staNo3;
	private String staNo4;
	private String staNo5;
	private String quaNum1;
	private String quaNum2;
	private String quaNum3;
	private String quaNum4;
	private String quaNum5;
	private String[] disqNum1;
	private String[] disqNum2;
	private String[] disqNum3;
	private String[] disqNum4;
	private String[] disqNum5;
	public int getWfId() {
		return wfId;
	}
	public void setWfId(int wfId) {
		this.wfId = wfId;
	}
	public String getBatchNo() {
		return BatchNo;
	}
	public void setBatchNo(String batchNo) {
		BatchNo = batchNo;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProNo() {
		return proNo;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	public String getProcName() {
		return procName;
	}
	public void setProcName(String procName) {
		this.procName = procName;
	}
	public String getProcNo() {
		return procNo;
	}
	public void setProcNo(String procNo) {
		this.procNo = procNo;
	}
	public int getQuaNum() {
		return quaNum;
	}
	public void setQuaNum(int quaNum) {
		this.quaNum = quaNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getDisqNum() {
		return disqNum;
	}
	public void setDisqNum(int disqNum) {
		this.disqNum = disqNum;
	}
	public String getDeletetime() {
		return deletetime;
	}
	public void setDeletetime(String deletetime) {
		this.deletetime = deletetime;
	}
	public String getStaName1() {
		return staName1;
	}
	public void setStaName1(String staName1) {
		this.staName1 = staName1;
	}
	public String getStaName2() {
		return staName2;
	}
	public void setStaName2(String staName2) {
		this.staName2 = staName2;
	}
	public String getStaName3() {
		return staName3;
	}
	public void setStaName3(String staName3) {
		this.staName3 = staName3;
	}
	public String getStaName4() {
		return staName4;
	}
	public void setStaName4(String staName4) {
		this.staName4 = staName4;
	}
	public String getStaName5() {
		return staName5;
	}
	public void setStaName5(String staName5) {
		this.staName5 = staName5;
	}
	public String getStaNo1() {
		return staNo1;
	}
	public void setStaNo1(String staNo1) {
		this.staNo1 = staNo1;
	}
	public String getStaNo2() {
		return staNo2;
	}
	public void setStaNo2(String staNo2) {
		this.staNo2 = staNo2;
	}
	public String getStaNo3() {
		return staNo3;
	}
	public void setStaNo3(String staNo3) {
		this.staNo3 = staNo3;
	}
	public String getStaNo4() {
		return staNo4;
	}
	public void setStaNo4(String staNo4) {
		this.staNo4 = staNo4;
	}
	public String getStaNo5() {
		return staNo5;
	}
	public void setStaNo5(String staNo5) {
		this.staNo5 = staNo5;
	}
	public String getQuaNum1() {
		return quaNum1;
	}
	public void setQuaNum1(String quaNum1) {
		this.quaNum1 = quaNum1;
	}
	public String getQuaNum2() {
		return quaNum2;
	}
	public void setQuaNum2(String quaNum2) {
		this.quaNum2 = quaNum2;
	}
	public String getQuaNum3() {
		return quaNum3;
	}
	public void setQuaNum3(String quaNum3) {
		this.quaNum3 = quaNum3;
	}
	public String getQuaNum4() {
		return quaNum4;
	}
	public void setQuaNum4(String quaNum4) {
		this.quaNum4 = quaNum4;
	}
	public String getQuaNum5() {
		return quaNum5;
	}
	public void setQuaNum5(String quaNum5) {
		this.quaNum5 = quaNum5;
	}
	public String[] getDisqNum1() {
		return disqNum1;
	}
	public void setDisqNum1(String[] disqNum1) {
		this.disqNum1 = disqNum1;
	}
	public String[] getDisqNum2() {
		return disqNum2;
	}
	public void setDisqNum2(String[] disqNum2) {
		this.disqNum2 = disqNum2;
	}
	public String[] getDisqNum3() {
		return disqNum3;
	}
	public void setDisqNum3(String[] disqNum3) {
		this.disqNum3 = disqNum3;
	}
	public String[] getDisqNum4() {
		return disqNum4;
	}
	public void setDisqNum4(String[] disqNum4) {
		this.disqNum4 = disqNum4;
	}
	public String[] getDisqNum5() {
		return disqNum5;
	}
	public void setDisqNum5(String[] disqNum5) {
		this.disqNum5 = disqNum5;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BatchNo == null) ? 0 : BatchNo.hashCode());
		result = prime * result
				+ ((deletetime == null) ? 0 : deletetime.hashCode());
		result = prime * result + disqNum;
		result = prime * result + Arrays.hashCode(disqNum1);
		result = prime * result + Arrays.hashCode(disqNum2);
		result = prime * result + Arrays.hashCode(disqNum3);
		result = prime * result + Arrays.hashCode(disqNum4);
		result = prime * result + Arrays.hashCode(disqNum5);
		result = prime * result + ((proName == null) ? 0 : proName.hashCode());
		result = prime * result + ((proNo == null) ? 0 : proNo.hashCode());
		result = prime * result
				+ ((procName == null) ? 0 : procName.hashCode());
		result = prime * result + ((procNo == null) ? 0 : procNo.hashCode());
		result = prime * result + quaNum;
		result = prime * result + ((quaNum1 == null) ? 0 : quaNum1.hashCode());
		result = prime * result + ((quaNum2 == null) ? 0 : quaNum2.hashCode());
		result = prime * result + ((quaNum3 == null) ? 0 : quaNum3.hashCode());
		result = prime * result + ((quaNum4 == null) ? 0 : quaNum4.hashCode());
		result = prime * result + ((quaNum5 == null) ? 0 : quaNum5.hashCode());
		result = prime * result
				+ ((staName1 == null) ? 0 : staName1.hashCode());
		result = prime * result
				+ ((staName2 == null) ? 0 : staName2.hashCode());
		result = prime * result
				+ ((staName3 == null) ? 0 : staName3.hashCode());
		result = prime * result
				+ ((staName4 == null) ? 0 : staName4.hashCode());
		result = prime * result
				+ ((staName5 == null) ? 0 : staName5.hashCode());
		result = prime * result + ((staNo1 == null) ? 0 : staNo1.hashCode());
		result = prime * result + ((staNo2 == null) ? 0 : staNo2.hashCode());
		result = prime * result + ((staNo3 == null) ? 0 : staNo3.hashCode());
		result = prime * result + ((staNo4 == null) ? 0 : staNo4.hashCode());
		result = prime * result + ((staNo5 == null) ? 0 : staNo5.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		WFstandard other = (WFstandard) obj;
		if (BatchNo == null) {
			if (other.BatchNo != null)
				return false;
		} else if (!BatchNo.equals(other.BatchNo))
			return false;
		if (deletetime == null) {
			if (other.deletetime != null)
				return false;
		} else if (!deletetime.equals(other.deletetime))
			return false;
		if (disqNum != other.disqNum)
			return false;
		if (!Arrays.equals(disqNum1, other.disqNum1))
			return false;
		if (!Arrays.equals(disqNum2, other.disqNum2))
			return false;
		if (!Arrays.equals(disqNum3, other.disqNum3))
			return false;
		if (!Arrays.equals(disqNum4, other.disqNum4))
			return false;
		if (!Arrays.equals(disqNum5, other.disqNum5))
			return false;
		if (proName == null) {
			if (other.proName != null)
				return false;
		} else if (!proName.equals(other.proName))
			return false;
		if (proNo == null) {
			if (other.proNo != null)
				return false;
		} else if (!proNo.equals(other.proNo))
			return false;
		if (procName == null) {
			if (other.procName != null)
				return false;
		} else if (!procName.equals(other.procName))
			return false;
		if (procNo == null) {
			if (other.procNo != null)
				return false;
		} else if (!procNo.equals(other.procNo))
			return false;
		if (quaNum != other.quaNum)
			return false;
		if (quaNum1 == null) {
			if (other.quaNum1 != null)
				return false;
		} else if (!quaNum1.equals(other.quaNum1))
			return false;
		if (quaNum2 == null) {
			if (other.quaNum2 != null)
				return false;
		} else if (!quaNum2.equals(other.quaNum2))
			return false;
		if (quaNum3 == null) {
			if (other.quaNum3 != null)
				return false;
		} else if (!quaNum3.equals(other.quaNum3))
			return false;
		if (quaNum4 == null) {
			if (other.quaNum4 != null)
				return false;
		} else if (!quaNum4.equals(other.quaNum4))
			return false;
		if (quaNum5 == null) {
			if (other.quaNum5 != null)
				return false;
		} else if (!quaNum5.equals(other.quaNum5))
			return false;
		if (staName1 == null) {
			if (other.staName1 != null)
				return false;
		} else if (!staName1.equals(other.staName1))
			return false;
		if (staName2 == null) {
			if (other.staName2 != null)
				return false;
		} else if (!staName2.equals(other.staName2))
			return false;
		if (staName3 == null) {
			if (other.staName3 != null)
				return false;
		} else if (!staName3.equals(other.staName3))
			return false;
		if (staName4 == null) {
			if (other.staName4 != null)
				return false;
		} else if (!staName4.equals(other.staName4))
			return false;
		if (staName5 == null) {
			if (other.staName5 != null)
				return false;
		} else if (!staName5.equals(other.staName5))
			return false;
		if (staNo1 == null) {
			if (other.staNo1 != null)
				return false;
		} else if (!staNo1.equals(other.staNo1))
			return false;
		if (staNo2 == null) {
			if (other.staNo2 != null)
				return false;
		} else if (!staNo2.equals(other.staNo2))
			return false;
		if (staNo3 == null) {
			if (other.staNo3 != null)
				return false;
		} else if (!staNo3.equals(other.staNo3))
			return false;
		if (staNo4 == null) {
			if (other.staNo4 != null)
				return false;
		} else if (!staNo4.equals(other.staNo4))
			return false;
		if (staNo5 == null) {
			if (other.staNo5 != null)
				return false;
		} else if (!staNo5.equals(other.staNo5))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (wfId != other.wfId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "WFstandard [wfId=" + wfId + ", BatchNo=" + BatchNo
				+ ", proName=" + proName + ", proNo=" + proNo + ", procName="
				+ procName + ", procNo=" + procNo + ", quaNum=" + quaNum
				+ ", status=" + status + ", disqNum=" + disqNum
				+ ", deletetime=" + deletetime + ", staName1=" + staName1
				+ ", staName2=" + staName2 + ", staName3=" + staName3
				+ ", staName4=" + staName4 + ", staName5=" + staName5
				+ ", staNo1=" + staNo1 + ", staNo2=" + staNo2 + ", staNo3="
				+ staNo3 + ", staNo4=" + staNo4 + ", staNo5=" + staNo5
				+ ", quaNum1=" + quaNum1 + ", quaNum2=" + quaNum2
				+ ", quaNum3=" + quaNum3 + ", quaNum4=" + quaNum4
				+ ", quaNum5=" + quaNum5 + ", disqNum1="
				+ Arrays.toString(disqNum1) + ", disqNum2="
				+ Arrays.toString(disqNum2) + ", disqNum3="
				+ Arrays.toString(disqNum3) + ", disqNum4="
				+ Arrays.toString(disqNum4) + ", disqNum5="
				+ Arrays.toString(disqNum5) + "]";
	}
	public WFstandard(int wfId, String batchNo, String proName, String proNo,
			String procName, String procNo, int quaNum, String status,
			int disqNum, String deletetime, String staName1, String staName2,
			String staName3, String staName4, String staName5, String staNo1,
			String staNo2, String staNo3, String staNo4, String staNo5,
			String quaNum1, String quaNum2, String quaNum3, String quaNum4,
			String quaNum5, String[] disqNum1, String[] disqNum2,
			String[] disqNum3, String[] disqNum4, String[] disqNum5) {
		super();
		this.wfId = wfId;
		BatchNo = batchNo;
		this.proName = proName;
		this.proNo = proNo;
		this.procName = procName;
		this.procNo = procNo;
		this.quaNum = quaNum;
		this.status = status;
		this.disqNum = disqNum;
		this.deletetime = deletetime;
		this.staName1 = staName1;
		this.staName2 = staName2;
		this.staName3 = staName3;
		this.staName4 = staName4;
		this.staName5 = staName5;
		this.staNo1 = staNo1;
		this.staNo2 = staNo2;
		this.staNo3 = staNo3;
		this.staNo4 = staNo4;
		this.staNo5 = staNo5;
		this.quaNum1 = quaNum1;
		this.quaNum2 = quaNum2;
		this.quaNum3 = quaNum3;
		this.quaNum4 = quaNum4;
		this.quaNum5 = quaNum5;
		this.disqNum1 = disqNum1;
		this.disqNum2 = disqNum2;
		this.disqNum3 = disqNum3;
		this.disqNum4 = disqNum4;
		this.disqNum5 = disqNum5;
	}
	public WFstandard() {
		super();
		// TODO Auto-generated constructor stub
	}
}
