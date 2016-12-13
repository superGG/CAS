package com.earl.cas.commons.dao;

import java.util.List;

import org.hibernate.Session;

import com.earl.cas.vo.PageInfo;

public interface BaseDao<T> {
	
	Session getCurrentSession();

	/*
	 * 保存实体.
	 */
	Integer save(T t);

	/*
	 * 根据实体的主键删除实体.
	 */
	int deleteById(Integer id);

	/*
	 * 根据实体主键获取实体.
	 */
	T get(Integer id);

	/*
	 * 查询所有
	 */
	List<T> findAll();
	
	/*
	 * 查询所有（分页）
	 */
	List<T> findAll(PageInfo pageInfo);
	
	/*
	 * 删除所有
	 */
	void deleteAll();

	/*
	 * 删除对象
	 */
	void delete(T persistentInstance);

	/*
	 * 通过Criteria进行非空值查询
	 */
	public List<T> findByGivenCriteria(T object);
	
	/*
	 * 通过Criteria进行非空值查询（分页）
	 */
	public List<T> findByGivenCriteria(T object,PageInfo pageInfo);

	/*
	 * 非空值更新（必须含有主键）** 前提，pojo id属性名上要有 IdAnnotatioin标签**
	 */
	void updateWithNotNullProperties(T object);

	List<T> findLikeGivenCreteria(T object, PageInfo pageInfo);

}
