package com.jpanotesproject.daos;

import javax.persistence.EntityManager;

import com.jpanotesproject.model.BaseEntity;;

public abstract class BaseDAO<E extends BaseEntity> {

	private final Class<E> type;

	protected EntityManager entityManager;

	protected BaseDAO(Class<E> type, EntityManager context) {
		this.type = type;
		entityManager = context;
	}

	public E findById(Long typeId) {
		return entityManager.find(type, typeId);
	}

	public void persist(E entity) {
		if (entity.getId() != null)
			entityManager.merge(entity);
		else
			entityManager.persist(entity);
	}

	public void remove(E entity) {
		if (entity.getId() != null)
			entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
		else
			throw new IllegalArgumentException("Entity not persisted");
	}

}