package com.earl.cas.dao;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.User;


public interface UserDao extends BaseDao<User>{

	/**
	 * 根据账号获取用户.
	 *@author 宋.
	 * @param account
	 * @return
	 */
	User findByAccount(String account);


}