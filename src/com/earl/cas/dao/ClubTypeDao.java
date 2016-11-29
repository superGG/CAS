package com.earl.cas.dao;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.ClubType;


public interface ClubTypeDao extends BaseDao<ClubType>{
	/**
	 * 通过id删除社团类型
	 */
	int delete(Integer id);
    
	/**
	 * 更新社团类型
	 */
	public Boolean update(ClubType clubs);
}