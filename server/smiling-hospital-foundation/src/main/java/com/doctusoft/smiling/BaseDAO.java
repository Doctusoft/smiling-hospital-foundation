package com.doctusoft.smiling;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;

public interface BaseDAO<T extends BaseEntity> {

	Result<Key<T>> save(T entity);
	
	void delete(T entity);

	T get(String id);

	List<T> getAll();

}
