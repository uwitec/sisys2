package com.sisys.action;

import java.io.IOException;

import com.sisys.service.ManageWorkFormService;
import com.sisys.service.WorkFormAddService;
import com.sisys.service.WorkFormAlterService;
import com.sisys.service.WorkFormDeleteService;
import com.sisys.service.WorkFormSearchService;

@SuppressWarnings("serial")
public class WorkFormAction extends BaseAction {

	private ManageWorkFormService mwfs;

	public String execute() throws Exception {
		return null;
	}

	//添加工单
	public String formadd() {


		String state;

		String name = request.getParameter("name").trim();
		String proNo = request.getParameter("proNo").trim();
		String procNo = request.getParameter("procNo").trim();
		String batNo = request.getParameter("batNo").trim();
		String staNo1 = request.getParameter("staNo1").trim();
		String staNo2 = request.getParameter("staNo2").trim();
		String staNo3 = request.getParameter("staNo3").trim();
		String staNo4 = request.getParameter("staNo4").trim();
		String staNo5 = request.getParameter("staNo5").trim();
		String quaNum1;
		String quaNum2;
		String quaNum3;
		String quaNum4;
		String quaNum5;
		
		if ("".equals(proNo) || "".equals(procNo) || "".equals(batNo)) {
			String proName = request.getParameter("proName");
			request.setAttribute("batNo", batNo);
			request.setAttribute("proNo", proNo);
			request.setAttribute("proName", proName);
			request.setAttribute("name", name);
			return "isnull";
		}
		
		//整合合格品显示（数量）
		if (!"".equals(request.getParameter("quaNum1").trim())) {
			quaNum1 = request.getParameter("quaNum1").trim();
		} else {
			quaNum1 = "0";
		}
		if (!"".equals(request.getParameter("quaNum2").trim())) {
			quaNum2 = request.getParameter("quaNum2").trim();
		} else {
			quaNum2 = "0";
		}
		if (!"".equals(request.getParameter("quaNum3").trim())) {
			quaNum3 = request.getParameter("quaNum3").trim();
		} else {
			quaNum3 = "0";
		}
		if (!"".equals(request.getParameter("quaNum4").trim())) {
			quaNum4 = request.getParameter("quaNum4").trim();
		} else {
			quaNum4 = "0";
		}
		if (!"".equals(request.getParameter("quaNum5").trim())) {
			quaNum5 = request.getParameter("quaNum5").trim();
		} else {
			quaNum5 = "0";
		}
		
		//整合不合格品，包括整合字符串及每个员工的不合格品数量
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
		for (int i = 1; i <= 10; i++) {
			if ("".equals(request.getParameter("disqNum1-" + i).trim())) {
				disqDetail1 += "0-";
				disqNum1 += 0;
			} else {
				disqDetail1 += request.getParameter("disqNum1-" + i).trim() + "-";
				disqNum1 += Integer.parseInt(request.getParameter("disqNum1-"
						+ i).trim());
			}
		}
		if ("".equals(request.getParameter("disqNum1-" + 11).trim())) {
			disqDetail1 += "0-";
			disqNum1 += 0;
		} else {
			disqDetail1 += request.getParameter("disqNum1-" + 11).trim() + "-";
			disqNum1 += Integer.parseInt(request.getParameter("disqNum1-"
					+ 11).trim());
		}
		
		for (int i = 1; i <= 10; i++) {
			if ("".equals(request.getParameter("disqNum2-" + i).trim())) {
				disqDetail2 += "0-";
				disqNum2 += 0;
			} else {
				disqDetail2 += request.getParameter("disqNum2-" + i).trim() + "-";
				disqNum2 += Integer.parseInt(request.getParameter("disqNum2-"
						+ i).trim());
			}
		}
		if ("".equals(request.getParameter("disqNum2-" + 11).trim())) {
			disqDetail2 += "0-";
			disqNum2 += 0;
		} else {
			disqDetail2 += request.getParameter("disqNum2-" + 11).trim() + "-";
			disqNum2 += Integer.parseInt(request.getParameter("disqNum2-"
					+ 11).trim());
		}
		
		for (int i = 1; i <= 10; i++) {
			if ("".equals(request.getParameter("disqNum3-" + i).trim())) {
				disqDetail3 += "0-";
				disqNum3 += 0;
			} else {
				disqDetail3 += request.getParameter("disqNum3-" + i).trim() + "-";
				disqNum3 += Integer.parseInt(request.getParameter("disqNum3-"
						+ i).trim());
			}
		}
		if ("".equals(request.getParameter("disqNum3-" + 11).trim())) {
			disqDetail3 += "0-";
			disqNum3 += 0;
		} else {
			disqDetail3 += request.getParameter("disqNum3-" + 11).trim() + "-";
			disqNum3 += Integer.parseInt(request.getParameter("disqNum3-"
					+ 11).trim());
		}
		
		for (int i = 1; i <= 10; i++) {
			if ("".equals(request.getParameter("disqNum4-" + i).trim())) {
				disqDetail4 += "0-";
				disqNum4 += 0;
			} else {
				disqDetail4 += request.getParameter("disqNum4-" + i).trim() + "-";
				disqNum4 += Integer.parseInt(request.getParameter("disqNum4-"
						+ i).trim());
			}
		}
		if ("".equals(request.getParameter("disqNum4-" + 11).trim())) {
			disqDetail4 += "0-";
			disqNum4 += 0;
		} else {
			disqDetail4 += request.getParameter("disqNum4-" + 11).trim() + "-";
			disqNum4 += Integer.parseInt(request.getParameter("disqNum4-"
					+ 11).trim());
		}
		
		for (int i = 1; i <= 10; i++) {
			if ("".equals(request.getParameter("disqNum5-" + i).trim())) {
				disqDetail5 += "0-";
				disqNum5 += 0;
			} else {
				disqDetail5 += request.getParameter("disqNum5-" + i).trim() + "-";
				disqNum5 += Integer.parseInt(request.getParameter("disqNum5-"
						+ i).trim());
			}
		}
		if ("".equals(request.getParameter("disqNum5-" + 11).trim())) {
			disqDetail5 += "0-";
			disqNum5 += 0;
		} else {
			disqDetail5 += request.getParameter("disqNum5-" + 11).trim() + "-";
			disqNum5 += Integer.parseInt(request.getParameter("disqNum5-"
					+ 11).trim());
		}
		
		WorkFormAddService wfaa = new WorkFormAddService();
		state = wfaa.wfadd(proNo, procNo, batNo, staNo1, quaNum1, disqDetail1,
				disqNum1, staNo2, quaNum2, disqDetail2, disqNum2, staNo3,
				quaNum3, disqDetail3, disqNum3, staNo4, quaNum4, disqDetail4,
				disqNum4, staNo5, quaNum5, disqDetail5, disqNum5, name);
		if (!state.equals("success")) {
			String proName = request.getParameter("proName");
			request.setAttribute("batNo", batNo);
			request.setAttribute("proNo", proNo);
			request.setAttribute("proName", proName);
			request.setAttribute("name", name);
		}
		return state;
	}
	
	//修改工单
	public String formalter(){
		String state;
		String wfid = request.getParameter("wfId").trim();
		String proNo = request.getParameter("proNo").trim();
		String procNo = request.getParameter("procNo").trim();
		String batNo = request.getParameter("batNo").trim();
		String staNo1 = request.getParameter("staNo1").trim();
		String staNo2 = request.getParameter("staNo2").trim();
		String staNo3 = request.getParameter("staNo3").trim();
		String staNo4 = request.getParameter("staNo4").trim();
		String staNo5 = request.getParameter("staNo5").trim();
		String quaNum1;
		String quaNum2;
		String quaNum3;
		String quaNum4;
		String quaNum5;
		if ("".equals(proNo) || "".equals(procNo) || "".equals(batNo)) {
			return "isnull";
		}
		
		//整合合格品显示（数量）
		if (!"".equals(request.getParameter("quaNum1").trim())) {
			quaNum1 = request.getParameter("quaNum1").trim();
		} else {
			quaNum1 = "0";
		}
		if (!"".equals(request.getParameter("quaNum2").trim())) {
			quaNum2 = request.getParameter("quaNum2").trim();
		} else {
			quaNum2 = "0";
		}
		if (!"".equals(request.getParameter("quaNum3").trim())) {
			quaNum3 = request.getParameter("quaNum3").trim();
		} else {
			quaNum3 = "0";
		}
		if (!"".equals(request.getParameter("quaNum4").trim())) {
			quaNum4 = request.getParameter("quaNum4").trim();
		} else {
			quaNum4 = "0";
		}
		if (!"".equals(request.getParameter("quaNum5").trim())) {
			quaNum5 = request.getParameter("quaNum5").trim();
		} else {
			quaNum5 = "0";
		}
		
		//整合不合格品，包括整合字符串及每个员工的不合格品数量
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
		for (int i = 1; i <= 10; i++) {
			if ("".equals(request.getParameter("disqNum1-" + i).trim())) {
				disqDetail1 += "0-";
				disqNum1 += 0;
			} else {
				disqDetail1 += request.getParameter("disqNum1-" + i).trim() + "-";
				disqNum1 += Integer.parseInt(request.getParameter("disqNum1-"
						+ i).trim());
			}
		}
		if ("".equals(request.getParameter("disqNum1-" + 11).trim())) {
			disqDetail1 += "0-";
			disqNum1 += 0;
		} else {
			disqDetail1 += request.getParameter("disqNum1-" + 11).trim() + "-";
			disqNum1 += Integer.parseInt(request.getParameter("disqNum1-"
					+ 11).trim());
		}
		
		for (int i = 1; i <= 10; i++) {
			if ("".equals(request.getParameter("disqNum2-" + i).trim())) {
				disqDetail2 += "0-";
				disqNum2 += 0;
			} else {
				disqDetail2 += request.getParameter("disqNum2-" + i).trim() + "-";
				disqNum2 += Integer.parseInt(request.getParameter("disqNum2-"
						+ i).trim());
			}
		}
		if ("".equals(request.getParameter("disqNum2-" + 11).trim())) {
			disqDetail2 += "0-";
			disqNum2 += 0;
		} else {
			disqDetail2 += request.getParameter("disqNum2-" + 11).trim() + "-";
			disqNum2 += Integer.parseInt(request.getParameter("disqNum2-"
					+ 11).trim());
		}
		
		for (int i = 1; i <= 10; i++) {
			if ("".equals(request.getParameter("disqNum3-" + i).trim())) {
				disqDetail3 += "0-";
				disqNum3 += 0;
			} else {
				disqDetail3 += request.getParameter("disqNum3-" + i).trim() + "-";
				disqNum3 += Integer.parseInt(request.getParameter("disqNum3-"
						+ i).trim());
			}
		}
		if ("".equals(request.getParameter("disqNum3-" + 11).trim())) {
			disqDetail3 += "0-";
			disqNum3 += 0;
		} else {
			disqDetail3 += request.getParameter("disqNum3-" + 11).trim() + "-";
			disqNum3 += Integer.parseInt(request.getParameter("disqNum3-"
					+ 11).trim());
		}
		
		for (int i = 1; i <= 10; i++) {
			if ("".equals(request.getParameter("disqNum4-" + i).trim())) {
				disqDetail4 += "0-";
				disqNum4 += 0;
			} else {
				disqDetail4 += request.getParameter("disqNum4-" + i).trim() + "-";
				disqNum4 += Integer.parseInt(request.getParameter("disqNum4-"
						+ i).trim());
			}
		}
		if ("".equals(request.getParameter("disqNum4-" + 11).trim())) {
			disqDetail4 += "0-";
			disqNum4 += 0;
		} else {
			disqDetail4 += request.getParameter("disqNum4-" + 11).trim() + "-";
			disqNum4 += Integer.parseInt(request.getParameter("disqNum4-"
					+ 11).trim());
		}
		
		for (int i = 1; i <= 10; i++) {
			if ("".equals(request.getParameter("disqNum5-" + i).trim())) {
				disqDetail5 += "0-";
				disqNum5 += 0;
			} else {
				disqDetail5 += request.getParameter("disqNum5-" + i).trim() + "-";
				disqNum5 += Integer.parseInt(request.getParameter("disqNum5-"
						+ i).trim());
			}
		}
		if ("".equals(request.getParameter("disqNum5-" + 11).trim())) {
			disqDetail5 += "0-";
			disqNum5 += 0;
		} else {
			disqDetail5 += request.getParameter("disqNum5-" + 11).trim() + "-";
			disqNum5 += Integer.parseInt(request.getParameter("disqNum5-"
					+ 11).trim());
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
	
	//删除工单
	public String formdelete(){
		String state;
		String wfid = request.getParameter("wfId");
		WorkFormDeleteService wfds = new WorkFormDeleteService();
		state = wfds.wfdelete(wfid);
		return state;
	}

	//添加工单时的产品，工序，员工同步显示
	public String preAddProNo() {
		mwfs = new ManageWorkFormService();
		String proNo = request.getParameter("proNo");
		String result = mwfs.preAddProNo(proNo);
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String preAddStaNo() {
		mwfs = new ManageWorkFormService();
		String staNo = request.getParameter("staNo");
		String result = mwfs.preAddStaNo(staNo);
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String preAddProcNo() {
		mwfs = new ManageWorkFormService();
		String procNo = request.getParameter("procNo");
		String proNo = request.getParameter("proNo");
		String batNo = request.getParameter("batNo");
		String result = mwfs.preAddProcNo(procNo, proNo, batNo);
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
