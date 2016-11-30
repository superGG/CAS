package com.earl.cas.service;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.UserDetails;


public interface UserDetailsService extends BaseService<UserDetails> {

	/**
	 * 通过userId获取用户详情.
	 *@author 宋.
	 * @param id
	 */
	UserDetails getByUserId(int id);


}