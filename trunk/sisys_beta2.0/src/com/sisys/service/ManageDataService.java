package com.sisys.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import com.sisys.bean.Department;
import com.sisys.bean.Flowpath;
import com.sisys.bean.Processes;
import com.sisys.bean.Product;
import com.sisys.bean.ProductLine;
import com.sisys.bean.Staff;
import com.sisys.bean.StaffKind;
import com.sisys.dao.DepartmentDAO;
import com.sisys.dao.FlowpathDAO;
import com.sisys.dao.ProcessesDAO;
import com.sisys.dao.ProductDAO;
import com.sisys.dao.ProductLineDAO;
import com.sisys.dao.StaffDAO;
import com.sisys.dao.StaffKindDAO;
import com.sisys.service.ManageDataService;
import com.sisys.service.TD;
import com.sisys.util.ColorUtil;
import com.sisys.util.MysqlConfig;

public class ManageDataService {
	private int cols;
	private WritableFont font1;
	private WritableFont font2;
	private WritableCellFormat format1;
	private WritableCellFormat format2;
	private final int secondRow = 28;
	
	//表格导出为excel文件
	public void tableExport(OutputStream os,String title,String content) throws Exception{
		System.out.println(title);
		System.out.println(content);
		WritableWorkbook book = Workbook.createWorkbook(os);
		book.setProtected(true);
		init();
		WritableSheet sheet = book.createSheet("sheet1", 0);
		int row = 0;
		int col = 0;
		Label label = null;
		
		
		
		List<TD> listTD = getTDContent(content);
		System.out.println("gettdcontent");
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		
		sheet.mergeCells(0, 0, cols-1, 1);
		label = new Label(col,row,title.substring(0, title.indexOf("表")+1),format1);
		sheet.addCell(label);
		row += 2;
		
		for (TD td : listTD) {
			System.out.println(td);
			if (td == null) { 
				row++; 
				col = 0; 
				continue; 
			} 

			while (map.get(col + "-" + row) != null) { 
				col++; 
			} 

			if (td.colspan > 1 || td.rowspan > 1) { 
				sheet.mergeCells(col, row, col + td.colspan - 1, row + td.rowspan - 1); 
				for (int i = col; i <= col + td.colspan - 1; i++) { 
					for (int j = row; j <= row + td.rowspan - 1; j++) { 
						map.put(i + "-" + j, true); 
					} 
				} 
			} 
			WritableFont newfont2 = font2;
			WritableCellFormat newformat2 = new WritableCellFormat(newfont2);
			if(td.fontcolor != null){
				newfont2 = new WritableFont(WritableFont.createFont("楷体 _GB2312"),10,WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE,ColorUtil.getColour(td.fontcolor)); 
				newformat2=new WritableCellFormat(newfont2);
			}
			if(td.bgcolor != null){
				newformat2.setBackground(ColorUtil.getColour(td.bgcolor));
			}
			newformat2.setAlignment(jxl.format.Alignment.CENTRE);
			newformat2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			newformat2.setWrap(true);

			label = new Label(col, row, td.content,newformat2); 
			sheet.addCell(label); 

			map.put(col + "-" + row, true); 
			col += td.colspan; 
		}
		
		book.write();
		book.close();
	}
	
	//数据库导出为sql文件
	public void dbEport(OutputStream os){
		MysqlConfig mc = new MysqlConfig();
		try{
			Runtime rt = Runtime.getRuntime();
			Process child = rt.exec(mc.getPath() + "mysqldump -u" + mc.getUser() + " -p" + mc.getPassword() + " sisys2");
			
			InputStream in = child.getInputStream();
			InputStreamReader isr = new InputStreamReader(in,"utf8");
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			BufferedReader br = new BufferedReader(isr);
			while ((inStr = br.readLine()) != null){
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			
			OutputStreamWriter writer = new OutputStreamWriter(os, "utf8");
			writer.write(outStr);
			
			writer.flush();
			
			in.close();
			isr.close();
			br.close();
			writer.close();
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//导入sql文件到数据库
	public void dbImport(File myFile){
		MysqlConfig mc = new MysqlConfig();
		try {
			Runtime rt = Runtime.getRuntime();
			Process child = rt.exec(mc.getPath() + "mysql -u" + mc.getUser() + " -p" + mc.getPassword() + " sisys2");
			OutputStream out = child.getOutputStream();
			
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(myFile), "utf8"));
			while((inStr = br.readLine()) != null){
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
			writer.write(outStr);
			writer.flush();
			out.close();
			br.close();
			writer.close();
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//员工表导入
	public String staffImport(File myFile) throws Exception {
		Workbook book = Workbook.getWorkbook(new FileInputStream(myFile));
		Sheet sheet = book.getSheet(0);
		String title = sheet.getCell(0, 0).getContents();
		if(!title.replaceAll(" ", "").equals("制造部员工表")){
			return "dataError";
		}
		
		int flag = 0;
		int row = 1;
		String deptName;
		int deptNo = 1;
		Cell[] cells;
		Department department = null;
		DepartmentDAO departmentDAO;
		Staff staff;
		StaffDAO staffDAO;
		Set<String> kindSet = new HashSet<String>();
		List<Department> depList;
		List<Staff> staList;
		List<StaffKind> kindList;
		StaffKind staffKind;
		StaffKindDAO staffKindDAO;
		
		while(row < sheet.getRows()){
			Map<String,String> equalsMap = new HashMap<String,String>();
			if(sheet.getCell(1, row).getContents().isEmpty()){
				deptName = sheet.getCell(0, row).getContents();
				equalsMap.put("deptName", deptName);
				departmentDAO = new DepartmentDAO();
				depList = departmentDAO.findEntity(equalsMap);
				if(depList.size() == 0){
					department = new Department();
					department.setDeptName(deptName);
					department.setDeptNo(String.valueOf(deptNo));
					System.out.println(department.getDeptNo());
					int r = new DepartmentDAO().create(department);
					if(r <= 0){
						return "error";
					}
				}
				departmentDAO = new DepartmentDAO();
				department = departmentDAO.findEntity(equalsMap).get(0);
				deptNo++;
				row++;
			}else{
				cells = sheet.getRow(row);
				equalsMap.put("staNo", cells[1].getContents());
				staffDAO = new StaffDAO();
				staList = staffDAO.findEntity(equalsMap);
				if(staList.size() != 0){
					System.out.println(staList.size());
					staff = staList.get(0);
				}else{
					staff = new Staff();
					staff.setStaName(cells[0].getContents());
					staff.setStaNo(cells[1].getContents());
					staff.setDeptId(department.getId());
					staff.setKind(cells[4].getContents());
					
					System.out.println(staff.getStaName());
					int r = new StaffDAO().create(staff);
					if(r <= 0){
						return "error";
					}
					flag = 1;
				}
				kindSet.add(cells[4].getContents());
				System.out.println(cells[0].getContents());
			}
			row++;
		}
		
		for(String kind : kindSet){
			Map<String,String> equalsMap = new HashMap<String,String>();
			equalsMap.put("kindDesc", kind);
			staffKindDAO = new StaffKindDAO();
			kindList = staffKindDAO.findEntity(equalsMap);
			if(kindList.size() == 0){
				staffKind = new StaffKind();
				staffKind.setKindDesc(kind);
				int r = new StaffKindDAO().create(staffKind);
				if(r <= 0){
					return "error";
				}
			}
		}
		if(flag == 0){
			return "dataSame";
		}
		return "success";
		
	}
	
	//生产线编码表导入
	public String proLineImport(File myFile) throws Exception{
		Workbook book = Workbook.getWorkbook(new FileInputStream(myFile));
		Sheet sheet = book.getSheet(0);
		String title = sheet.getCell(0, 0).getContents();
		if(!title.replaceAll(" ", "").equals("制造部及生产线编码表")){
			return "dataError";
		}
		ProductLine proLine;
		ProductLineDAO proLineDAO;
		int flag = 0;
		int row = 2;
		Cell[] cells;
		List<ProductLine> proLineList;
		System.out.println("test");
		while(row < sheet.getRows()){
			if(!sheet.getCell(0, row).getContents().isEmpty()){
				Map<String,String> equalsMap = new HashMap<String,String>();
				cells = sheet.getRow(row);
				equalsMap.put("lineNo", cells[0].getContents());
				proLineDAO = new ProductLineDAO();
				proLineList = proLineDAO.findEntity(equalsMap);
				if(proLineList.size() == 0){
					proLine = new ProductLine();
					proLine.setLineNo(cells[0].getContents());
					proLine.setLineDesc(cells[1].getContents());
					System.out.println(proLine.getLineDesc());
					int r = new ProductLineDAO().create(proLine);
					if(r <= 0){
						return "error";
					}
					flag = 1;
				}
			}
			row++;
		}
		if(flag == 0){
			return "dataSame";
		}
		
		return "success";
	}
	
	//产品成本表导入
	public String proImport(File myFile) throws Exception {
		Workbook book = Workbook.getWorkbook(new FileInputStream(myFile));
		Sheet sheet = book.getSheet(0);
		String title = sheet.getCell(0, 0).getContents();
		if(!title.replaceAll(" ", "").equals("产品制造成本表")){
			return "dataError";
		}
		
		Product product = new Product();
		ProductDAO productDAO;
		Flowpath flowpath = new Flowpath();
		FlowpathDAO flowpathDAO = new FlowpathDAO();
		Processes process;
		ProcessesDAO processesDAO;
		DepartmentDAO departmentDAO;
		List<Department> deptList;
		ProductLineDAO productLineDAO;
		List<ProductLine> proLineList;
		List<Integer> sequenceList = new ArrayList<Integer>();
		int row = 2;
		Cell[] cells;
		List<Product> proList;
		List<Processes> processList;
		cells = sheet.getRow(row);
		
		Map<String,String> equalsMap = new HashMap<String,String>();
		
		//查询产品编号是否存在
		String proNo = cells[11].getContents();
		equalsMap.clear();
		equalsMap.put("proNo", proNo);
		productDAO = new ProductDAO();
		proList = productDAO.findEntity(equalsMap);
		if(proList.size() != 0){
			return "error";
		}
		
		//查询生产线编号是否存在
		String lineNo = "";
		Cell[] lineCell = sheet.getColumn(21);
		for(int i = secondRow + 1;i < lineCell.length;i++){
			if(!lineCell[i].getContents().trim().equals("")){
				lineNo = lineCell[i].getContents().trim();
				lineNo = lineNo.substring(0,1) + "0" + lineNo.substring(lineNo.length()-1, lineNo.length());
			}
		}		
		
		equalsMap.clear();
		equalsMap.put("lineNo", lineNo);
		System.out.println(lineNo);
		productLineDAO = new ProductLineDAO();
		proLineList = productLineDAO.findEntity(equalsMap);
		if(proLineList.size() == 0){
			return "noproLine";
		}
		
		//部门信息
		String deptNo = lineNo.trim().substring(0,1);
		equalsMap.clear();
		equalsMap.put("deptNo", deptNo);
		departmentDAO = new DepartmentDAO();
		deptList = departmentDAO.findEntity(equalsMap);
		if(deptList.size() ==0){
			return "error";
		}
		
		//生产周期
		int proCycle = 0;
		Cell[] cycleCell = sheet.getColumn(22);
		for(int i = secondRow + 1;i < cycleCell.length;i++){
			if(!(cycleCell[i].getContents().equals(""))){
				proCycle += Integer.parseInt(cycleCell[i].getContents());
			}
		}
		
		//插入产品信息
		product.setDeptId(deptList.get(0).getId());
		product.setProCycle(proCycle);
		product.setProlineId(proLineList.get(0).getId());
		product.setProNo(proNo);
		product.setProName(cells[5].getContents());
		productDAO = new ProductDAO();
		int r = productDAO.create(product);
		if(r < 0){
			return "error";
		}
		equalsMap.clear();
		equalsMap.put("proNo", proNo);
		productDAO = new ProductDAO();
		product = productDAO.findEntity(equalsMap).get(0);
		
		
		//遍历工序信息
		row = secondRow + 1;
		while(sheet.getCell(0, row).getType() == CellType.NUMBER || sheet.getCell(0, row).getType() == CellType.NUMBER_FORMULA){
			cells = sheet.getRow(row);
			String color;
			process = new Processes();
			processesDAO = new ProcessesDAO();
			color = ColorUtil.toHexEncoding(cells[19].getCellFormat().getBackgroundColour());
			process.setColorNo(color);
			process.setProcNo(cells[0].getContents());
			process.setProcName(cells[1].getContents());
			if(cells[3].getType() == CellType.NUMBER || cells[3].getType() == CellType.NUMBER_FORMULA){
				process.setUnitOutput(Integer.parseInt(cells[3].getContents().trim()));
			}else{
				process.setUnitOutput(-1);
			}
			if(cells[13].getType() == CellType.NUMBER || cells[13].getType() == CellType.NUMBER_FORMULA){
				process.setUnitCost(Double.parseDouble(cells[13].getContents().trim()));
			}else{
				process.setUnitCost(0);
			}
			if(cells[15].getType() == CellType.NUMBER || cells[15].getType() == CellType.NUMBER_FORMULA){
				if(process.getUnitOutput() == -1){
					process.setUnitOutput(500);
				}
				process.setUnitCost(process.getUnitOutput() * Double.parseDouble(cells[15].getContents()) / 8.0);
			}
			r = processesDAO.create(process);
			if(r <= 0){
				return "error";
			}
			equalsMap.clear();
			equalsMap.put("procName", process.getProcName());
			processList = new ProcessesDAO().findEntity(equalsMap);
			if(processList.size() == 0){
				return "error";
			}
			
			//记录流程的工序id
			sequenceList.add(processList.get(processList.size()-1).getId());
			
			row ++;
		}
		
		//插入流程信息
		String sequence;
		StringBuffer sb = new StringBuffer("");
		for(int i = 0;i < sequenceList.size();i++){
			if(!sb.toString().isEmpty()){
				sb.append("-");
			}
			sb.append(sequenceList.get(i));
		}
		sequence = sb.toString();
		flowpath = new Flowpath();
		flowpath.setSequence(sequence);
		flowpath.setProId(product.getId());
		r = flowpathDAO.create(flowpath);
		if(r <= 0){
			return "error";
		}
		
		return "success";
	}
	
	//提取td属性及内容
	private List<TD> getTDContent(String content){
		int begin = -1;
		int end = -1;
		int index = -1;
		String numberStr;
		String colorStr;
		int number;
		int flag = 0;
		List<TD> list = new ArrayList<TD>();
		if(content.indexOf("</TR>") >= 0){
			content = content.replaceAll("TR", "tr");
			content = content.replaceAll("TH", "th");
			content = content.replaceAll("TD", "td");
			content = content.replaceAll("rowSpan", "rowspan");
			content = content.replaceAll("colSpan", "colspan");
		}
		content = content.replaceAll("<th", "<td");
		content = content.replaceAll("</th>", "</td>");
		
		String[] trs = content.split("</tr>");
		for(String tr : trs){
			number = 1;
			String[] ss = tr.split("</td>");
			for(String s : ss){
				System.out.println(s);
				begin = s.indexOf("<td");
				if(begin == -1){
					continue;
				}
				
				s = s.substring(begin + 3);
				index = s.indexOf(">");
				TD td = new TD();
				
				begin = s.indexOf("rowspan=");
				if(begin != -1){
					end = s.indexOf(" ",begin);
					if(end == -1 || end > index){
						end = index;
					}
					numberStr = s.substring(begin + 8, end).replace('\"' , ' ' ).replace('\'' , ' ' ).trim();
					number = Integer.parseInt(numberStr);
					td.rowspan = number;
				}
				
				begin = s.indexOf("colspan=");
				if(begin != -1){
					end = s.indexOf(" ",begin);
					if(end == -1 || end > index){
						end = index;
					}
					numberStr = s.substring(begin + 8, end).replace('\"' , ' ' ).replace('\'' , ' ' ).trim();
					number = Integer.parseInt(numberStr);
					td.colspan = number;
					if(flag == 0){
						cols += number;
					}
				}else{
					if(flag == 0){
						cols++;
					}
				}
				begin = s.indexOf("background-color:");
				if(begin != -1){
					end = s.indexOf(" ", begin);
					if(end == -1 || end > index){
						end = index;
					}
					colorStr = s.substring(begin + 17,end).replace('\"' , ' ' ).replace('\'' , ' ' ).trim();
					td.bgcolor = colorStr;
				}
				begin = s.indexOf("color=");
				if(begin != -1){
					index = s.indexOf(">",begin);
					end = s.indexOf(" ", begin);
					if(end == -1 || end > index){
						end = index;
					}
					colorStr = s.substring(begin + 6,end).replace('\"' , ' ' ).replace('\'' , ' ' ).trim();
					td.fontcolor = colorStr;
				}
				td.content = s.substring(index + 1).replaceAll("<.*?>", "").replaceAll(" ", "").trim();
				if(td.content.equals("&nbsp;")){
					td.content = " ";
				}
				list.add(td);
			}
			list.add(null);
			flag = 1;
			
		}
		
		return list;
	}
	
	//表格格式初始化
	private void init() throws Exception{
		font1= new WritableFont(WritableFont.TIMES,16,WritableFont.BOLD); 
		font2=new WritableFont(WritableFont.createFont("楷体 _GB2312"),10,WritableFont.NO_BOLD );
		format1=new WritableCellFormat(font1);
		format2=new WritableCellFormat(font2);
		format1.setAlignment(jxl.format.Alignment.CENTRE);
		format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		format2.setAlignment(jxl.format.Alignment.CENTRE);
		format2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		format1.setWrap(true);
		format2.setWrap(true);
	}
	
	public static void main(String[] args) throws Exception {
		ManageDataService mds = new ManageDataService();
		mds.proImport(null);
	}
	
}

class TD { 
	int rowspan = 1; 
	int colspan = 1; 
	String fontcolor = null;
	String bgcolor = null;
	String content; 
	
	@Override
	public String toString() {
		return "TD [bgcolor=" + bgcolor + ", colspan=" + colspan + ", content="
				+ content + ", fontcolor=" + fontcolor + ", rowspan=" + rowspan
				+ "]";
	}
}