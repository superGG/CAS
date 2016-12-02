package com.earl.cas.commons.service;

import java.util.List;

import com.earl.cas.vo.PageInfo;


public interface BaseService<T> {
    
	/*
	 * 保存实体.
	 */
	Integer save(T model);

	/**
	 * 前提，pojo id属性名上要有 IdAnnotatioin标签
	 * @param t
	 */
	void updateWithNotNullProperties(T t);

	/*
	 * 根据实体的主键删除实体.
	 */
	int deleteById(Integer id);

	/*
	 * 查询所有
	 */
	List<T> findAll();
	
	/*
	 * 查询所有（分页）
	 */
	List<T> findAll(PageInfo pageInfo);

	/*
	 * 根据实体主键获取实体.
	 */
	T get(Integer id);
	
	/*
	 * 通过Criteria进行非空值查询
	 */
	List<T> findByGivenCreteria(T object);

	/*
	 * 通过Criteria进行非空值查询（分页）
	 */
	List<T> findByGivenCreteria(T object, PageInfo pageInfo);
}
