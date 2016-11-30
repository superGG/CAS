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
	public List<Club> getByName( String clubName);
	
	/**
	 * 更新社团
	 * @param club
	 * @return
	 */
	public boolean update(Club club);
	/**
	 * 根据userDetailId找到社团
	 * @param id
	 * @return
	 */
	public Club getClubByuserDetailId(int id);
 
}