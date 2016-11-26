package com.earl.cas.commons.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanMap;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.earl.cas.commons.domain.IdAnnotatioin;
import com.earl.cas.vo.PageInfo;



public class BaseDaoImpl<T> implements BaseDao<T> {

    private static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

    @SuppressWarnings("rawtypes")
	private Class entityClazz;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClazz = (Class<T>) params[0];
    }
    
    @Resource(name = "sessionFactory")
	SessionFactory sessionFactory;
    
    public Session getCurrentSession() {
		logger.debug("进入getCurrentSession函数");
		return sessionFactory.getCurrentSession();
	}
    
	// 插入对象
	@Override
	/**
	 * @author Administrator
	 * 
	 * @Param  T   object
	 * @Result void
	 */
	public Integer save(T t) {
		System.out.println(t.toString());
		// logger.debug("saving " + clazz.getName() + " instance");
//		Integer id =  (Integer) getCurrentSession().save(t);
		return (Integer) getCurrentSession().save(t);
	}

	// 更新对象
	@Override
	public void update(T t) {
		logger.debug("update " + entityClazz.getName() + " instance");
		getCurrentSession().update(t);
	}

	// 根据ID删除对象
	@Override
	public int deleteById(Integer id) {
		logger.debug("delete " + entityClazz.getName() + " instance");
		String hql = "delete from " + entityClazz.getSimpleName() + " where id = ?";
		return getCurrentSession().createQuery(hql).setInteger(0, id).executeUpdate();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Integer id) {
		T object = (T) getCurrentSession().get(entityClazz, id);
		return object;
	}

	// 查找该表中的所有记录，
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		String hql = "from " + entityClazz.getSimpleName();
		System.out.println("hql:" + hql);
		List<T> list = getCurrentSession().createQuery(hql).list();
		logger.info(list.toString());
		return list;
	}

	@Override
	public List<T> findAll(PageInfo pageInfo) {
		String hql = "from " + entityClazz.getSimpleName();
		@SuppressWarnings("unchecked")
		List<T> list = getCurrentSession()
				.createQuery(hql)
				.setFirstResult(
						(pageInfo.getIndexPageNum() - 1) * pageInfo.getSize())
				.setMaxResults(pageInfo.getSize()).list();

		String hql2 = "select count(*) from " + entityClazz.getSimpleName();
		Object uniqueResult = getCurrentSession().createQuery(hql2)
				.uniqueResult();
		Long intValue = (new Integer(uniqueResult.toString())).longValue();
		pageInfo.setTotalCount(intValue);
		return list;
	}

	// 删除所有对象
	@Override
	public void deleteAll() {
		logger.debug("delete " + entityClazz.getName() + " instance");
		String hql = "delete from " + entityClazz.getName();
		getCurrentSession().createQuery(hql).executeUpdate();
	}

	/**
	 * 通过对象来进行删除,软删除对象
	 */
	@Override
	public void delete(T persistentInstance) {
		logger.info("delete " + entityClazz.getName() + " instance");
		try {
			logger.info(persistentInstance.toString());
			getCurrentSession().delete(persistentInstance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 通过给定条件进行查询
	@Override
	public List<T> findByGivenCriteria(T object) {
		// 业务逻辑开始
		Map<String, Object> notNullParam = null;
		notNullParam = getNotNullProperties(object);
		Criteria criteria = getCurrentSession().createCriteria(entityClazz);

		for (Entry<String, Object> entry : notNullParam.entrySet()) {

			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));

		}
		@SuppressWarnings("unchecked")
		List<T> listObject = criteria.list();

		// 业务逻辑结束
		logger.debug("退出findByGivenCreteria方法");
		return listObject;
	}

	/**
	 * 通过给定条件查询,并且分页.
	 */
	public List<T> findByGivenCriteria(T object, PageInfo pageInfo) {
		// // 业务逻辑开始

		Map<String, Object> notNullParam = null;
		notNullParam = getNotNullProperties(object);

		Criteria criteria = getCurrentSession().createCriteria(entityClazz);
		for (Entry<String, Object> entry : notNullParam.entrySet()) {
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}

		@SuppressWarnings("unchecked")
		List<T> listObject = pageAttribute(criteria, pageInfo).list();

		return listObject;
	}

	/**
	 * 根据给出的Object条件分页查询该表中的所有记录，并且分页显示
	 * 
	 * @author 马德泉
	 * 
	 */
	@Override
	public List<T> findLikeGivenCreteria(T object, PageInfo pageInfo) {
		// 业务逻辑开始

		Map<String, Object> notNullParam = null;
		notNullParam = getNotNullProperties(object);

		Criteria criteria = getCurrentSession().createCriteria(entityClazz);
		for (Entry<String, Object> entry : notNullParam.entrySet()) {
			// criteria.add(Restrictions.like(key,"%" +
			// notNullParam.get(key)+"%"));
			criteria.add(Restrictions.like(entry.getKey(),
					(String) entry.getValue(), MatchMode.ANYWHERE));
		}
		// 分页
		// criteria.setFirstResult(
		// (pageInfo.getIndexPageNum() - 1) * pageInfo.getSize())
		// .setMaxResults(pageInfo.getSize()).list();

		@SuppressWarnings("unchecked")
		List<T> listObject = criteria.list();

		// 业务逻辑结束
		logger.debug("退出findByGivenCreteriaWithPage方法");
		return listObject;
	}

	// 前提，pojo id属性名上要有 IdAnnotatioin标签
	@Override
	public void updateWithNotNullProperties(T object) {
		T t = null;
		Map<String, Object> notNullParam;
		try {
			notNullParam = getNotNullProperties(object);
			BeanMap beanMap = new BeanMap(object);
			Field[] fields = entityClazz.getDeclaredFields();
			for (Field field : fields) {
				// 判断该属性是否标注着idAnnotation
				if (field.isAnnotationPresent(IdAnnotatioin.class)) {
					Integer id;
					// 得到po的id
					id = (Integer) beanMap.get(field.getName());
					// 通过hibernate的id查询出对象
					t = get(id);
					break;
				}
			}
			for (Entry<String, Object> entry : notNullParam.entrySet()) {
				// 得到非空属性的set方法,得到非空属性的值
				Method writeMethod = beanMap.getWriteMethod(entry.getKey());
				// 赋值，
				writeMethod.invoke(t, entry.getValue());
			}
			// 更新
			getCurrentSession().update(t);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private Map<String, Object> getNotNullProperties(T object) {
		Map<String, Object> notNullParam = null;
		BeanMap beanMap = new BeanMap(object);
		notNullParam = new HashMap<String, Object>();
		Iterator<Object> keyIterator = beanMap.keySet().iterator();
		String propertyName = null;
		while (keyIterator.hasNext()) {
			propertyName = (String) keyIterator.next();
			if (!"class".equals(propertyName)
					&& beanMap.get(propertyName) != null
					&& !"".equals(beanMap.get(propertyName))) {
				notNullParam.put(propertyName, beanMap.get(propertyName));
			}
		}
		return notNullParam;
	}
	
	protected Criteria pageAttribute(Criteria criteria,PageInfo pageInfo){
		
				pageInfo.setTotalCount((Long) criteria.setProjection(
						Projections.rowCount()).uniqueResult());
				criteria.setProjection(null);
				criteria.setFirstResult(
						(pageInfo.getIndexPageNum() - 1) * pageInfo.getSize())
						.setMaxResults(pageInfo.getSize()).list();
				return criteria;
	}

	
}
