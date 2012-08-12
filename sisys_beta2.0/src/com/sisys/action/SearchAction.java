package com.sisys.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import com.sisys.bean.Batch;
import com.sisys.bean.User;
import com.sisys.bean.ScheduleTab;

/*import com.sisys.service.SearchJdService;*/
import com.sisys.service.SearchJdService;
import com.sisys.service.SearchPd2Service;
import com.sisys.service.SearchPd3Service;
import com.sisys.service.SearchPd4Service;
import com.sisys.service.SearchPd5Service;
import com.sisys.service.SearchPdService;
import com.sisys.service.SearchPpService;
import com.sisys.service.SearchWhService;

//import com.sisys.service.SearchWhService;

public class SearchAction extends BaseAction {
	/**
	 * Ganjun
	 */
	private static final long serialVersionUID = 1L;
	private String level;  //用户权限
	// 工时表参数
	private String staNo;
	private String startTime;
	private String endTime;
	// 进度表参数
	private String proNo;
	private String batchNo;
	// 部门表参数
	private String deptNo;
	// 生产线参数
	private String lineNo;
	// 工时表输出
	Map<String, Object> mapWh = new HashMap<String, Object>();
	
	// 进度表输出
	Map<String, Object> mapJd = new HashMap<String, Object>();
	List<ScheduleTab> listJd = new ArrayList<ScheduleTab>();
	ActionContext ac = ActionContext.getContext();
	// 员工表输出
	Map<String, Object> mapPp = new HashMap<String, Object>();
	
	// 不合格产品统计输出
	Map<String, Object> mapPd = new HashMap<String, Object>();
	List<Batch> listPd = new ArrayList<Batch>();
	// 不合格批次输出
	Map<String, Object> mapPd2 = new HashMap<String, Object>();
	// List<DisqBatch> listPd2 =new ArrayList<DisqBatch>();
	Map<String, Object> mapPd3 = new HashMap<String, Object>();
	// 不合格部门输出
	Map<String, Object> mapPd4 = new HashMap<String, Object>();
	// 不合格生产线输出
	Map<String, Object> mapPd5 = new HashMap<String, Object>();

	@SuppressWarnings("unchecked")
	public String SearchWh() throws Exception {
		deptNo = request.getParameter("deptNo");
		startTime = request.getParameter("startTime");
		endTime = request.getParameter("endTime");
		System.out.println(deptNo);

		SearchWhService searchWhService = new SearchWhService();

		mapWh = searchWhService.Search(deptNo, startTime, endTime);
		if(mapWh.get("result").equals("error")){
			request.setAttribute("resultMap", mapWh);
			return ERROR;
		}
		mapWh = searchWhService.ShowWh(mapWh);
		request.setAttribute("resultMap", mapWh);
		if(mapWh.get("result").equals("error")){
			return ERROR;
		}
		return SUCCESS;
		

	}

	public String SearchJd() throws Exception {
		proNo = request.getParameter("proNo");
		startTime = request.getParameter("startTime");
		endTime = request.getParameter("endTime");

		SearchJdService searchJdService = new SearchJdService();
				

		mapJd = searchJdService.SearchJd(proNo, startTime, endTime);
		
		session.setAttribute("mapJd", mapJd);
		if (mapJd.get("result").equals("success")) {
			return SUCCESS;
		} else
			return ERROR;

	}
	
	public String SearchIndexJd() throws Exception {
		proNo = request.getParameter("proNo");
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = cal.getTime();
		time.setDate(1);
		String startTime = format.format(time);
		time.setMonth(time.getMonth() + 1);
		time.setDate(0);
		String endTime = format.format(time);

		SearchJdService searchJdService = new SearchJdService();
				

		mapJd = searchJdService.SearchJd(proNo, startTime, endTime);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap = searchJdService.ShowJd(mapJd);
		System.out.println(resultMap);
		request.setAttribute("resultMap", resultMap);
		if (resultMap.get("result").equals("success")) {
			return SUCCESS;
		} else
			return ERROR;

	}
	
	public String ShowJd() throws Exception{
		SearchJdService searchJdService = new SearchJdService();
		
		Map resultMap = (Map)session.getAttribute("mapJd");
		
		resultMap = searchJdService.ShowJd(resultMap);
		System.out.println(resultMap);
		request.setAttribute("resultMap", resultMap);
		if (resultMap.get("result").equals("success")) {
			return SUCCESS;
		} else
			return ERROR;
	}
	//通过部门员工不合格情况统计跳转到员工完成情况统计
	public String SearchPpByDisq() throws Exception {

		staNo = request.getParameter("staNo");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = cal.getTime();
		time.setDate(1);
		String startTime = format.format(time);
		time.setMonth(time.getMonth() + 1);
		time.setDate(0);
		String endTime = format.format(time);
		System.out.println(staNo);

		SearchPpService searchPpService = new SearchPpService();
		mapPp = searchPpService.SearchPp(staNo, startTime, endTime);

		if (mapPp.get("result").equals("success")) {
			
			ActionContext.getContext().put("Ppsheet", mapPp.get("list"));
			ActionContext.getContext().put("staName", mapPp.get("staName"));
			ActionContext.getContext().put("sTime", startTime);
			ActionContext.getContext().put("eTime", endTime);
			ActionContext.getContext().put("staNo", staNo);
			ActionContext.getContext().put("deptName", mapPp.get("deptName"));
			
			return SUCCESS;
		} else{
			ActionContext.getContext().put("message", mapPp.get("message"));	
			return ERROR;
		}
	}
	
//员工完成情况统计单
	public String SearchPp() throws Exception {

		staNo = request.getParameter("staNo");
		startTime = request.getParameter("startTime");
		endTime = request.getParameter("endTime");
		System.out.println(staNo);

		SearchPpService searchPpService = new SearchPpService();
		mapPp = searchPpService.SearchPp(staNo, startTime, endTime);

		if (mapPp.get("result").equals("success")) {
			
			ActionContext.getContext().put("Ppsheet", mapPp.get("list"));
			ActionContext.getContext().put("staName", mapPp.get("staName"));
			ActionContext.getContext().put("sTime", startTime);
			ActionContext.getContext().put("eTime", endTime);
			ActionContext.getContext().put("staNo", staNo);
			ActionContext.getContext().put("deptName", mapPp.get("deptName"));
			
			return SUCCESS;
		} else{
			ActionContext.getContext().put("message", mapPp.get("message"));	
			return ERROR;
		}
	}

	// 不合格产品统计
	@SuppressWarnings("unchecked")
	public String SearchPd() throws Exception {
		proNo = request.getParameter("proNo");
		startTime = request.getParameter("startTime");
		endTime = request.getParameter("endTime");
		System.out.println(proNo);

		SearchPdService searchPdService = new SearchPdService();

		mapPd = searchPdService.SearchPd(proNo, startTime, endTime);

		if (mapPd.get("result").equals("success")) {
			listPd = (List<Batch>) mapPd.get("list");
			System.out.println("fafaf"+mapPd.get("proName"));
			ActionContext.getContext().put("Pdsheet", mapPd.get("disqProduct"));
			ActionContext.getContext().put("proName", mapPd.get("proName"));
			ActionContext.getContext().put("proNo", proNo);
			ActionContext.getContext().put("sTime", startTime);
			ActionContext.getContext().put("eTime", endTime);
			ActionContext.getContext().put("TcompleteNum",
					mapPd.get("totalNum").toString());
			ActionContext.getContext().put("TdisqNum",
					mapPd.get("disqNum").toString());
			ActionContext.getContext().put("TdisqPercent",
					mapPd.get("disqPercent").toString());
			return SUCCESS;
		} else{
			ActionContext.getContext().put("message", mapPd.get("message"));
			return ERROR;
		}
	}

	// 不合格批次统计
	public String SearchPd2() throws Exception {
		batchNo = request.getParameter("batchNo");
		proNo = request.getParameter("proNo");
		startTime = request.getParameter("startTime");
		endTime = request.getParameter("endTime");
		System.out.println(batchNo);

		SearchPd2Service searchPd2Service = new SearchPd2Service();

		mapPd2 = searchPd2Service.SearchPd2(batchNo, proNo, startTime, endTime);

		if (mapPd2.get("result").equals("success")) {
			// listPd2=(List<DisqBatch>) mapPd2.get("list");

			ActionContext.getContext().put("sTime", startTime);
			ActionContext.getContext().put("eTime", endTime);
			ActionContext.getContext().put("batchNo", batchNo);
			ActionContext.getContext().put("proName", mapPd2.get("proName"));
			ActionContext.getContext().put("Pd2sheet", mapPd2.get("list"));
			ActionContext.getContext().put("TcompleteNum",
					mapPd2.get("completeNum").toString());
			ActionContext.getContext().put("TdisqNum",
					mapPd2.get("disqNum").toString());
			ActionContext.getContext().put("TdisqPercent",
					mapPd2.get("disqPercent").toString());
			return SUCCESS;
		} else{
			ActionContext.getContext().put("message", mapPd2.get("message"));
			return ERROR;
		}
	}

	// 不合格产品员工统计

	
	public String SearchPd3() throws Exception {
		startTime = request.getParameter("startTime");
		endTime = request.getParameter("endTime");

		SearchPd3Service searchPd3Service = new SearchPd3Service();

		mapPd3 = searchPd3Service.SearchPd3(startTime, endTime);

		if (mapPd3.get("result").equals("success")) {

			ActionContext.getContext().put("Pd3sheet", mapPd3.get("disqStaff"));
			ActionContext.getContext().put("total", mapPd3.get("total"));
			ActionContext.getContext().put("disqkind", mapPd3.get("disqkind"));
			ActionContext.getContext().put("sTime", startTime);
			ActionContext.getContext().put("eTime", endTime);
			return SUCCESS;
		} else{
			ActionContext.getContext().put("message", mapPd3.get("message"));
			return ERROR;
		}
	}

	// 不合格产品部门统计
	public String SearchPd4() throws Exception {
		deptNo = request.getParameter("deptNo");
		startTime = request.getParameter("startTime");
		endTime = request.getParameter("endTime");
		System.out.println(deptNo);

		SearchPd4Service searchPd4Service = new SearchPd4Service();

		mapPd4 = searchPd4Service.SearchPd4(deptNo, startTime, endTime);

		if (mapPd4.get("result").equals("success")) {

			ActionContext.getContext().put("Pd4sheet", mapPd4.get("list"));
			ActionContext.getContext().put("deptName", mapPd4.get("deptName"));
			ActionContext.getContext().put("sTime", startTime);
			ActionContext.getContext().put("eTime", endTime);
			ActionContext.getContext().put("TcompleteNum",
					mapPd4.get("completeNum").toString());
			ActionContext.getContext().put("TdisqNum",
					mapPd4.get("disqNum").toString());
			ActionContext.getContext().put("TdisqPercent",
					mapPd4.get("disqPercent").toString());
			return SUCCESS;
		} else{
			ActionContext.getContext().put("message", mapPd4.get("message"));
			return ERROR;
		}
	}

	// 不合格产生产线门统计

	public String SearchPd5() throws Exception {
		lineNo = request.getParameter("lineNo");
		startTime = request.getParameter("startTime");
		endTime = request.getParameter("endTime");
		System.out.println(lineNo);

		SearchPd5Service searchPd5Service = new SearchPd5Service();

		mapPd5 = searchPd5Service.SearchPd5(lineNo, startTime, endTime);

		if (mapPd5.get("result").equals("success")) {

			ActionContext.getContext().put("Pd5sheet", mapPd5.get("list"));
			ActionContext.getContext().put("lineDesc", mapPd5.get("lineDesc"));
			ActionContext.getContext().put("sTime", startTime);
			ActionContext.getContext().put("eTime", endTime);
			ActionContext.getContext().put("lineNo", lineNo);
			ActionContext.getContext().put("TcompleteNum",
					mapPd5.get("completeNum").toString());
			ActionContext.getContext().put("TdisqNum",
					mapPd5.get("disqNum").toString());
			ActionContext.getContext().put("TdisqPercent",
					mapPd5.get("disqPercent").toString());
			return SUCCESS;
		} else{
			ActionContext.getContext().put("message", mapPd5.get("message"));
			return ERROR;
		}
	}

	public void setStahNo(String staNo) {
		this.staNo = staNo;
	}

	public String getStaNo() {
		return staNo;
	}

	public String getLevel() {
		User user = (User)session.getAttribute("user");
		switch(user.getLevel()){
		case 1:
			level = "viewer";
			break;
		case 2:
			level = "operator";
			break;
		case 3:
			level = "admin";
			break;
		}
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	

}
