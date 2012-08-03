package com.sisys.bean.mapping;

import java.sql.ResultSet;
import java.text.ParseException;

public abstract class BasicMapping<E> {

	public abstract E mapping(ResultSet rs) throws ParseException;
}
