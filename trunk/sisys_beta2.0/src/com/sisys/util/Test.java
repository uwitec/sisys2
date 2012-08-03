package com.sisys.util;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public void test() {
		OP op = OP.BETWEEN;
		Link link = Link.WHERE;
		Object[] value = {3,10};
		Element element = new Element(link, op, "id", value);
		List<Element> elements = new ArrayList<Element>();
		elements.add(element);
		CreateSqlUtil csu = new CreateSqlUtil();
		System.out.print(CreateSqlUtil.createQuerySql(elements));
	}
	public static void main(String args[]) {
		Test t = new Test();
		t.test();
	}
}
