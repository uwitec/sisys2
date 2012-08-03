package com.sisys.dao;


import java.io.Serializable;
import java.util.List;

public interface DaoTemplate<T,PK extends Serializable> {
	public int create(T entity);
	public int delete(T entity);
	public List<T> read(T entity);
	public List<T> readByPk(PK pk);
	public int count();

}
