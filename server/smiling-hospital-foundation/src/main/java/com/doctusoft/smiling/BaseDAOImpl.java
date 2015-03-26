package com.doctusoft.smiling;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Result;

public abstract class BaseDAOImpl<T extends BaseEntity> implements BaseDAO<T> {

	protected Class<T> entityClass;

	public BaseDAOImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
		ObjectifyService.factory().register(entityClass);
	}

	@Override
	public Result<Key<T>> save(T entity) {
		return ofy().save().entity(entity);
	}
	
	@Override
	public Result<Void> delete(T entity) {
		return ofy().delete().entity(entity);
	}

	@Override
	public T get(String id) {
		return ofy().load().type(entityClass).id(id).now();
	}

	@Override
	public List<T> getAll() {
		return ofy().load().type(entityClass).list();
	}
}
