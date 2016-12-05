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
	List<Club> getByName( String clubName);
	
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
	 Club getClubByuserDetailId(int id);
	 /**
	  * 通过学校和名字精确找到社团
	  */
	 Club getByNameAndSchool(String Name,int schoolId);
}