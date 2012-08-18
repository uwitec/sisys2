package com.sisys.service;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;

import com.sisys.bean.*;
import com.sisys.dao.*;

public class WorkFormSearchService {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	ActionContext context = ActionContext.getContext();
	HttpServletRequest request = (HttpServletRequest) context
			.get(StrutsStatics.HTTP_REQUEST);
	Map session = context.getSession();

	public String FirstPage() {
		String result = "";
		User user = (User) session.get("user");
		String sql = "select * from workform where isdelete=0 order by id desc limit 6";
		WorkFormDAO wfd = new WorkFormDAO();
		List<WFstandard> wfs = new ArrayList<WFstandard>();
		List<WorkForm> list = wfd.findEntityByList(sql);
		if (list.size() != 0) {
			request.setAttribute("page", list);
		} else {
			switch (user.getLevel()) {
			case 1:
				result = "viewererror";
				return result;
			case 2:
				result = "operatorerror";
				return result;
			case 3:
				result = "adminerror";
				return result;
			}
		}
		// 根据page查询工单
		for (int i = 0; i < list.size(); i++) {
			WFstandard wfsave = new WFstandard();
			SmallWfDAO swd = new SmallWfDAO();
			sql = "select * from smallwf where wfid=" + list.get(i).getId();
			List<SmallWf> swlist = swd.findEntityByList(sql);
			ProcessesDAO procd = new ProcessesDAO();
			sql = "select * from processes where Id=" + list.get(i).getProcId();
			List<Processes> proclist = procd.findEntityByList(sql);
			ProductDAO prod = new ProductDAO();
			sql = "select * from product where proNo='"
					+ swlist.get(0).getProNo() + "'";
			List<Product> prolist = prod.findEntityByList(sql);
			BatchDAO batd = new BatchDAO();
			sql = "select * from batch where Id=" + list.get(i).getBatchId();
			List<Batch> batlist = batd.findEntityByList(sql);
			wfsave.setBatchNo(batlist.get(0).getBatchNo());
			wfsave.setProcName(proclist.get(0).getProcName());
			wfsave.setProcNo(proclist.get(0).getProcNo());
			wfsave.setProName(prolist.get(0).getProName());
			wfsave.setProNo(prolist.get(0).getProNo());
			wfsave.setQuaNum(list.get(i).getQuaNum());
			wfsave.setWfId(list.get(i).getId());
			wfsave.setDisqNum(list.get(i).getDisquaNum());
			Date date = list.get(i).getDeleteTime();
			String time = "";
			if (date != null) {
				time = df.format(date);
			}
			wfsave.setDeletetime(time);
			if (list.get(i).getIsDelete() == 1) {
				wfsave.setStatus("是");
			} else {
				wfsave.setStatus("否");
			}
			wfs.add(wfsave);
		}
		/*
		 * if (workform.size() == 0) { return "error"; }
		 */
		request.setAttribute("form", wfs);
		// request.setAttribute("page", page);

		switch (user.getLevel()) {
		case 1:
			result = "viewer";
			break;
		case 2:
			result = "operator";
			break;
		case 3:
			result = "admin";
			break;
		}
		return result;
	}

	/**
	 * 按批次搜索工单
	 * 
	 * @return
	 */
	public String searchByBatch() {

		String result = "";
		String batchNo = request.getParameter("batchNo");
		String proNo = request.getParameter("proNo");

		if ("".equals(batchNo) && "".equals(proNo)) {

			User user = (User) session.get("user");
			switch (user.getLevel()) {
			case 1:
				result = "errorviewer";
				break;
			case 2:
				result = "erroroperator";
				break;
			case 3:
				result = "erroradmin";
				break;
			}
		} else {
			List<WFstandard> wfslist = new ArrayList<WFstandard>();

			wfslist = this.Conditionsearch(proNo, batchNo);

			User user = (User) session.get("user");
			if (wfslist != null) {
				request.setAttribute("form", wfslist);

				switch (user.getLevel()) {
				case 1:
					result = "viewer";
					break;
				case 2:
					result = "operator";
					break;
				case 3:
					result = "admin";
					break;
				}
			} else {
				switch (user.getLevel()) {
				case 1:
					result = "inputerrorviewer";
					break;
				case 2:
					result = "inputerroroperator";
					break;
				case 3:
					result = "inputerroradmin";
					break;
				}
			}
		}
		return result;
	}

	public List<WFstandard> Conditionsearch(String proNo, String batNo) {
		String sql = "";
		ProductDAO prol = new ProductDAO();
		sql = "select * from product where proNo='" + proNo + "'";
		List<Product> plist = prol.findEntityByList(sql);
		if (plist.size() == 0) {
			return null;
		}

		BatchDAO batl = new BatchDAO();
		sql = "select * from batch where proId='" + plist.get(0).getId()
				+ "' and batchNo='" + batNo + "'";
		List<Batch> batlist = batl.findEntityByList(sql);
		if (batlist.size() == 0) {
			return null;
		}
		sql = "select * from workForm where batchId=" + batlist.get(0).getId()
				+ " and isdelete=0 order by procId";
		WorkFormDAO wfd = new WorkFormDAO();
		List<WorkForm> list = wfd.findEntityByList(sql);
		System.out.println(list.size());
		List<WFstandard> wfs = new ArrayList<WFstandard>();

		for (int i = 0; i < list.size(); i++) {
			WFstandard wfsave = new WFstandard();
			SmallWfDAO swd = new SmallWfDAO();
			sql = "select * from smallwf where wfid=" + list.get(i).getId();
			List<SmallWf> swlist = swd.findEntityByList(sql);
			ProcessesDAO procd = new ProcessesDAO();
			sql = "select * from processes where Id=" + list.get(i).getProcId();
			List<Processes> proclist = procd.findEntityByList(sql);
			ProductDAO prod = new ProductDAO();
			sql = "select * from product where proNo='"
					+ swlist.get(0).getProNo() + "'";
			List<Product> prolist = prod.findEntityByList(sql);
			BatchDAO batd = new BatchDAO();
			sql = "select * from batch where Id=" + list.get(i).getBatchId();
			batlist = new ArrayList<Batch>();
			batlist = batd.findEntityByList(sql);
			wfsave.setBatchNo(batlist.get(0).getBatchNo());
			wfsave.setProcName(proclist.get(0).getProcName());
			wfsave.setProcNo(proclist.get(0).getProcNo());
			wfsave.setProName(prolist.get(0).getProName());
			wfsave.setProNo(prolist.get(0).getProNo());
			wfsave.setQuaNum(list.get(i).getQuaNum());
			wfsave.setWfId(list.get(i).getId());
			wfsave.setDisqNum(list.get(i).getDisquaNum());
			Date date = list.get(i).getDeleteTime();
			String time = "";
			if (date != null) {
				time = df.format(date);
			}
			wfsave.setDeletetime(time);
			if (list.get(i).getIsDelete() == 1) {
				wfsave.setStatus("是");
			} else {
				wfsave.setStatus("否");
			}
			wfs.add(wfsave);
		}
		return wfs;
	}

	// 查看工单详情
	public String detail() {
		String result = "";

		WorkFormDAO wfd = new WorkFormDAO();
		int Id = Integer.parseInt(request.getParameter("wfId"));
		String sql = "select * from workform where Id=" + Id;
		List<WorkForm> wflist = wfd.findEntityByList(sql);
		if (wflist.size() == 0) {
			return "error";
		}
		WorkForm wf = wflist.get(0);
		WFstandard wfsave = new WFstandard();

		wfsave.setWfId(Id);
		String name = wf.getName();

		// 查询对应小工单
		SmallWfDAO swd = new SmallWfDAO();
		sql = "select * from smallwf where wfid=" + wf.getId();
		List<SmallWf> swlist = swd.findEntityByList(sql);
		// 查询并存储大工单信息
		ProcessesDAO procd = new ProcessesDAO();
		sql = "select * from processes where Id=" + wf.getProcId();
		List<Processes> proclist = procd.findEntityByList(sql);
		ProductDAO prod = new ProductDAO();
		sql = "select * from product where proNo='" + swlist.get(0).getProNo()
				+ "'";
		List<Product> prolist = prod.findEntityByList(sql);
		BatchDAO batd = new BatchDAO();
		sql = "select * from batch where Id=" + wf.getBatchId();
		List<Batch> batlist = batd.findEntityByList(sql);
		wfsave.setBatchNo(batlist.get(0).getBatchNo());
		wfsave.setProcName(proclist.get(0).getProcName());
		wfsave.setProcNo(proclist.get(0).getProcNo());
		wfsave.setProName(prolist.get(0).getProName());
		wfsave.setProNo(prolist.get(0).getProNo());
		wfsave.setWfId(wf.getId());
		if (wf.getIsDelete() == 0) {
			wfsave.setStatus("否");
			wfsave.setDeletetime("");
		} else {
			wfsave.setStatus("是");
			wfsave.setDeletetime(df.format(wf.getDeleteTime()));
		}

		// 根据小工单信息得到对应的原始工单数据
		String[] disq1 = new String[] { "", "", "", "", "", "", "", "", "", "",
				"" };
		String[] disq2 = new String[] { "", "", "", "", "", "", "", "", "", "",
				"" };
		String[] disq3 = new String[] { "", "", "", "", "", "", "", "", "", "",
				"" };
		String[] disq4 = new String[] { "", "", "", "", "", "", "", "", "", "",
				"" };
		String[] disq5 = new String[] { "", "", "", "", "", "", "", "", "", "",
				"" };
		int num = 0;// 填入工单员工数量
		wfsave.setStaNo1(swlist.get(0).getStaNo());
		sql = "select * from staff where staNo='" + wfsave.getStaNo1() + "'";
		StaffDAO std = new StaffDAO();
		List<Staff> stalist1 = std.findEntityByList(sql);
		wfsave.setStaName1(stalist1.get(0).getStaName());
		wfsave.setQuaNum1(String.valueOf(swlist.get(0).getQuaNum()));
		disq1 = swlist.get(0).getDisqDetail().split("-");
		wfsave.setDisqNum1(disq1);
		num++;
		if (num < swlist.size()) {
			// 如果已存小工单数量小于原工单员工数量，则继续添加
			wfsave.setStaNo2(swlist.get(1).getStaNo());
			sql = "select * from staff where staNo='" + wfsave.getStaNo2()
					+ "'";
			std = new StaffDAO();
			List<Staff> stalist2 = std.findEntityByList(sql);
			wfsave.setStaName2(stalist2.get(0).getStaName());
			wfsave.setQuaNum2(String.valueOf(swlist.get(1).getQuaNum()));
			disq2 = swlist.get(1).getDisqDetail().split("-");
			wfsave.setDisqNum2(disq2);
		} else {
			wfsave.setStaNo2("");
			wfsave.setStaName2("");
			wfsave.setQuaNum2("");
			wfsave.setDisqNum2(disq2);
		}
		num++;
		if (num < swlist.size()) {
			// 如果已存小工单数量小于原工单员工数量，则继续添加
			wfsave.setStaNo3(swlist.get(2).getStaNo());
			sql = "select * from staff where staNo='" + wfsave.getStaNo3()
					+ "'";
			std = new StaffDAO();
			List<Staff> stalist3 = std.findEntityByList(sql);
			wfsave.setStaName3(stalist3.get(0).getStaName());
			wfsave.setQuaNum3(String.valueOf(swlist.get(2).getQuaNum()));
			disq3 = swlist.get(2).getDisqDetail().split("-");
			wfsave.setDisqNum3(disq3);
		} else {
			wfsave.setStaNo3("");
			wfsave.setStaName3("");
			wfsave.setQuaNum3("");
			wfsave.setDisqNum3(disq3);
		}
		num++;
		if (num < swlist.size()) {
			// 如果已存小工单数量小于原工单员工数量，则继续添加
			wfsave.setStaNo4(swlist.get(3).getStaNo());
			sql = "select * from staff where staNo='" + wfsave.getStaNo4()
					+ "'";
			std = new StaffDAO();
			List<Staff> stalist4 = std.findEntityByList(sql);
			wfsave.setStaName4(stalist4.get(0).getStaName());
			wfsave.setQuaNum4(String.valueOf(swlist.get(3).getQuaNum()));
			disq4 = swlist.get(3).getDisqDetail().split("-");
			wfsave.setDisqNum4(disq4);
		} else {
			wfsave.setStaNo4("");
			wfsave.setStaName4("");
			wfsave.setQuaNum4("");
			wfsave.setDisqNum4(disq4);
		}
		num++;
		if (num < swlist.size()) {
			// 如果已存小工单数量小于原工单员工数量，则继续添加
			wfsave.setStaNo5(swlist.get(4).getStaNo());
			sql = "select * from staff where staNo='" + wfsave.getStaNo5()
					+ "'";
			std = new StaffDAO();
			List<Staff> stalist5 = std.findEntityByList(sql);
			wfsave.setStaName5(stalist5.get(0).getStaName());
			wfsave.setQuaNum5(String.valueOf(swlist.get(4).getQuaNum()));
			disq5 = swlist.get(4).getDisqDetail().split("-");
			wfsave.setDisqNum5(disq5);
		} else {
			wfsave.setStaNo5("");
			wfsave.setStaName5("");
			wfsave.setQuaNum5("");
			wfsave.setDisqNum5(disq5);
		}

		request.setAttribute("wfsave", wfsave);
		request.setAttribute("name", name);

		User user = (User) session.get("user");
		switch (user.getLevel()) {
		case 1:
			result = "viewer";
			break;
		case 2:
			result = "operator";
			break;
		case 3:
			result = "admin";
			break;
		}
		return result;
	}

	// 条形码判真伪，解析条码
	public String codeAnalysis() {
		String code = request.getParameter("barCode");
		System.out.println(code);
		System.out.println(code.length());
		String batNo = code.substring(0, 10);
		String proNo = code.substring(10, code.length() - 2);
		char[] str = code.toCharArray();
		int oddNum = 0;
		int evenNum = 0;
		int num;
		for (int i = 0; i < str.length - 1; i += 2) {
			oddNum += Integer.parseInt(str[i] + "");
		}
		for (int i = 1; i < str.length - 1; i += 2) {
			evenNum += Integer.parseInt(str[i] + "");
		}
		num = oddNum * 3 + evenNum;
		if (Integer.parseInt(str[code.length() - 1] + "") != (10 - num % 10) % 10) {
			return "error";
		}
		String sql;
		sql = "select * from product where proNo='" + proNo + "'";
		ProductDAO prod = new ProductDAO();
		List<Product> prolist = prod.findEntityByList(sql);
		if (prolist.size() == 0) {
			return "error";
		}
		sql = "select * from batch where batchNo='" + batNo + "' and proId="
				+ prolist.get(0).getId();
		BatchDAO batd = new BatchDAO();
		List<Batch> batlist = batd.findEntityByList(sql);
		if (batlist.size() == 0) {
			return "error";
		}

		User user = (User) session.get("user");
		String name = user.getUsername();

		request.setAttribute("name", name);
		request.setAttribute("proNo", proNo);
		request.setAttribute("batNo", batNo);
		request.setAttribute("proName", prolist.get(0).getProName());

		return "success";
	}
}
