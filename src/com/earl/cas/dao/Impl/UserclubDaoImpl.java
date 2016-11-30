package com.earl.cas.dao.Impl;

import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.UserclubDao;
import com.earl.cas.entity.Userclub;

/**
 * userclubDao的实现类
 *@author 宋
 *@date 2016-11-23
 */
@Repository("userclubDao")
public class UserclubDaoImpl extends BaseDaoImpl<Userclub> implements UserclubDao {
	
	public int deleteByapplyId(int applyId){
		String hql = "delete Userclub  where applyId = :applyId";
		int flag=getCurrentSession().createQuery(hql).setInteger("applyId",applyId).executeUpdate();
		getCurrentSession().flush();
		return flag;
	}
	
}