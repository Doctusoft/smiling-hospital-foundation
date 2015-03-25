package com.doctusoft.smiling;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.ObjectifyService;

public abstract class BaseDAOImpl<T extends BaseEntity> implements BaseDAO<T> {

	protected Class<T> entityClass;

	public BaseDAOImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
		ObjectifyService.factory().register(entityClass);
	}

	@Override
	public void save(T entity) {
		ofy().save().entity(entity);
	}
	
	@Override
	public void delete(T entity) {
		ofy().delete().entity(entity);
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
