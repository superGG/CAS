package com.earl.cas.dao;

import java.util.List;

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
	Boolean update(ClubType clubtype);
	
	/**
	 * 获取社团类型名字
	 */
	List<ClubType>  getTypeName(ClubType clubtype);
	/**
	 * 根据名字获取
	 * @param name
	 * @return
	 */
	ClubType getByName(String name);
}