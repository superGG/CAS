package com.earl.cas.service;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.User;
import com.earl.cas.entity.UserDetails;


public interface UserService extends BaseService<User> {

	/**
	 * 根据账号获取用户.
	 *@author 宋.
	 * @param account
	 * @return
	 */
	User findByAccount(String account);

	/**
	 * 获取短信验证码.
	 *@author 宋.
	 * @param phone
	 * @return
	 */
	String getSmsCode(String phone);

	/**
	 * 用户注册.
	 *@author 宋.
	 * @param user
	 * @return
	 */
	UserDetails register(User user);

	


}