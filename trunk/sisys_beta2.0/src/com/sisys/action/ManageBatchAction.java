package com.sisys.action;


import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.sisys.bean.Batch;
import com.sisys.bean.Flowpath;
import com.sisys.bean.Product;
import com.sisys.bean.User;
import com.sisys.service.ManageBatchService;

public class ManageBatchAction extends BaseAction {
	
	private String level;  //用户权限
	private ManageBatchService mbs = new ManageBatchService();
	ActionContext context = ActionContext.getContext();  
    HttpServletRequest request = (HttpServletRequest) context.get(StrutsStatics.HTTP_REQUEST);  
	
	//批次和产品
	private String proNo = request.getParameter("proNo");
	private Batch batch = new Batch();
	private Product product = new Product();
	private Flowpath flowpath = new Flowpath();
	private String fp = "";
	//相应ajax请求
	private InputStream inputStream;
	private String fpath;
	
	//对应的get()和set()方法
	public Batch getBatch() {
		return batch;
	}
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setFlowpath(Flowpath flowpath) {
		this.flowpath = flowpath;
	}
	public Flowpath getFlowpath() {
		return flowpath;
	}
	public void setFp(String fp) {
		this.fp = fp;
	}
	public String getFp() {
		return fp;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	public String getProNo() {
		return proNo;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setFpath(String fpath) {
		this.fpath = fpath;
	}
	public String getFpath() {
		return fpath;
	}
	
	@Override
	public String execute() {
		return null;
	}
	
	//进入批次添加页面
	public String preAddBatch() throws IOException {
		product.setProNo(proNo);
		String result = mbs.preAddBatch(product);
		System.out.println("result"+result);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
		return null; 
	}
	
	//批次添加
	public String addBatch() throws IOException {
		return mbs.addBatch(product, batch, fpath);
	}
	
	//超期批次的修改
	public String modifyOutDue() {
		return mbs.modifyOutDue(product, batch);
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
	
	//进入超期批次列表
	public String outOfDueList() {
		return mbs.outOfDueList();
	}

}
