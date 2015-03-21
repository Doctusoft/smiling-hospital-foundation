package com.doctusoft.smiling;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

public abstract class BaseDAOImpl<T extends BaseEntity> implements BaseDAO<T> {

	private Class<T> entityClass;

	public BaseDAOImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public void save(T entity) {
		ofy().save().entity(entity);
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
