package com.elton.treinamento.model.generic;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Filter;


public interface IGenericDao<E extends IEntity<K>, K extends Serializable> {

	E findById(final K id);

	List<E> findMany(final List<K> ids);

	List<E> findAll();

	List<E> findPage(final Long page, final Long size);

	E save(E entity);

	List<E> save(List<E> entities);

	E saveOrUpdate(E entity);

	List<E> saveOrUpdate(List<E> entities);

	E update(E entity);

	List<E> update(List<E> entities);

	void delete(E entity);

	void delete(List<E> entities);

	void deleteById(final K entityId);

	void deleteByIds(final List<K> entityIds);

	void flush();

	void detach(E entity);

	boolean contains(E entity);

	Filter enableFilter(String filterName);

	void disableFilter(String filterName);

}
