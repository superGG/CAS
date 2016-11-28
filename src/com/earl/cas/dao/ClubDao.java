package com.earl.cas.dao;

import java.util.List;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.Club;


public interface ClubDao extends BaseDao<Club>{
	/**
	 * 通过名字找到该社团
	 * @param name
	 * @param clubName 
	 */
	public Club getByName(int SchoolId, String clubName);
 
}