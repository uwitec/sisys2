package com.sisys.action;

import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.sisys.action.BaseAction;
import com.sisys.service.ManageDataService;

public class ManageDataAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;	//表格标题
	private String content;		//表格内容
	
	private File myFile;  // 上传的文件   
    private String myFileContentType;    // 上传文件的类型  
    private String myFileFileName;   // 上传文件的文件名
	
	private ManageDataService manageDataService = new ManageDataService();
	
	//表格导出为excel文件
	public String tableExport() throws Exception{
		request.setCharacterEncoding("GBK");
		title = request.getParameter("title");
		content = request.getParameter("content");
		
		if(content.isEmpty()){
			return ERROR;
		}
		
		response.setContentType("application/ms-excel");
		response.addHeader("Content-Disposition", "attachment; filename=" + new String( title.getBytes("gb2312"), "ISO8859-1" ) + ".xls");
		OutputStream os = response.getOutputStream();
		manageDataService.tableExport(os, title, content);
		System.out.println("end");
		return null;
	}
	
	//数据库导出为sql文件
	public String dbExport() throws Exception{
		Calendar now = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	    String fileName = "backup--" + format.format(now.getTime());
		response.setContentType("text/x-sql");
		response.addHeader("Content-Disposition", "attachment; filename=" + fileName + ".sql");
		
		OutputStream os = response.getOutputStream();
		manageDataService.dbEport(os);
		
		return null;
	}
	
	//导入sql文件到数据库
	public String dbImport(){
		if(!myFile.exists()){
			return ERROR;
		}
		manageDataService.dbImport(myFile);
		return SUCCESS;
	}
	
	//员工表导入
	public String staffImport() throws Exception{
		if(!myFile.exists()){
			return ERROR;
		}
		String result = manageDataService.staffImport(myFile);
		
		return result;
	}
	
	//生产线编码表导入
	public String proLineImport() throws Exception{
		if(!myFile.exists()){
			return ERROR;
		}
		String result = manageDataService.proLineImport(myFile);
		
		return result;
	}
	
	//产品成本表导入
	public String proImport() throws Exception{
		if(!myFile.exists()){
			return ERROR;
		}
		String result = manageDataService.proImport(myFile);
		
		return result;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}
	
	
}