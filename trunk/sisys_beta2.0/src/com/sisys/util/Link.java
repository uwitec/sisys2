package com.sisys.util;


/**
 * 连接符号
 * @author huangxin
 *
 * 2012-07-09
 * 
 * Link
 */
public enum Link {
	WHERE("where"),
	AND("and"),
	OR("or"),
	IN("in"),
	ORDER("order");
	
	private String value;

	private Link(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
