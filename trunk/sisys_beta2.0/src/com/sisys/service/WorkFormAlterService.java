package com.sisys.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

public class WorkFormAlterService {
	private SmallWf wf1 = new SmallWf();
	private SmallWf wf2 = new SmallWf();
	private SmallWf wf3 = new SmallWf();
	private SmallWf wf4 = new SmallWf();
	private SmallWf wf5 = new SmallWf();
	private WorkForm work = new WorkForm();
	private WorkForm worksave = new WorkForm();
	private Batch bat = new Batch();
	private Flowpath flow = new Flowpath();
	private Processes proc = new Processes();
	private ScheduleTab sche = new ScheduleTab();
	private Batch batsave = new Batch();
	private Flowpath flowsave = new Flowpath();
	private ScheduleTab schesave = new ScheduleTab();

	Date date = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String time = df.format(date);

	public String wfalter(String wfid, String proNo, String procNo,
			String batNo, String staNo1, String quaNum1, String disqDetail1,
			int disqNum1, String staNo2, String quaNum2, String disqDetail2,
			int disqNum2, String staNo3, String quaNum3, String disqDetail3,
			int disqNum3, String staNo4, String quaNum4, String disqDetail4,
			int disqNum4, String staNo5, String quaNum5, String disqDetail5,
			int disqNum5) {
		String sql;
		// 搜索工单表，找到要修改的工单
		sql = "select * from workform where Id=" + wfid;
		WorkFormDAO wfd = new WorkFormDAO();
		wfd = new WorkFormDAO();
		List<WorkForm> worklist = wfd.findEntityByList(sql);
		worksave = worklist.get(0);
		if (work.getIsDelete() == 1) {
			return "isdelete";
		}
		work.setId(worksave.getId());
		work.setName(worksave.getName());
		work.setTime(worksave.getTime());
		work.setIsDelete(0);
		int quaNum = 0;// 大工单合格品数量
		int disqNum = 0;// 大工单不合格品数量
		quaNum = Integer.parseInt(quaNum1) + Integer.parseInt(quaNum2)
				+ Integer.parseInt(quaNum3) + Integer.parseInt(quaNum4)
				+ Integer.parseInt(quaNum5);
		disqNum = disqNum1 + disqNum2 + disqNum3 + disqNum4 + disqNum5;
		/*** 判断原工单逻辑是否错误 ****/
		// 搜索批次表
		sql = "select * from batch where Id=" + worksave.getBatchId();
		BatchDAO batd = new BatchDAO();
		List<Batch> batl = batd.findEntityByList(sql);
		batsave = batl.get(0);
		flowsave.setId(batsave.getFlowId());
		// 搜索流程表，得到流程顺序
		sql = "select * from flowpath where Id=" + flowsave.getId();
		FlowpathDAO fpd = new FlowpathDAO();
		List<Flowpath> flowl = fpd.findEntityByList(sql);
		flowsave = flowl.get(0);
		String[] str1 = flowsave.getSequence().split("-");
		int i = 0;
		for (; i < str1.length; i++) {
			if (Integer.parseInt(str1[i]) == worksave.getProcId()) {
				break;// 找到相应工序的位置
			}
		}
		// 搜索进度表，并判断前后工序关系
		sql = "select * from scheduletab where batchId=" + batsave.getId();
		ScheduleTabDAO std = new ScheduleTabDAO();
		List<ScheduleTab> schel = std.findEntityByList(sql);
		schesave = schel.get(i);
		/*** 判断原工单逻辑结束 ****/
		/*** 判断新的工单逻辑是否错误 ****/
		// 搜索产品表，得到产品Id
		sql = "select * from product where proNo='" + proNo + "'";
		ProductDAO prod = new ProductDAO();
		List<Product> prolist = prod.findEntityByList(sql);
		// 搜索批次表，得到新批次id、流程id
		sql = "select * from batch where batchNo='" + batNo + "' and proId="
				+ prolist.get(0).getId();
		batd = new BatchDAO();
		List<Batch> batlist = batd.findEntityByList(sql);
		if (batlist.size() == 0) {
			return "error";
		}
		bat = batlist.get(0);
		flow.setId(bat.getFlowId());
		// 搜索流程表，得到流程顺序
		sql = "select * from flowpath where Id=" + flow.getId();
		fpd = new FlowpathDAO();
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
		std = new ScheduleTabDAO();
		List<ScheduleTab> schelist = std.findEntityByList(sql);
		sche = schelist.get(Integer.parseInt(procNo) - 1);
		if (sche.getQuaNum() != 0 && sche.getId() != schesave.getId()) {
			return "outofline";
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
		/*
		 * 判断原工单是否是最后一道工序，若是 判断后工序是否已完成，若后工序已完成;
		 * 1.若修改后的工单所对应的批次及工序与原工单都相同，判断整体逻辑 ; 
		 * 2.若修改后的工单所对应的批次及工序和原工单不同，直接返回错误。
		 */
		if (i != str1.length - 1) {
			if (schel.get(i + 1).getQuaNum() != 0) {
				if (schesave.getId() == sche.getId()) {
					if (quaNum != schel.get(i + 1).getQuaNum()
							+ schel.get(i + 1).getDisqNum()) {
						return "outofline";
					}
				} else {
					return "outofline";
				}
			}
		}
		/*** 判断新的工单逻辑结束 ****/
		/*** 清空原工单所存逻辑 ****/
		schesave.setDisqNum(0);
		schesave.setQuaNum(0);
		schesave.setNum(0);
		schesave.setTime(null);
		std = new ScheduleTabDAO();
		std.update(schesave, 1);
		// 搜索小工单，找到与该大工单对应的若干小工单
		sql = "select * from smallwf where wfid=" + work.getId();
		SmallWfDAO swd = new SmallWfDAO();
		List<SmallWf> smalll = swd.findEntityByList(sql);
		for (int j = 0; j < smalll.size(); j++) {
			this.deleteswf(smalll.get(j));
			swd = new SmallWfDAO();
			swd.delete(smalll.get(j));
		}
		if (i == str1.length - 1) {
			// 如果所删除工单是共批次最后一个工序，则修改完成标志
			if (batsave.getStatus() == 1) {
				if (date.after(batsave.getEndTime())) {
					batsave.setStatus(2);
				} else {
					batsave.setStatus(0);
				}
			} else if (batsave.getStatus() == 4) {
				batsave.setStatus(2);
			}
			batd = new BatchDAO();
			batd.update(batsave, 1);
		}
		/*** 原工单逻辑清空结束 ****/
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
		sche.setTime(work.getTime());
		work.setBatchId(bat.getId());
		work.setDisquaNum(disqNum);
		work.setProcId(proc.getId());
		work.setQuaNum(quaNum);
		std = new ScheduleTabDAO();
		std.update(sche, 1);
		wfd = new WorkFormDAO();
		wfd.update(work, 1);
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

	public void deleteswf(SmallWf wf) {
		String sql;
		String[] str;
		str = wf.getDisqDetail().split("-");
		DisqKindDetailDAO dkdd;
		for (int i = 1; i <= str.length; i++) {
			sql = "select * from disqkinddetail where disqkid=" + i
					+ " and time='" + df.format(worksave.getTime()) + "'";
			dkdd = new DisqKindDetailDAO();
			List<DisqKindDetail> dkdlist = dkdd.findEntityByList(sql);
			dkdlist.get(0)
					.setDisqKNum(
							dkdlist.get(0).getDisqKNum()
									- Integer.parseInt(str[i - 1]));
			dkdd = new DisqKindDetailDAO();
			dkdd.update(dkdlist.get(0), 1);
		}
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
		small.setTime(worksave.getTime());
		small.setWfId(work.getId());
		str = disqDetail.split("-");
		// 在不合格种类详情中搜索今日不合格详情，有则更新，没有则建立
		DisqKindDetailDAO dkdd;
		DisqKindDAO disqd;
		for (int i = 1; i <= str.length; i++) {
			sql = "select * from disqkinddetail where disqkid=" + i
					+ " and time='" + df.format(worksave.getTime()) + "'";
			dkdd = new DisqKindDetailDAO();
			List<DisqKindDetail> dkdlist = dkdd.findEntityByList(sql);
			if (dkdlist.size() == 0) {
				DisqKindDetail dkd = new DisqKindDetail();
				dkd.setDisqKId(i);
				dkd.setDisqKNum(Integer.parseInt(str[i - 1]));
				dkd.setTime(worksave.getTime());
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
