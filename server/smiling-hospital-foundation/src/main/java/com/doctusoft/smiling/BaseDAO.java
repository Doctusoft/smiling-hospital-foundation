package com.doctusoft.smiling;

import java.util.List;

public interface BaseDAO<T extends BaseEntity> {

	void save(T entity);
	
	void delete(T entity);

	T get(String id);

	List<T> getAll();

}
