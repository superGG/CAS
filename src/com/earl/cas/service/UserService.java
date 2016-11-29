package com.earl.cas.service;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.User;


public interface UserService extends BaseService<User> {

	/**
	 * 根据账号获取用户.
	 *@author 宋.
	 * @param account
	 * @return
	 */
	User findByAccount(String account);

	


}