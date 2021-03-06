package com.earl.cas.service;

import java.util.List;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Position;


public interface PositionService extends BaseService<Position> {

	/**
	 * 通过社团名字找到该社团的职位信息
	 * @param name
	 * @return list
	 */
	
	List<Position> getByClubId(int id);
	/**
	 * 更新社团职位
	 * @param position
	 */
	void update(Position position);
	/**
	 * 获得职位名称
	 * @param id
	 * @return
	 */
	List<String> getNameByClubId(int id);
	/**
	 * 通过ID找到对象
	 * @param id
	 * @return
	 */
	Position findById(Integer id);
	/**
	 * 精确获取社团职位
	 * @param clubId
	 * @param name
	 */
	Position getByClubIdAndName(Integer clubId, String name);

}