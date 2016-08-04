package com.elton.treinamento.model.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public abstract class AbstractDao<T extends IEntity<K>, K extends Serializable> implements IGenericDao<T, K> {

	private final Class<T> clazz;

	@PersistenceContext
	protected EntityManager entityManager;

	public AbstractDao(final Class<T> clazz) {
		this.clazz = clazz;
	}

	public boolean contains(final T entity) {
		return entityManager.contains(entity);
	}

	protected TypedQuery<T> createNamedTypedQuery(final String namedQuery) {
		return entityManager.createNamedQuery(namedQuery, this.clazz);
	}

	protected <N> TypedQuery<N> createNamedTypedQuery(final String namedQuery, final Class<N> clazz) {
		return entityManager.createNamedQuery(namedQuery, clazz);
	}

	protected Query createNativeQuery(final String nativeQuery) {
		return entityManager.createNativeQuery(nativeQuery, this.clazz);
	}

	protected <N> Query createNativeQuery(final String nativeQuery, final Class<N> clazz) {
		return entityManager.createNativeQuery(nativeQuery, clazz);
	}

	protected <N> Query createNativeQueryWithoutClass(final String nativeQuery) {
		return entityManager.createNativeQuery(nativeQuery);
	}

	protected TypedQuery<T> createTypedQuery(final String query) {
		return entityManager.createQuery(query, this.clazz);
	}

	protected <N> TypedQuery<N> createTypedQuery(final String query, final Class<N> clazz) {
		return entityManager.createQuery(query, clazz);
	}

	protected Query createUpdate(final String query) {
		return entityManager.createQuery(query);
	}

	public void delete(final List<T> entities) {
		for (final T entity : entities) {
			entityManager.remove(entity);
		}
	}

	public void delete(final T entity) {
		entityManager.remove(entity);
	}

	public void deleteById(final K entityId) {
		final T entity = findById(entityId);

		delete(entity);
	}

	public void deleteByIds(final List<K> entityIds) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<T> criteria = builder.createQuery(this.clazz);

		final Root<T> entityRoot = criteria.from(this.clazz);

		criteria.select(entityRoot);
		criteria.where(entityRoot.get("id").in(entityIds));

		final List<T> entities = entityManager.createQuery(criteria).getResultList();

		this.delete(entities);
	}

	public void detach(final T entity) {
		entityManager.detach(entity);
	}

	public void disableFilter(final String filterName) {
		entityManager.unwrap(Session.class).disableFilter(filterName);
	}

	public Filter enableFilter(final String filterName) {
		return entityManager.unwrap(Session.class).enableFilter(filterName);
	}

	public List<T> findAll() {
		return entityManager.createQuery("from " + this.clazz.getName()).getResultList();
	}

	public T findById(final K id) {
		return entityManager.find(this.clazz, id);
	}

	public List<T> findMany(final List<K> ids) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<T> criteria = builder.createQuery(this.clazz);

		final Root<T> entityRoot = criteria.from(this.clazz);

		criteria.select(entityRoot);
		criteria.where(entityRoot.get("id").in(ids));

		return entityManager.createQuery(criteria).getResultList();
	}

	public List<T> findPage(final Long page, final Long size) {
		Assert.isTrue(page > 0, "The page number must be greater than zero");
		Assert.isTrue(size > 0, "The number of records must be greater than zero");
		return entityManager.createQuery("from " + this.clazz.getName())
				.setFirstResult((page.intValue() - 1) * size.intValue()).setMaxResults(size.intValue()).getResultList();
	}

	public void flush() {
		entityManager.flush();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	protected void refresh(final T entity) {
		entityManager.refresh(entity);
	}

	public List<T> save(final List<T> entities) {
		final List<T> result = new ArrayList<T>();

		for (final T entity : entities) {
			entityManager.persist(entity);
			result.add(entity);
		}

		return result;
	}

	public T save(final T entity) {
		entityManager.persist(entity);
		return entity;
	}

	public List<T> saveOrUpdate(final List<T> entities) {
		final List<T> result = new ArrayList<T>();

		for (final T entity : entities) {
			result.add(entityManager.merge(entity));
		}

		return result;
	}

	public T saveOrUpdate(final T entity) {
		return entityManager.merge(entity);
	}

	protected void setParameters(final Query query, final Object[] params) {
		if (query != null && params != null) {
			for (int i = 1; i <= params.length; i++) {
				query.setParameter(i, params[i - 1]);
			}
		}
	}

	public List<T> update(final List<T> entities) {
		final List<T> result = new ArrayList<T>();

		for (final T entity : entities) {
			result.add(entityManager.merge(entity));
		}

		return result;
	}

	public T update(final T entity) {
		return entityManager.merge(entity);
	}
}
