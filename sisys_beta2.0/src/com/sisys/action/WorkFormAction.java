package com.sisys.action;

import com.sisys.service.WorkFormAddService;
import com.sisys.service.WorkFormAlterService;
import com.sisys.service.WorkFormDeleteService;
import com.sisys.service.WorkFormSearchService;

@SuppressWarnings("serial")
public class WorkFormAction extends BaseAction {

	public String execute() throws Exception {
		return null;
	}

	public String formadd() {


		String state;

		String name = request.getParameter("name");
		String proNo = request.getParameter("proNo");
		String procNo = request.getParameter("procNo");
		String batNo = request.getParameter("batNo");
		String staNo1 = request.getParameter("staNo1");
		String staNo2 = request.getParameter("staNo2");
		String staNo3 = request.getParameter("staNo3");
		String staNo4 = request.getParameter("staNo4");
		String staNo5 = request.getParameter("staNo5");
		String quaNum1;
		String quaNum2;
		String quaNum3;
		String quaNum4;
		String quaNum5;
		if ("".equals(proNo) || "".equals(procNo) || "".equals(batNo)) {
			return "isnull";
		}
		if (!"".equals(request.getParameter("quaNum1"))) {
			quaNum1 = request.getParameter("quaNum1");
		} else {
			quaNum1 = "0";
		}
		if (!"".equals(request.getParameter("quaNum2"))) {
			quaNum2 = request.getParameter("quaNum2");
		} else {
			quaNum2 = "0";
		}
		if (!"".equals(request.getParameter("quaNum3"))) {
			quaNum3 = request.getParameter("quaNum3");
		} else {
			quaNum3 = "0";
		}
		if (!"".equals(request.getParameter("quaNum4"))) {
			quaNum4 = request.getParameter("quaNum4");
		} else {
			quaNum4 = "0";
		}
		if (!"".equals(request.getParameter("quaNum5"))) {
			quaNum5 = request.getParameter("quaNum5");
		} else {
			quaNum5 = "0";
		}
		String disqDetail1 = "";
		String disqDetail2 = "";
		String disqDetail3 = "";
		String disqDetail4 = "";
		String disqDetail5 = "";
		int disqNum1 = 0;
		int disqNum2 = 0;
		int disqNum3 = 0;
		int disqNum4 = 0;
		int disqNum5 = 0;
		for (int i = 1; i <= 11; i++) {
			if ("".equals(request.getParameter("disqNum1-" + i))) {
				disqDetail1 += "0-";
				disqNum1 += 0;
			} else {
				disqDetail1 += request.getParameter("disqNum1-" + i) + "-";
				disqNum1 += Integer.parseInt(request.getParameter("disqNum1-"
						+ i));
			}
		}
		for (int i = 1; i <= 11; i++) {
			if ("".equals(request.getParameter("disqNum2-" + i))) {
				disqDetail2 += "0-";
				disqNum2 += 0;
			} else {
				disqDetail2 += request.getParameter("disqNum2-" + i) + "-";
				disqNum2 += Integer.parseInt(request.getParameter("disqNum2-"
						+ i));
			}
		}
		for (int i = 1; i <= 11; i++) {
			if ("".equals(request.getParameter("disqNum3-" + i))) {
				disqDetail3 += "0-";
				disqNum3 += 0;
			} else {
				disqDetail3 += request.getParameter("disqNum3-" + i) + "-";
				disqNum3 += Integer.parseInt(request.getParameter("disqNum3-"
						+ i));
			}
		}
		for (int i = 1; i <= 11; i++) {
			if ("".equals(request.getParameter("disqNum4-" + i))) {
				disqDetail4 += "0-";
				disqNum4 += 0;
			} else {
				disqDetail4 += request.getParameter("disqNum4-" + i) + "-";
				disqNum4 += Integer.parseInt(request.getParameter("disqNum4-"
						+ i));
			}
		}
		for (int i = 1; i <= 11; i++) {
			if ("".equals(request.getParameter("disqNum5-" + i))) {
				disqDetail5 += "0-";
				disqNum5 += 0;
			} else {
				disqDetail5 += request.getParameter("disqNum5-" + i) + "-";
				disqNum5 += Integer.parseInt(request.getParameter("disqNum5-"
						+ i));
			}
		}
		WorkFormAddService wfaa = new WorkFormAddService();
		state = wfaa.wfadd(proNo, procNo, batNo, staNo1, quaNum1, disqDetail1,
				disqNum1, staNo2, quaNum2, disqDetail2, disqNum2, staNo3,
				quaNum3, disqDetail3, disqNum3, staNo4, quaNum4, disqDetail4,
				disqNum4, staNo5, quaNum5, disqDetail5, disqNum5, name);
		return state;
	}
	
	public String formalter(){
		String state;
		String wfid = request.getParameter("wfId");
		String proNo = request.getParameter("proNo");
		String procNo = request.getParameter("procNo");
		String batNo = request.getParameter("batNo");
		String staNo1 = request.getParameter("staNo1");
		String staNo2 = request.getParameter("staNo2");
		String staNo3 = request.getParameter("staNo3");
		String staNo4 = request.getParameter("staNo4");
		String staNo5 = request.getParameter("staNo5");
		String quaNum1;
		String quaNum2;
		String quaNum3;
		String quaNum4;
		String quaNum5;
		if ("".equals(proNo) || "".equals(procNo) || "".equals(batNo)) {
			return "isnull";
		}
		if (!"".equals(request.getParameter("quaNum1"))) {
			quaNum1 = request.getParameter("quaNum1");
		} else {
			quaNum1 = "0";
		}
		if (!"".equals(request.getParameter("quaNum2"))) {
			quaNum2 = request.getParameter("quaNum2");
		} else {
			quaNum2 = "0";
		}
		if (!"".equals(request.getParameter("quaNum3"))) {
			quaNum3 = request.getParameter("quaNum3");
		} else {
			quaNum3 = "0";
		}
		if (!"".equals(request.getParameter("quaNum4"))) {
			quaNum4 = request.getParameter("quaNum4");
		} else {
			quaNum4 = "0";
		}
		if (!"".equals(request.getParameter("quaNum5"))) {
			quaNum5 = request.getParameter("quaNum5");
		} else {
			quaNum5 = "0";
		}
		String disqDetail1 = "";
		String disqDetail2 = "";
		String disqDetail3 = "";
		String disqDetail4 = "";
		String disqDetail5 = "";
		int disqNum1 = 0;
		int disqNum2 = 0;
		int disqNum3 = 0;
		int disqNum4 = 0;
		int disqNum5 = 0;
		for (int i = 1; i <= 11; i++) {
			if ("".equals(request.getParameter("disqNum1-" + i))) {
				disqDetail1 += "0-";
				disqNum1 += 0;
			} else {
				disqDetail1 += request.getParameter("disqNum1-" + i) + "-";
				disqNum1 += Integer.parseInt(request.getParameter("disqNum1-"
						+ i));
			}
		}
		for (int i = 1; i <= 11; i++) {
			if ("".equals(request.getParameter("disqNum2-" + i))) {
				disqDetail2 += "0-";
				disqNum2 += 0;
			} else {
				disqDetail2 += request.getParameter("disqNum2-" + i) + "-";
				disqNum2 += Integer.parseInt(request.getParameter("disqNum2-"
						+ i));
			}
		}
		for (int i = 1; i <= 11; i++) {
			if ("".equals(request.getParameter("disqNum3-" + i))) {
				disqDetail3 += "0-";
				disqNum3 += 0;
			} else {
				disqDetail3 += request.getParameter("disqNum3-" + i) + "-";
				disqNum3 += Integer.parseInt(request.getParameter("disqNum3-"
						+ i));
			}
		}
		for (int i = 1; i <= 11; i++) {
			if ("".equals(request.getParameter("disqNum4-" + i))) {
				disqDetail4 += "0-";
				disqNum4 += 0;
			} else {
				disqDetail4 += request.getParameter("disqNum4-" + i) + "-";
				disqNum4 += Integer.parseInt(request.getParameter("disqNum4-"
						+ i));
			}
		}
		for (int i = 1; i <= 11; i++) {
			if ("".equals(request.getParameter("disqNum5-" + i))) {
				disqDetail5 += "0-";
				disqNum5 += 0;
			} else {
				disqDetail5 += request.getParameter("disqNum5-" + i) + "-";
				disqNum5 += Integer.parseInt(request.getParameter("disqNum5-"
						+ i));
			}
		}
		WorkFormAlterService wfas = new WorkFormAlterService();
		state = wfas.wfalter(wfid, proNo, procNo, batNo, staNo1, quaNum1, disqDetail1,
				disqNum1, staNo2, quaNum2, disqDetail2, disqNum2, staNo3,
				quaNum3, disqDetail3, disqNum3, staNo4, quaNum4, disqDetail4,
				disqNum4, staNo5, quaNum5, disqDetail5, disqNum5);
		WorkFormSearchService wfss = new WorkFormSearchService();
		wfss.detail();
		return state;
	}
	
	public String formdelete(){
		String state;
		String wfid = request.getParameter("wfid");
		WorkFormDeleteService wfds = new WorkFormDeleteService();
		state = wfds.wfdelete(wfid);
		return state;
	}

}
