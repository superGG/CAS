package com.earl.cas.commons.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.vo.PageInfo;


@Transactional
public abstract class BaseServiceImpl<T , E>
		implements BaseService<T, E> {

	protected abstract BaseDao<T> getDao();

	protected Class<T> entityClazz;

	protected Class<E> criteriaClazz;

	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClazz = (Class<T>) params[0];
		criteriaClazz = (Class<E>) params[1];
	}
	
	@Override
	public void save(T model) {
		getDao().save(model);
	}

	@Override
	public Boolean update(T t) {
		try {
			getDao().update(t);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void deleteById(Integer id) {
		getDao().deleteById(id);
	}

	@Override
	public T get(Integer id) {
		return (T) getDao().get(id);
	}

	@Override
	public List<T> findAll() {
		return getDao().findAll();
	}

	@Override
	public List<T> findAll(PageInfo pageInfo) {
		return getDao().findAll(pageInfo);
	}

	@Override
	public List<T> findByGivenCreteria(T object) {

		return getDao().findByGivenCriteria(object);
	}

	@Override
	public List<T> findByGivenCreteria(T object,PageInfo pageInfo) {

		return getDao().findByGivenCriteria(object,pageInfo);
	}
	
	
	@Override
	public void updateWithNotNullProperties(T t) {
		getDao().updateWithNotNullProperties(t);
	}

}
