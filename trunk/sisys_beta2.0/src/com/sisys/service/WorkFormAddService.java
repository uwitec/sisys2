package com.sisys.service;

import java.text.SimpleDateFormat;
import java.util.*;

import com.sisys.bean.Batch;
import com.sisys.bean.DisqKind;
import com.sisys.bean.DisqKindDetail;
import com.sisys.bean.Flowpath;
import com.sisys.bean.Processes;
import com.sisys.bean.Product;
import com.sisys.bean.ScheduleTab;
import com.sisys.bean.SmallWf;
import com.sisys.bean.WorkForm;
import com.sisys.dao.BatchDAO;
import com.sisys.dao.DisqKindDAO;
import com.sisys.dao.DisqKindDetailDAO;
import com.sisys.dao.FlowpathDAO;
import com.sisys.dao.ProcessesDAO;
import com.sisys.dao.ProductDAO;
import com.sisys.dao.ScheduleTabDAO;
import com.sisys.dao.SmallWfDAO;
import com.sisys.dao.WorkFormDAO;

public class WorkFormAddService {
	private SmallWf wf1 = new SmallWf();
	private SmallWf wf2 = new SmallWf();
	private SmallWf wf3 = new SmallWf();
	private SmallWf wf4 = new SmallWf();
	private SmallWf wf5 = new SmallWf();
	private WorkForm work = new WorkForm();
	private Batch bat = new Batch();
	private Flowpath flow = new Flowpath();
	private Processes proc = new Processes();
	private ScheduleTab sche = new ScheduleTab();

	Date date = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String time = df.format(date);

	public String wfadd(String proNo, String procNo, String batNo,
			String staNo1, String quaNum1, String disqDetail1, int disqNum1,
			String staNo2, String quaNum2, String disqDetail2, int disqNum2,
			String staNo3, String quaNum3, String disqDetail3, int disqNum3,
			String staNo4, String quaNum4, String disqDetail4, int disqNum4,
			String staNo5, String quaNum5, String disqDetail5, int disqNum5,
			String name) {
		String sql;
		int quaNum = 0;// 大工单合格品数量
		int disqNum = 0;// 大工单不合格品数量
		quaNum = Integer.parseInt(quaNum1) + Integer.parseInt(quaNum2)
				+ Integer.parseInt(quaNum3) + Integer.parseInt(quaNum4)
				+ Integer.parseInt(quaNum5);
		disqNum = disqNum1 + disqNum2 + disqNum3 + disqNum4 + disqNum5;
		// 搜索产品表，得到产品Id
		sql = "select * from product where proNo='" + proNo + "'";
		ProductDAO prod = new ProductDAO();
		List<Product> prolist = prod.findEntityByList(sql);
		// 搜索批次表，得到批次id、流程id
		sql = "select * from batch where batchNo='" + batNo + "' and proId="
				+ prolist.get(0).getId();
		BatchDAO batd = new BatchDAO();
		List<Batch> batlist = batd.findEntityByList(sql);
		if (batlist.size() == 0) {
			return "error";
		}
		bat = batlist.get(0);
		flow.setId(bat.getFlowId());
		// 搜索流程表，得到流程顺序
		sql = "select * from flowpath where Id=" + flow.getId();
		FlowpathDAO fpd = new FlowpathDAO();
		List<Flowpath> flowlist = fpd.findEntityByList(sql);
		flow = flowlist.get(0);
		String[] str = flow.getSequence().split("-");
		proc.setId(Integer.parseInt(str[Integer.parseInt(procNo) - 1]));
		// 搜索工序表，得到工序的id、班产量、工时单价
		sql = "select * from processes where Id=" + proc.getId();
		ProcessesDAO procd = new ProcessesDAO();
		List<Processes> proclist = procd.findEntityByList(sql);
		proc = proclist.get(0);
		// 搜索进度表，并判断前后工序关系
		sql = "select * from scheduletab where batchId=" + bat.getId();
		ScheduleTabDAO std = new ScheduleTabDAO();
		List<ScheduleTab> schelist = std.findEntityByList(sql);
		sche = schelist.get(Integer.parseInt(procNo) - 1);
		System.out.println(sche.getQuaNum());
		if (sche.getQuaNum() != 0) {
			return "error";
		}
		// 如果是第一道工序，则num为0
		if ("1".equals(procNo)) {
			sche.setNum(0);
		} else {// 如果不是第一道工序，则num是上个工序的num与disqnum之和
			sche.setNum(schelist.get(Integer.parseInt(procNo) - 2).getNum()
					+ schelist.get(Integer.parseInt(procNo) - 2).getDisqNum());
		}
		// 判断是否逻辑的正确性
		if (sche.getNum() + quaNum + disqNum != bat.getTotalNum()) {
			return "outofline";
		}
		if (sche.getIsEnd() == 1) {
			if (bat.getStatus() == 2) {
				bat.setStatus(4);
			} else if (bat.getStatus() == 0) {
				bat.setStatus(1);
			}
			batd = new BatchDAO();
			batd.update(bat, 1);
		}
		sche.setColorNo(proc.getColorNo());
		sche.setDisqNum(disqNum);
		sche.setQuaNum(quaNum);
		sche.setTime(date);
		work.setBatchId(bat.getId());
		work.setDisquaNum(disqNum);
		work.setIsDelete(0);
		work.setName(name);
		work.setProcId(proc.getId());
		work.setQuaNum(quaNum);
		work.setTime(date);
		std = new ScheduleTabDAO();
		std.update(sche, 1);
		WorkFormDAO wfd = new WorkFormDAO();
		wfd.create(work);
		// 搜索工单，找到刚刚存储的工单id
		sql = "select * from workform where batchId=" + bat.getId()
				+ " and procId=" + proc.getId();
		wfd = new WorkFormDAO();
		List<WorkForm> worklist = wfd.findEntityByList(sql);
		work = worklist.get(0);
		SmallWfDAO swd;
		if (!"".equals(staNo1)) {
			this.staffwf(proNo, staNo1, quaNum1, disqDetail1, 1);
			swd = new SmallWfDAO();
			swd.create(wf1);
		}
		if (!"".equals(staNo2)) {
			this.staffwf(proNo, staNo2, quaNum2, disqDetail2, 2);
			swd = new SmallWfDAO();
			swd.create(wf2);
		}
		if (!"".equals(staNo3)) {
			this.staffwf(proNo, staNo3, quaNum3, disqDetail3, 3);
			swd = new SmallWfDAO();
			swd.create(wf3);
		}
		if (!"".equals(staNo4)) {
			this.staffwf(proNo, staNo4, quaNum4, disqDetail4, 4);
			swd = new SmallWfDAO();
			swd.create(wf4);
		}
		if (!"".equals(staNo5)) {
			this.staffwf(proNo, staNo5, quaNum5, disqDetail5, 5);
			swd = new SmallWfDAO();
			swd.create(wf5);
		}
		return "success";
	}

	public void staffwf(String proNo, String staNo, String quaNum,
			String disqDetail, int No) {
		SmallWf small = new SmallWf();
		String sql;
		String[] str;
		small.setStaNo(staNo);
		small.setDisqDetail(disqDetail);
		small.setgWasteNum(0);
		small.setlWasteNum(0);
		small.setProcId(proc.getId());
		small.setProNo(proNo);
		small.setQuaNum(Integer.parseInt(quaNum));
		small.setBworkHours((double) small.getQuaNum() * 8.0
				/ proc.getUnitOutput());
		small.setSalary(small.getBworkHours() * proc.getUnitCost());
		small.setTime(date);
		small.setWfId(work.getId());
		str = disqDetail.split("-");
		// 在不合格种类详情中搜索今日不合格详情，有则更新，没有则建立
		DisqKindDetailDAO dkdd;
		DisqKindDAO disqd;
		for (int i = 1; i <= str.length; i++) {
			sql = "select * from disqkinddetail where disqkid=" + i
					+ " and time='" + time + "'";
			dkdd = new DisqKindDetailDAO();
			List<DisqKindDetail> dkdlist = dkdd.findEntityByList(sql);
			if (dkdlist.size() == 0) {
				DisqKindDetail dkd = new DisqKindDetail();
				dkd.setDisqKId(i);
				dkd.setDisqKNum(Integer.parseInt(str[i - 1]));
				dkd.setTime(date);
				dkdd = new DisqKindDetailDAO();
				dkdd.create(dkd);
			} else {
				DisqKindDetail dkd = dkdlist.get(0);
				dkd.setDisqKNum(dkd.getDisqKNum()
						+ Integer.parseInt(str[i - 1]));
				dkdd = new DisqKindDetailDAO();
				dkdd.update(dkd, 1);
			}
			sql = "select * from disqkind where Id=" + i;
			disqd = new DisqKindDAO();
			List<DisqKind> dlist = disqd.findEntityByList(sql);
			if (dlist.get(0).getKind() == 0) {
				small.setgWasteNum(Integer.parseInt(str[i - 1]) + small.getgWasteNum());
			} else {
				small.setlWasteNum(Integer.parseInt(str[i - 1]) + small.getlWasteNum());
			}
		}
		switch (No) {
		case 1:
			wf1 = small;
		case 2:
			wf2 = small;
		case 3:
			wf3 = small;
		case 4:
			wf4 = small;
		case 5:
			wf5 = small;
		}
	}
}