package com.earl.cas.commons.service;

import java.util.List;

import com.earl.cas.vo.PageInfo;


public interface BaseService<T, E> {

	void save(T model);

	Boolean update(T t);
	
	/**
	 * 前提，pojo id属性名上要有 IdAnnotatioin标签
	 * @param t
	 */
	void updateWithNotNullProperties(T t);

	void deleteById(Long id);

	List<T> findAll();
	
	List<T> findAll(PageInfo pageInfo);

	T get(Long id);
	
	List<T> findByGivenCreteria(T object);

	List<T> findByGivenCreteria(T object, PageInfo pageInfo);
}
