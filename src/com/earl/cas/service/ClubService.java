package com.earl.cas.service;

import java.util.List;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Club;

public interface ClubService extends BaseService<Club> {
	/**
	 * 获取某学校的某个社团
	 * 
	 * @param schoolName
	 * @param clubName
	 * @return
	 */
	List<Club> getByName(String clubName);

	/**
	 * 更新社团
	 * 
	 * @param club
	 * @return
	 */
	boolean update(Club club);

	/**
	 * 根据detailId查找社团
	 * 
	 * @param id
	 * @return
	 */
	Club getClubByuserDetailId(int id);

}