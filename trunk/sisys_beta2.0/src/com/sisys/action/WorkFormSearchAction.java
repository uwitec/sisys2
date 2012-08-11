package com.sisys.action;

import com.sisys.service.WorkFormSearchService;

@SuppressWarnings("serial")
public class WorkFormSearchAction extends BaseAction {
	
	WorkFormSearchService wfsearch = new WorkFormSearchService();
	
	public String execute() throws Exception {
		return null;
	}
	
	//按批次搜索工单
	public String searchByBatch() {
		return wfsearch.searchByBatch();
	}

	//默认工单列表
	public String Firstpage() {
		return wfsearch.FirstPage();
	}
	
	//查看工单详情
	public String detail() {
		return wfsearch.detail();
		
	}
	
	//进入修改界面
	public String preAlter() {
		return wfsearch.detail();
	}
	
	//条形码输入
	public String codeSearch(){
		return wfsearch.codeAnalysis();
	}

}
