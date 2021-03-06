package com.earl.cas.dao.Impl;

import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.UserDao;
import com.earl.cas.entity.User;

/**
 * userDao的实现类
 * 
 * @author 宋
 * @date 2016-11-23
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User findByAccount(String account) {
		String hql = "from User where account = :account";
		User user = (User) getCurrentSession().createQuery(hql)
				.setString("account", account).uniqueResult();
		return user;
	}

}