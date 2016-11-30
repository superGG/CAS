package com.earl.cas.dao;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.UserDetails;


public interface UserDetailsDao extends BaseDao<UserDetails>{

	/**
	 * 通过userId获取用户详情.
	 *@author 宋.
	 * @param id
	 */
	UserDetails getByUserId(int id);
	
    
}