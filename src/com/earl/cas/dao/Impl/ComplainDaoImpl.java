package com.earl.cas.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.ComplainDao;
import com.earl.cas.entity.Complain;
import com.earl.cas.vo.PageInfo;

/**
 * complainDao的实现类
 * 
 * @author 宋
 * @date 2016-11-23
 */
@Repository("complainDao")
public class ComplainDaoImpl extends BaseDaoImpl<Complain> implements ComplainDao {

	public List<Complain> findAll(PageInfo pageInfo) {
		String hql = "from Complain order by createtime desc";
		@SuppressWarnings("unchecked")
		List<Complain> list = getCurrentSession()
				.createQuery(hql)
				.setFirstResult(
						(pageInfo.getIndexPageNum() - 1) * pageInfo.getSize())
				.setMaxResults(pageInfo.getSize()).list();

		String hql2 = "select count(*) from Complain";
		Object uniqueResult = getCurrentSession().createQuery(hql2)
				.uniqueResult();
		Long intValue = (new Integer(uniqueResult.toString())).longValue();
		pageInfo.setTotalCount(intValue);
		return list;
	}
}