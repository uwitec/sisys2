package com.sisys.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.sisys.bean.Batch;
import com.sisys.bean.Department;
import com.sisys.bean.Flowpath;
import com.sisys.bean.OutDueBatchCopy;
import com.sisys.bean.ProHash;
import com.sisys.bean.Processes;
import com.sisys.bean.Product;
import com.sisys.bean.ScheduleTab;
import com.sisys.bean.User;
import com.sisys.bean.mapping.ProHashMapping;
import com.sisys.dao.BatchDAO;
import com.sisys.dao.DepartmentDAO;
import com.sisys.dao.FlowpathDAO;
import com.sisys.dao.ProHashDAO;
import com.sisys.dao.ProcessesDAO;
import com.sisys.dao.ProductDAO;
import com.sisys.dao.ScheduleTabDAO;

public class ManageBatchService {

	ActionContext actionContext = ActionContext.getContext();
	Map session = actionContext.getSession();
	HttpServletRequest request = (HttpServletRequest) actionContext
			.get(StrutsStatics.HTTP_REQUEST);

	// 进入批次添加页面
	public String preAddBatch(Product product) {
		// 根据产品编号查询对应产品ID
		User user = (User) session.get("user");
		ProductDAO pdao = new ProductDAO();
		Map<String, Object> equalsMap = new HashMap<String, Object>();
		equalsMap.put("proNo", product.getProNo());
		List<Product> pList = pdao.findEntity(equalsMap);
		List<Department> list = new ArrayList<Department>();
		//若产品不存在，返回none 
		if(pList.size() == 0) { 
			return "pnone";
		}
		for(int i=0; i<pList.size(); i++) {
			int deptId = pList.get(i).getDeptId();
			equalsMap.clear();
			equalsMap.put("deptId", deptId);
			DepartmentDAO dDao = new DepartmentDAO();
			list = dDao.readByPk(deptId);
			
		}
		product = pList.get(0);
		
		FlowpathDAO fdao = new FlowpathDAO();
		equalsMap.clear();
		equalsMap.put("proId", product.getId());
		List<Flowpath> fList = fdao.findEntity(equalsMap);
		/*
		 * //若对应流程不存在，返回none if(fList.size() == 0) { return "fnone"; }
		 */
		StringBuffer fp = null;
		Flowpath flowpath = null;
		StringBuffer result = new StringBuffer();
		for (int j = 0; j < fList.size(); j++) {
			if (j != fList.size() - 1) {
				result.append("<input type=\"radio\" name=\"fpath\" value=\"");
			} else {
				result.append("<input type=\"radio\" name=\"fpath\" checked value=\"");
			}

			flowpath = fList.get(j);
			String process = flowpath.getSequence();
			String[] processes = process.split("-");
			fp = new StringBuffer();
			// 得到对应的工序名称列表字符串，以“-”连接
			ProcessesDAO procdao;
			for (int i = 0; i < processes.length; i++) {
				int procId = Integer.parseInt(processes[i]);
				equalsMap.clear();
				equalsMap.put("Id", procId);
				procdao = new ProcessesDAO();
				List<Processes> procList = procdao.findEntity(equalsMap);
				/*
				 * if(procList.size() == 0) { return "fnone"; }
				 */
				fp.append(procList.get(0).getProcName());
				if (i != processes.length - 1) {
					fp.append("-");
				}
			}
			result.append(process);
			result.append("\">");
			result.append(fp);
			if (j != fList.size()) {
				result.append("<br />");
			}

		}
		// 自动生成批次号
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		DecimalFormat df = new DecimalFormat("0000");
		StringBuffer sb = new StringBuffer(sdf.format(date));
		ProHashMapping phMapping = new ProHashMapping();
		ProHashDAO pDao = new ProHashDAO(ProHash.class, phMapping);
		String sql = "select * from prohash where proNo='" + product.getProNo() + "'";
		List<ProHash> pList1 = pDao.findEntityByList(sql);
		if (!pList1.isEmpty() && pList1.size() != 0) {
			ProHash proh = pList1.get(0);
			if (proh.getOwn() == 9999) {
				result.append(";该产品批次已满！");
			} else {
				if (proh.getDate().equals(sdf.format(date))) {
					result.append(";" + sb.toString() + df.format(proh.getOwn() + 1));
				} else {
					result.append(";" + sb.toString() + "0001");
				}
			}
		}
		result.append(";");
		System.out.println(user);
		switch(user.getLevel()) {
		case 2:
			result.append(user.getDeptName() + ";1-");
			break;
		case 3:
			for(int i=0; i<list.size(); i++) {
				Department dept = list.get(i);
				if(dept != null) {
					result.append(list.get(i).getDeptName() + "-");
				}
				
			}
			break;
		}
		
		result.delete(result.length()-1, result.length());
		return result.toString();
	}

	// 批次添加
	public String addBatch(Product product, Batch batch, String deptId, String fpath)
			throws IOException {
		User user = (User) session.get("user");
		// User user = new User();
		System.out.println(product);
		System.out.println(batch);
		System.out.println(fpath);
		// 判断输入是否完整
		StringBuffer code = new StringBuffer("");
		if ("".equals(batch.getBatchNo()) || "".equals(batch.getTotalNum())
				|| "".equals(product.getProNo())) {
			return "isnull";
		}
		Department dept;
		DepartmentDAO dDao = new DepartmentDAO();
		Map<String, Object> equalsMap = new HashMap<String, Object>();
		equalsMap.put("deptName", deptId);
		List<Department> dList = dDao.findEntity(equalsMap);
		if(dList != null) {
			dept = dList.get(0);
		} else {
			return "pnone";
		}
		// 判断产品是否存在
		ProductDAO pdao = new ProductDAO();
		equalsMap.clear();
		equalsMap.put("proNo", product.getProNo());
		equalsMap.put("deptId", dept.getId());
		List<Product> pList = pdao.findEntity(equalsMap);
		Product p;
		// 若产品不存在，返回none
		if (pList.size() == 0) {
			return "pnone";
		} else {
			p = pList.get(0);
		}

		// 判断批次是否已经存在
		BatchDAO bdao = new BatchDAO();
		equalsMap.clear();
		equalsMap.put("batchNo", batch.getBatchNo());
		equalsMap.put("proId", String.valueOf(p.getId()));
		List<Batch> blist = bdao.findEntity(equalsMap);
		if (blist.size() != 0) {
			return "repetition";
		}
		// 批次不存在，新建批次
		Calendar startTime = Calendar.getInstance();
		Date nowTime = new Date();// 这个是现在的时间，判断scgd是否生成pdf时会用到
		String batchNo = batch.getBatchNo();
		String num = batchNo.substring(8, batchNo.length());
		//batch.setTotalNum(batch.getTotalNum() + Integer.parseInt(num));
		batch.setIsDelete(0);
		batch.setDeleteTime(null);
		batch.setProId(p.getId());
		batch.setStartTime(startTime.getTime());
		startTime.add(Calendar.DATE, p.getProCycle());
		batch.setEndTime(startTime.getTime());

		// 调用跟单生成程序SCGD
		/*** 这一段先注释掉，等黄欣来补完吧 ***/

		String pn = p.getProName().replace("#", "\\#");
		String content = p.getProNo() + "\r\n" + pn + "\r\n"
				+ batch.getBatchNo() + "\r\n" + batch.getTotalNum() + "\r\n"
				+ user.getUsername();
		System.out.println(content);

		// inputPath和outputPath是我所存的输入和输入文件的绝对地址
		// String inputPath =
		// "E:/Program Files/workspace/sisys_beta2.0/input.tex";
		String path = "C:\\Java\\Tomcat 6.0\\webapps\\sisys_beta2.0";
		String inputPath = "e:\\" + batchNo + p.getProNo() +".tex";
		String outputPath = path + "\\PDFs\\" + p.getProNo() + "\\"
				+ batch.getBatchNo() + ".pdf";
		System.out.println(inputPath);
		System.out.println(outputPath);
		File proFile = new File(outputPath);
		if (!proFile.getParentFile().exists()) {
			proFile.getParentFile().mkdirs();
		}
		createPDF(content, inputPath, outputPath);

		// 读取所生成的pdf
		File f2 = new File(outputPath);
		Date date = new Date(f2.lastModified());// 查看pdf的修改时间
		// 如果所生成的pdf不存在或者修改时间在进入该程序之前，即scgd生成pdf失败，则返回生成失败
		if (!f2.exists() || date.before(nowTime)) {
			return "false";
		}

		request.setAttribute("pro", p.getProNo());
		request.setAttribute("bat", batch.getBatchNo());
		
		// 找到对应的流程
		FlowpathDAO fdao = new FlowpathDAO();
		// Map<String, String> equalsMap = new HashMap<String, String>();
		equalsMap.clear();
		equalsMap.put("sequence", fpath);
		List<Flowpath> fList = fdao.findEntity(equalsMap);
		batch.setFlowId(fList.get(0).getId());
		String[] processes = fpath.split("-");

		BatchDAO bdao1 = new BatchDAO();
		batch.setStatus(0);
		bdao1 = new BatchDAO();
		int num1 = bdao1.create(batch);
		if (num1 == 0) {
			return "false";
		}

		// 在工作表建立初始化的相应记录
		ScheduleTabDAO stdao = new ScheduleTabDAO();
		ScheduleTab sch = new ScheduleTab();
		sch.setProcId(Integer.parseInt(processes[0]));
		int batchId = 0;
		bdao1 = new BatchDAO();
		equalsMap.clear();
		equalsMap.put("batchNo", batch.getBatchNo());
		equalsMap.put("proId", String.valueOf(p.getId()));
		List<Batch> blist1 = bdao1.findEntity(equalsMap);
		if (blist1.size() != 0) {
			batchId = blist1.get(0).getId();
			sch.setBatchId(batchId);
		}
		ProcessesDAO pDao = new ProcessesDAO();
		equalsMap.clear();
		equalsMap.put("id", processes[0]);
		List<Processes> plist = pDao.findEntity(equalsMap);
		if (plist.size() != 0) {
			sch.setColorNo(plist.get(0).getColorNo());
		}

		num1 = stdao.create(sch);
		if (num1 == 0) {
			return "false";
		}
		for (int i = 1; i < processes.length; i++) {
			ScheduleTabDAO stdao1 = new ScheduleTabDAO();
			ScheduleTab sch1 = new ScheduleTab();
			sch1.setProcId(Integer.parseInt(processes[i]));
			sch1.setBatchId(batchId);
			ProcessesDAO pDao1 = new ProcessesDAO();
			equalsMap.clear();
			equalsMap.put("id", processes[i]);
			List<Processes> plist1 = pDao1.findEntity(equalsMap);
			if (plist1.size() != 0) {
				sch1.setColorNo(plist1.get(0).getColorNo());
			}
			if (i != processes.length - 1) {
				sch1.setIsEnd(0);
			} else {
				sch1.setIsEnd(1);
			}
			num1 = stdao1.create(sch1);
			if (num1 == 0) {
				return "false";
			}
		}
		// 更新proHash表中相应记录
		String sql = "select * from proHash where proNo='" + product.getProNo() + "'";
		ProHashMapping phMapping = new ProHashMapping();
		ProHashDAO phDao = new ProHashDAO(ProHash.class, phMapping);
		List<ProHash> phlist = phDao.findEntityByList(sql);
		phlist.get(0).setOwn(Integer.parseInt(num));
		if (num.equals("0001")) {
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String da = sdf.format(d);
			phlist.get(0).setDate(da);
		}
		phDao = new ProHashDAO(ProHash.class, phMapping);
		phDao.update(phlist.get(0), 1);

		// 生成条码字符串（以后要删除或注释）
		
/*		code.append(batch.getBatchNo());
		code.append(product.getProNo());
		if (code.toString().length() % 2 == 1) {
			code.append("01");
		} else {
			code.append("0");
		}
		char[] str = code.toString().toCharArray();
		int oddNum = 0;
		int evenNum = 0;
		int tnum;
		for (int i = 0; i < str.length - 1; i += 2) {
			oddNum += Integer.parseInt(str[i] + "");
		}
		for (int i = 1; i < str.length - 1; i += 2) {
			evenNum += Integer.parseInt(str[i] + "");
		}
		tnum = oddNum * 3 + evenNum;
		code.append("" + (10 - tnum % 10) % 10);*/

		request.setAttribute("code", code.toString());

		// 记录管理员操作信息
		// LogInfo logInfo = new LogInfo();
		/*
		 * String content = "管理员" + user.getUsername() + "新建批次。产品名称：" +
		 * p.getProName() + ",产品编号：" + product.getProNo() + ";批次号：" +
		 * batch.getBatchNo();
		 */
		// logInfo.saveLog(user, content, System.currentTimeMillis());


		return "success";
	}

	// 超期批次的修改
	public String modifyOutDue(Product product, Batch batch) {
		// 若输入值为空，返回empty
		if (product.getProNo() == "" || batch.getBatchNo() == "") {
			return "empty";
		}
		// 根据产品编号查询对应产品ID
		ProductDAO pdao = new ProductDAO();
		Map<String, Object> equalsMap = new HashMap<String, Object>();
		equalsMap.put("proNo", product.getProNo());
		equalsMap.put("isDelete", 0);
		List<Product> pList = pdao.findEntity(equalsMap);
		// 若产品不存在，返回none
		if (pList.size() == 0) {
			return "none";
		}
		product.setId(pList.get(0).getId());
		batch.setProId(product.getId());
		// 根据批次号和产品Id查询对应批次
		BatchDAO bdao = new BatchDAO();
		equalsMap.clear();
		equalsMap.put("batchNo", batch.getBatchNo());
		equalsMap.put("proId", batch.getProId());
		equalsMap.put("status", 2);
		equalsMap.put("isDelete", 0);
		List<Batch> bList = bdao.findEntity(equalsMap);
		// 若该批次不存在，返回none
		if (bList.size() == 0) {
			return "none";
		}
		Batch b = bList.get(0);
		b.setNote(batch.getNote());
		b.setStatus(3);

		// 若修改成功则返回success,若修改失败则返回false
		BatchDAO bdao1 = new BatchDAO();
		int num = bdao1.update(b, b.getId());
		if (num == 1) {

			// 记录管理员操作信息
			ActionContext actionContext = ActionContext.getContext();
			Map session = actionContext.getSession();
			// LogInfo logInfo = new LogInfo();
			User user = (User) session.get("user");
			String content = "管理员" + user.getUsername() + "修改超期批次。产品名称："
					+ product.getProName() + ",产品编号：" + product.getProNo()
					+ ";批次号：" + batch.getBatchNo() + ";备注：" + batch.getNote();
			// logInfo.saveLog(user, content, System.currentTimeMillis());

			return "success";
		} else {
			return "false";
		}
	}

	// 进入超期批次列表

	public String outOfDueList() {
		List<OutDueBatchCopy> outOfDueList = new ArrayList<OutDueBatchCopy>();
		String result = ""; // 得到开始时间和截止时间 String
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String start = null;
		String end = null;
		try {
			start = sdf.format(sdf.parse(startTime));
			end = sdf.format(sdf.parse(endTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(start);
		System.out.println(end);
		// 包含超期未完成、超期已完成、超期已处理三种类型
		String sql1 = "select * from batch where startTime<'" + end
				+ "' and endTime>'" + start + "' and endTime <'" + end
				+ "' and status=2 or status=3 or status=4";
		BatchDAO bdao = new BatchDAO();
		List<Batch> blist = bdao.findEntityByList(sql1);
		if (blist.size() != 0) {
			for (int i = 0; i < blist.size(); i++) {
				Batch batch = blist.get(i);
				// System.out.println(batch);
				String sql2 = "select * from product where id="
						+ batch.getProId();
				ProductDAO pdao = new ProductDAO();
				List<Product> plist = pdao.findEntityByList(sql2);
				if (plist.size() != 0) {
					Product product = plist.get(0);
					OutDueBatchCopy outDue = new OutDueBatchCopy();
					outDue.setBatchNo(batch.getBatchNo());
					outDue.setId(batch.getId());
					if (batch.getStatus() == 2) { // 超期未完成
						outDue.setIsHandle(0);
						outDue.setIsOver(0);
					} else if (batch.getStatus() == 3) { // 超期已处理
						outDue.setIsHandle(1);
						outDue.setIsOver(0);
					} else if (batch.getStatus() == 4) { // 超期已完成
						outDue.setIsHandle(0);
						outDue.setIsOver(1);
					}
					outDue.setNote(batch.getNote());
					outDue.setProName(product.getProName());
					outOfDueList.add(outDue);
				}
			}
		}
		// System.out.println(outOfDueList);
		request.setAttribute("outOfDueList", outOfDueList);
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

	public void createPDF(String content, String inputPath, String outputPath) {

		try {
			File file = new File(inputPath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			osw.write(content);
			osw.flush();
			if (osw != null) {
				osw.close();
				osw = null;
				System.out.println("4");
			}
			if (fos != null) {
				fos.close();
				fos = null;
				System.out.println("5");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// String command = "cmd.exe /k start scgd "+inputPath+" "+ outputPath;
		String command = "cmd /c scgd \"" + inputPath + "\" \"" + outputPath
				+ "\"";
		System.out.println(command);
		Runtime rt = Runtime.getRuntime();
		try {
			String ls_1;
			Process process = rt.exec(command);
			BufferedReader bufferReader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			while ((ls_1 = bufferReader.readLine()) != null) {
				System.out.println(ls_1);
			}
			process.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File file = new File(inputPath);
		if (file.exists()) {
			file.delete();
		}

	}
}
