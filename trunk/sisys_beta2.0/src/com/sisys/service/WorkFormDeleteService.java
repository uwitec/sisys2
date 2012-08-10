package com.sisys.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sisys.bean.Batch;
import com.sisys.bean.DisqKindDetail;
import com.sisys.bean.Flowpath;
import com.sisys.bean.ScheduleTab;
import com.sisys.bean.SmallWf;
import com.sisys.bean.WorkForm;
import com.sisys.dao.BatchDAO;
import com.sisys.dao.DisqKindDetailDAO;
import com.sisys.dao.FlowpathDAO;
import com.sisys.dao.ScheduleTabDAO;
import com.sisys.dao.SmallWfDAO;
import com.sisys.dao.WorkFormDAO;

public class WorkFormDeleteService {
	private WorkForm work = new WorkForm();
	private Batch bat = new Batch();
	private Flowpath flow = new Flowpath();
	private ScheduleTab sche = new ScheduleTab();

	Date date = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String time = df.format(date);

	public String wfdelete(String wfid) {
		String sql;
		// 搜索工单表，找到相应的工单
		sql = "select * from workform where Id=" + wfid;
		WorkFormDAO wfd = new WorkFormDAO();
		wfd = new WorkFormDAO();
		List<WorkForm> worklist = wfd.findEntityByList(sql);
		work = worklist.get(0);
		if (work.getIsDelete() == 1) {
			return "isdelete";
		}
		// 搜索批次表
		sql = "select * from batch where Id=" + work.getBatchId();
		BatchDAO batd = new BatchDAO();
		List<Batch> batlist = batd.findEntityByList(sql);
		bat = batlist.get(0);
		flow.setId(bat.getFlowId());
		// 搜索流程表，得到流程顺序
		sql = "select * from flowpath where Id=" + flow.getId();
		FlowpathDAO fpd = new FlowpathDAO();
		List<Flowpath> flowlist = fpd.findEntityByList(sql);
		flow = flowlist.get(0);
		String[] str = flow.getSequence().split("-");
		int i = 0;
		for (; i < str.length; i++) {
			if (Integer.parseInt(str[i]) == work.getProcId()) {
				break;//找到相应工序的位置
			}
		}
		// 搜索进度表，并判断前后工序关系
		sql = "select * from scheduletab where batchId=" + bat.getId();
		ScheduleTabDAO std = new ScheduleTabDAO();
		List<ScheduleTab> schelist = std.findEntityByList(sql);
		sche = schelist.get(i);
		// 如果不是最后一道工序，则检查是否有后工序工单存在，若有，则不能删除
		if (i != str.length - 1) {
			if (schelist.get(i + 1).getQuaNum() != 0) {
				return "outofline";
			}
		} else {
			//如果所删除工单是该批次最后一个工序，则修改完成标志
			if (bat.getStatus() == 1) {
				if(date.after(bat.getEndTime())){
					bat.setStatus(2);
				} else {
					bat.setStatus(0);
				}
			} else if (bat.getStatus() == 4) {
				bat.setStatus(2);
			}
			batd = new BatchDAO();
			batd.update(bat, 1);
		}
		sche.setDisqNum(0);
		sche.setNum(0);
		sche.setQuaNum(0);
		sche.setTime(null);
		work.setIsDelete(1);
		work.setDeleteTime(date);
		std = new ScheduleTabDAO();
		std.update(sche, 1);
		//搜索小工单，找到与该大工单对应的若干小工单
		sql = "select * from smallwf where wfid=" + work.getId();
		SmallWfDAO swd = new SmallWfDAO();
		List<SmallWf> smalllist = swd.findEntityByList(sql);
		for(int j = 0;j < smalllist.size();j++){
			this.deleteswf(smalllist.get(j));
		}
		return "success";
	}
	
	public void deleteswf(SmallWf wf){
		String sql;
		String[] str;
		str = wf.getDisqDetail().split("-");
		DisqKindDetailDAO dkdd;
		for(int i = 1; i <= str.length; i++){
			sql = "select * from disqkinddetail where disqkid=" + i
					+ " and time='" + time + "'";
			dkdd = new DisqKindDetailDAO();
			List<DisqKindDetail> dkdlist = dkdd.findEntityByList(sql);
			dkdlist.get(0).setDisqKNum(dkdlist.get(0).getDisqKNum() - Integer.parseInt(str[i - 1]));
			dkdd = new DisqKindDetailDAO();
			dkdd.update(dkdlist.get(0), 1);
		}
	}
}
