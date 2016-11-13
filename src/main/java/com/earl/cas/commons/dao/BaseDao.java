package com.earl.cas.commons.dao;

import java.util.List;

import org.hibernate.Session;

import com.earl.cas.vo.PageInfo;

public interface BaseDao<T> {
	Session getCurrentSession();

	Integer save(T t);

	void update(T t);

	void deleteById(Long id);

	/**
	 * findById 功能跟get(int )一样
	 * 
	 * @param id
	 * @return
	 */
	T get(Long id);

	List<T> findAll();

	List<T> findAll(PageInfo pageInfo);
	
	void deleteAll();

	void delete(T persistentInstance);

	
	public List<T> findByGivenCriteria(T object);
	
	public List<T> findByGivenCriteria(T object,PageInfo pageInfo);


	void updateWithNotNullProperties(T object);

	List<T> findLikeGivenCreteria(T object, PageInfo pageInfo);


}
