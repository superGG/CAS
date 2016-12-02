package com.earl.cas.service;

import java.util.List;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Club;
import com.earl.cas.entity.Position;


public interface PositionService extends BaseService<Position> {

	/**
	 * 通过社团名字找到该社团的职位信息
	 * @param name
	 * @return list
	 */
	List<Position> getByClubName(String clubName);
	/**
	 * 通过社团ID获取职位信息
	 * @param id
	 * @return
	 */
	List<Position> getByClubId(int id);
	/**
	 * 更新社团职位
	 * @param position
	 */
	void update(Position position);


}