package com.earl.cas.service;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Club;


public interface ClubService extends BaseService<Club> {
	/**
	 * 获取某学校的某个社团
	 * @param schoolName
	 * @param clubName
	 * @return
	 */
	public Club getByName(int schoolId, String clubName);

}