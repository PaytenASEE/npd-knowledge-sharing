package com.isah.ormpitfalls.repo.spec;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Base generic repository which provides CRUD operations for entities
 *
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractRepository<T, ID> {
	@PersistenceContext
	protected EntityManager entityManager;
	private Class<T> entityClass;

	public AbstractRepository(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void add(T entity) {
		entityManager.persist(entity);
	}

	public T merge(T entity) {
		return entityManager.merge(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}

	public T find(ID id) {
		return entityManager.find(entityClass, id);
	}
}
