package com.earl.cas.dao.Impl;

import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.UserDetailsDao;
import com.earl.cas.entity.UserDetails;

/**
 * userDetailsDao的实现类
 * 
 * @author 宋
 * @date 2016-11-23
 */
@Repository("userDetailsDao")
public class UserDetailsDaoImpl extends BaseDaoImpl<UserDetails> implements
		UserDetailsDao {

	@Override
	public UserDetails getByUserId(int id) {
		String hql = "from UserDetails where user_id =:id";
		UserDetails userdetail = (UserDetails) getCurrentSession()
				.createQuery(hql).setInteger("id", id).uniqueResult();
		return userdetail;
	}

}