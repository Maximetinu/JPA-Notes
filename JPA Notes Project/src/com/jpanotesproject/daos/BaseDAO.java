package com.jpanotesproject.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jpanotesproject.model.BaseEntity;;

public abstract class BaseDAO<E extends BaseEntity>{

    private final Class<E> type;

    @PersistenceContext
    protected EntityManager entityManager;

    protected BaseDAO(Class<E> type) {
        this.type = type;
    }

    public E findById(Long typeId) {
        return entityManager.find(type, typeId);
    }

    public void persist(E entity) {
        //if (entity.getId() != null)
        //    entityManager.merge(entity);
        //else
            entityManager.persist(entity);
    }

    public void remove(E entity) {
        if (entity.getId() != null)
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
        else
            throw new IllegalArgumentException("Entity not persisted");
    }

}